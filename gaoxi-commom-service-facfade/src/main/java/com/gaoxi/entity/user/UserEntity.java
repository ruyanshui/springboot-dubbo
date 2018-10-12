package com.gaoxi.entity.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserEntity implements Serializable{

    private static final long serialVersionUID = -3383201813232421258L;

    private String id;

    private String username;

    private String password;

    private String phone;

    private String mail;

    // 营业执照照片
    private String licencePic;

    private Timestamp registerTime;

    // 用户类型
    private UserTypeEnum userTypeEnum;

    // 账号状态
    private UserStateEnum userStateEnum;

    // 用户角色
    private RoleEntity roleEntity;
}
