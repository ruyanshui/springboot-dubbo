package com.gaoxi.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.user.*;
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
        return Result.newSuccessResult(userEntity);
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
        return Result.newSuccessResult();
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
        return null;
    }

    @Override
    public Result isLogin(HttpServletRequest httpReq) {
        return null;
    }

    @Override
    public Result<List<UserEntity>> findUsers(UserQueryReq userQueryReq) {
        return null;
    }

    @Override
    public Result batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {
        return null;
    }

    @Override
    public Result createAdminUser(AdminCreateReq adminCreateReq) {
        return null;
    }

    @Override
    public Result<List<RoleEntity>> findRoles() {
        return null;
    }

    @Override
    public Result deleteRole(String roleId) {
        return null;
    }

    @Override
    public Result updateMenuOfRole(RoleMemuReq roleMenuReq) {
        return null;
    }

    @Override
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        return null;
    }

    @Override
    public Result<List<PermssionEntity>> findPermissions() {
        return null;
    }

    @Override
    public Result<List<MenuEntity>> findMenus() {
        return null;
    }

    @Override
    public Result<List<LocationEntity>> findLocations(HttpServletRequest httpReq) {
        return null;
    }

    @Override
    public Result<String> createLocation(LocationCreateReq locationCreateReq, HttpServletRequest httpReq) {
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
