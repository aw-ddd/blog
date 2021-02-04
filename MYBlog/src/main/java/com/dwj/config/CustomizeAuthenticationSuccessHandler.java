package com.dwj.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwj.common.JsonResult;
import com.dwj.common.ResultTool;
import com.dwj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

//登陆成功逻辑处理器
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次的登录时间，更新人，更新时间等字段
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //根据登录用户的用户名到数据库中查找对应的用户信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",user.getUsername());
        com.dwj.pojo.User sysUser = userMapper.selectOne(wrapper);
        sysUser.setLastLoginTime(LocalDateTime.now());//更新最近一次登录时间
        userMapper.updateById(sysUser);

        //返回json数据
        JsonResult jsonResult = ResultTool.success();
        //处理编码情况，防止中文乱码
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //返回到前端
        httpServletResponse.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
