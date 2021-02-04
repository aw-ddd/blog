package com.dwj.config;

import com.alibaba.fastjson.JSON;
import com.dwj.common.JsonResult;
import com.dwj.common.ResultCode;
import com.dwj.common.ResultTool;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录失败逻辑处理器
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回JSON数据
        JsonResult result = null;
        if (e instanceof AccountExpiredException){
            //账号过期
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        }else if (e instanceof DisabledException){
            //账号不可用
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        }else if (e instanceof LockedException){
            //账号已锁定
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        }else if (e instanceof InternalAuthenticationServiceException){
            //用户不存在
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }else if (e instanceof BadCredentialsException){
            //密码错误
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        }else if (e instanceof CredentialsExpiredException){
            //密码过期
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        }else{
            //其他错误
            result = ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        //处理编码
            httpServletResponse.setContentType("text/json;charset=utf-8");
        //将结果输出到前端
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
