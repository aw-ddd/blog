package com.dwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwj.pojo.Permission;
import com.dwj.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    //根据用户名查找权限
    public List<Permission> findPermissionByUsername(@Param("username") String username);
}
