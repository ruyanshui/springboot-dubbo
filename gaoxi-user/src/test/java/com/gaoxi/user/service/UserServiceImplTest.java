package com.gaoxi.user.service;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.facade.user.IUserService;
import com.gaoxi.req.user.LoginReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    public void login() {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("ccdd");
        loginReq.setPassword("123");
        UserEntity userEntity = userService.login(loginReq);
        System.out.println(userEntity.toString());
    }
}


