package com.dwj.pojo;

import lombok.Data;

//角色
@Data
public class Role {
    private int id;
    private String roleCode;
    private String roleName;
    private String roleDescription;
}
