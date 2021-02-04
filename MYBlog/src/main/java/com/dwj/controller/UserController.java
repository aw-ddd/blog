package com.dwj.controller;

import com.dwj.common.JsonResult;
import com.dwj.common.ResultTool;
import com.dwj.pojo.User;
import com.dwj.service.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private LocalUserService localUserService;

    @GetMapping("/findUser/{username}")
    public JsonResult<User> findUser(@PathVariable("username") String username){
    User user = localUserService.findUser(username);
    return ResultTool.success(user);
    }
}
