package com.zfb.bootworld.system.security;

import com.alibaba.fastjson.JSON;
import com.zfb.bootworld.system.common.Response;
import com.zfb.bootworld.system.exception.BusinessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("authenticationFailure")
public class AuthenticationFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {


        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(Response.failure("用户名或密码错误")));
        out.flush();
        out.close();

        // throw new BadCredentialsException("用户名或密码错误");


        //重定向到失败页面
        // request.getRequestDispatcher("/login?meg="+meg).forward(request, response);
    }

}