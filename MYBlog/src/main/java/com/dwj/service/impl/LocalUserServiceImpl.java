package com.dwj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwj.mapper.UserMapper;
import com.dwj.pojo.User;
import com.dwj.service.LocalUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalUserServiceImpl implements LocalUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(String username) {
        if (!StringUtils.isBlank(username)){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",username);
            User user = userMapper.selectOne(queryWrapper);
            return user;
        }
        return null;
    }
}
