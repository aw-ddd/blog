package com.dwj.service.impl;

import com.dwj.mapper.UserMapper;
import com.dwj.pojo.User;
import com.dwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.selectAll();
        return userList;
    }
}
