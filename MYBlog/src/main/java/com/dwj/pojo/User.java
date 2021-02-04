package com.dwj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

//用户
@Data
public class User {
    private int id;
    private String account;
    private String userName;
    private String password;
    private LocalDateTime lastLoginTime;
    private Boolean enabled;
    private Boolean accountNotExpired;
    private Boolean accountNotLocked;
    private Boolean credentialsNotExpired;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer createUser;
    private Integer updateUser;
}
