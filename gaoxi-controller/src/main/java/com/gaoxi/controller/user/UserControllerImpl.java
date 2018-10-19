package com.gaoxi.controller.user;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaoxi.entity.user.*;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.Redis.IRedisService;
import com.gaoxi.facade.user.IUserService;
import com.gaoxi.redis.RedisServiceTemp;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.gaoxi.rsp.Result;

import com.gaoxi.util.UserUtil;
import com.gaoxi.utils.KeyGenerator;
import com.gaoxi.utils.RedisPrefixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.gaoxi.rsp.Result.newFailureResult;
import static com.gaoxi.rsp.Result.newSuccessResult;

@RestController
public class UserControllerImpl implements IUserController{


    @Reference(version = "1.0.0")
    private IUserService userService;

    @Reference(version = "1.0.0")
    private IRedisService redisService;

    @Value("${session.expiretime}")
    private long sessionExpireTime;

    @Value("${session.sessionIdName}")
    private String sessionIdName;

    @Autowired
    private UserUtil userUtil;

    @Override
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp) {
        // 登录鉴权
        UserEntity userEntity = userService.login(loginReq);

        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return newSuccessResult(userEntity);
    }

    private void doLoginSuccess(UserEntity userEntity, HttpServletResponse httpRsp) {
        // 生成SessionID
        String sessionID = RedisPrefixUtil.SessionID_Prefix + KeyGenerator.getKey();

        // 将 SessionID-UserEntity 存入Redis
        // TODO 暂时存储本地
//        redisService.set(sessionID, userEntity, sessionExpireTime);
        RedisServiceTemp.userMap.put(sessionID, userEntity);

        // 将SessionID存入HTTP响应头
        Cookie cookie = new Cookie(sessionIdName, sessionID);
        httpRsp.addCookie(cookie);

    }

    @Override
    public Result logout(HttpServletRequest httpReq, HttpServletResponse httpRsp) {
        // 处理登出
        doLogout(httpReq, httpRsp);
        return newSuccessResult();
    }

    private void doLogout(HttpServletRequest httpReq, HttpServletResponse httpRsp) {
        // 获取sessionId
        String sessionID = getSessionID(httpReq);
        RedisServiceTemp.userMap.remove(sessionIdName, null);
        Cookie cookie = new Cookie(sessionIdName, null);
        httpRsp.addCookie(cookie);

    }
    /**
     * 获取SessionID
     * @param httpReq 当前的请求对象
     * @return SessionID的值
     */
    private String getSessionID(HttpServletRequest httpReq) {
        Cookie[] cookies = httpReq.getCookies();

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

    @Override
    public Result register(RegisterReq registerReq, HttpServletResponse httpRsp) {
        // 用户信息入库
        UserEntity userEntity = userService.register(registerReq);
        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return newSuccessResult();
    }

    @Override
    public Result isLogin(HttpServletRequest httpReq) {
        
        String sessionId = getSessionID(httpReq);
        UserEntity userEntity = getUserEntity(sessionId);

        if (userEntity == null) {
            return newFailureResult();
        }
        return newSuccessResult(userEntity);
    }

    /**
     * 获取SessionId对应的用户信息
     * @param sessionID
     * @return
     */
    private UserEntity getUserEntity(String sessionID) {
        // SessionID为空
        if (StringUtils.isEmpty(sessionID)) {
            return null;
        }

        // 获取UserRntity
        Object userEntity = RedisServiceTemp.userMap.get(sessionID);
        if (userEntity==null) {
            return null;
        }
        return (UserEntity) userEntity;
    }

    @Override
    public Result<List<UserEntity>> findUsers(UserQueryReq userQueryReq) {
        // 查询
        List<UserEntity> userEntityList = userService.findUsers(userQueryReq);
        return newSuccessResult(userEntityList);
    }

    @Override
    public Result batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {

        // 批量更新
        userService.batchUpdateUserState(userStateReqs);

        return newSuccessResult();
    }

    @Override
    public Result createAdminUser(AdminCreateReq adminCreateReq) {
        // 创建管理员
        userService.createAdminUser(adminCreateReq);
        return newSuccessResult();
    }

    @Override
    public Result<List<RoleEntity>> findRoles() {
        // 查询
        List<RoleEntity> roleEntityList = userService.findRoles();

        // 成功
        return newSuccessResult(roleEntityList);
    }

    @Override
    public Result deleteRole(String roleId) {
        userService.deleteRole(roleId);

        return newSuccessResult();
    }

    @Override
    public Result updateMenuOfRole(RoleMemuReq roleMenuReq) {

        userService.updateMenuOfRole(roleMenuReq);
        return newSuccessResult();
    }

    @Override
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq) {

        userService.updatePermissionOfRole(rolePermissionReq);
        return newSuccessResult();
    }

    @Override
    public Result<List<PermssionEntity>> findPermissions() {

        List<PermssionEntity> permssionEntityList = userService.findPermissions();
        return newSuccessResult(permssionEntityList);
    }

    @Override
    public Result<List<MenuEntity>> findMenus() {

        List<MenuEntity> menuEntities = userService.findMenus();
        return newSuccessResult(menuEntities);
    }

    @Override
    public Result<List<LocationEntity>> findLocations(HttpServletRequest httpReq) {
        // 获取userId
        String userId = getUserId(httpReq);
        List<LocationEntity> locationEntityList = userService.findLocations(userId);
        return newSuccessResult(locationEntityList);
    }

    /**
     * 获取用户ID
     * @param httpReq
     * @return
     */
    private String getUserId(HttpServletRequest httpReq) {
        UserEntity userEntity = userUtil.getUser(httpReq);
        if (userEntity == null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }
        return userEntity.getId();
    }

    @Override
    public Result<String> createLocation(LocationCreateReq locationCreateReq, HttpServletRequest httpReq) {

        // 获取UserId
        String userId = getUserId(httpReq);
        // 新增
        String locationId = userService.createLocation()

        return null;
    }

    @Override
    public Result deleteLocation(String locationId, HttpServletRequest httpReq) {
        return null;
    }

    @Override
    public Result modifyLocation(LocationUpdateReq locationUpdateReq, HttpServletRequest httpReq) {
        return null;
    }
}
