package com.dwj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwj.mapper.UserMapper;
import com.dwj.pojo.Permission;
import com.dwj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//SpringSecurity框架的登录校验
@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",s);
        User sysUser = userMapper.selectOne(wrapper);
        if (sysUser == null){
            throw new InternalAuthenticationServiceException("该用户不存在");
        }
        List<Permission> permissionList = userMapper.findPermissionByUsername(s);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (permissionList.size() > 0){
            permissionList.forEach(e -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(e.getPermissionCode());
                authorityList.add(authority);//为登录人赋予权限
            });
        }
        return new org.springframework.security.core.userdetails.User(sysUser.getUserName(), new BCryptPasswordEncoder().encode(sysUser.getPassword()),sysUser.getEnabled(),sysUser.getAccountNotExpired(),sysUser.getCredentialsNotExpired(),sysUser.getAccountNotLocked(), authorityList);
    }
}
