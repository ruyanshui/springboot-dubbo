package com.gaoxi.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.Redis.IRedisService;
import com.gaoxi.redis.RedisServiceTemp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息工具包
 */
@Component
public class UserUtil {


    @Value("${session.SessionIdName}")
    private String sessionIdName;

    @Reference(version = "1.0.0")
    private IRedisService redisService;

    public UserEntity getUser(HttpServletRequest httpServletReq) {
        // 获取SessionId
        String sessionID = getSessionId(httpServletReq);
        if (StringUtils.isEmpty(sessionID)) {
            throw new CommonBizException(ExpCodeEnum.SESSION_NULL);
        }

        // 获取UserEntity
//        Object userEntity =  redisService.get(sessionID);
        // TODO 暂时使用本地redis
        Object userEntity = RedisServiceTemp.userMap.get(sessionID);
        if (userEntity == null) {
            return null;
        }
        return (UserEntity) userEntity;
    }

    /**
     * 获取SessionID
     * @param request 当前的请求对象
     * @return SessionID的值
     */
    private String getSessionId(HttpServletRequest request) {

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
}
