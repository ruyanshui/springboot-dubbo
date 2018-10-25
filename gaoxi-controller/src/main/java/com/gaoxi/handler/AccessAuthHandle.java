package com.gaoxi.handler;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.user.AccessAuthEntity;
import com.gaoxi.entity.user.PermssionEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.Redis.IRedisService;
import com.gaoxi.redis.RedisServiceTemp;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 访问权限处理类（所有请求都要经过此类）
 */
@Aspect
@Component
public class AccessAuthHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // HTTP Response中SessionID的名字
    @Value("${session.SessionIdName}")
    private String sessionIdName;

    @Reference(version = "1.0.0")
    private IRedisService redisService;

    // 反斜杠
    private static final String Back_Slash = "/";
    // 星
    private static final String STAR = "*";

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.gaoxi.controller..*.*(..))")
    public void accessAuth() {}

    /**
     * 拦截所有请求
     */
    @Before("accessAuth()")
    public void doBefore() {
        // 访问鉴权
        authentication();
    }

    /**
     * 查询当前用户是否允许访问该接口
     */
    private void authentication() {
        // 获取 HttpServletRequest
        HttpServletRequest request = getHttpServletRequest();
        // 获取 method 和url
        String method = request.getMethod();
        String url = request.getServletPath();
        // 获取SessionId
        String sessionID = getSessionID(request);
        // 获取SessionID对应的用户信息
        UserEntity userEntity = getUserEntity(sessionID);
        // 获取接口权限信息
        AccessAuthEntity accessAuthEntity = getAccessAuthEntity(method, url);
        // 检测权限
        authentication(userEntity, accessAuthEntity);
    }

    /**
     * 检测权限
     * @param userEntity
     * @param accessAuthEntity
     */
    private void authentication(UserEntity userEntity, AccessAuthEntity accessAuthEntity) {
        //无需登录
        if (!accessAuthEntity.isLogin()) {
            return;
        }
        // 检测是否登录
        checkLogin(userEntity, accessAuthEntity);

        // 检查是否拥有权限
        checkPermission(userEntity, accessAuthEntity);
    }

    private void checkPermission(UserEntity userEntity, AccessAuthEntity accessAuthEntity) {
        // 获取接口权限
        String accessPermission = accessAuthEntity.getPermission();

        // 获取用户权限
        List<PermssionEntity> userPermissionList = userEntity.getRoleEntity().getPermissionList();

        // 判断用户是否包含接口权限
        if (CollectionUtils.isNotEmpty(userPermissionList)) {
            for (PermssionEntity permissionEntity : userPermissionList) {
                if (permissionEntity.getPermission().equals(accessPermission)) {
                    return;
                }
            }
        }

        // 没有权限
        throw new CommonBizException(ExpCodeEnum.NO_PREMISSION);
    }

    /**
     * 检查当前接口是否需要登录
     * @param userEntity 用户信息
     * @param accessAuthEntity 接口访问权限
     */
    private void checkLogin(UserEntity userEntity, AccessAuthEntity accessAuthEntity) {
        // 尚未登录
        if (accessAuthEntity.isLogin() && userEntity==null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }
    }

    private AccessAuthEntity getAccessAuthEntity(String method, String url) {
        // 获取所有接口访问权限
        Map<String , AccessAuthEntity> accessAuthEntityMap = RedisServiceTemp.accessAuthMap;
        // 遍历所有接口访问权限
        if (accessAuthEntityMap != null && accessAuthEntityMap.size() > 0) {
            for (String key : accessAuthEntityMap.keySet()) {
                if (isMatch(key, method, url)) {
                    return accessAuthEntityMap.get(key);
                }
            }
        }
        // 没有该接口
        throw new CommonBizException(ExpCodeEnum.ERROR_404);
    }

    /**
     * 判断key是否与method +url匹配
     * @param key 接口的'method + url“
     * @param method
     * @param url
     * @return
     */
    private boolean isMatch(String key, String method, String url) {
        // 清除首尾的反斜杠
        if (url.startsWith(Back_Slash)) {
            url = url.substring(1);
        }
        if (url.endsWith(Back_Slash)) {
            url = url.substring(0, url.length()-1);
        }
        // 将URL按照反斜杠切分
        String[] urls_1 = key.split("/");
        String[] urls_2 = url.split("/");
        urls_2[0] = method + urls_2[0];
        // 反斜杠数量不同，则匹配失败
        if (urls_1.length != urls_2.length) {
            return false;
        }
        // 逐个匹配
        for (int i = 0; i < urls_1.length; i++) {
            // 若当前是个* 或则当前字符串相对，则匹配下一个
            if (STAR.equals(urls_1[i]) || urls_1[i].equals(urls_2[i])) {
                continue;
            }
            //若两个字符串不相对，则匹配失败
            return false;
        }
        // 匹配成功
        return true;
    }

    /**
     * 获取SessionID对应的用户信息
     * @param sessionID
     * @return
     */
    private UserEntity getUserEntity(String sessionID) {
        // SessionID为空
        if (StringUtils.isEmpty(sessionID)) {
            return null;
        }

        // 获取UserEntity
        // TODO 暂时存储本地
//        Object userEntity = redisService.get(sessionID);
        Object userEntity = RedisServiceTemp.userMap.get(sessionID);
        if (userEntity==null) {
            return null;
        }
        return (UserEntity) userEntity;
    }

    private String getSessionID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        // 遍历所有cookie，找出SessionID
        String sessionID = null;
        if (cookies!=null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                if (sessionIdName.equals(cookie.getName())) {
                    sessionID = cookie.getValue();
                    break;
                }
            }
        }

        return sessionID;
    }

    /**
     * 获取HttpServletRequest
     * @return
     */
    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }
}
