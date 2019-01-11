package com.zfb.bootworld.system.security;

import com.zfb.bootworld.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("authenticationSuccess")
public class AuthenticationSuccess implements AuthenticationSuccessHandler {
    //验证成功后进入系统前进行的处理，比如初始化权限用户信息等
    @Autowired
    private UserService userService ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //authentication.getName()这个是认证通过的账号
        // SysUser loginUser = userService.getUserLoginByName(authentication.getName());
        // //例如把用户信息存入session
        // request.getSession().setAttribute("USERSESSION", loginUser);
        // //处理完成后重定向到系统主页面的链接
        // new DefaultRedirectStrategy().sendRedirect(request, response, "/main");

            User userDetails = (User)authentication.getPrincipal();


            log.info("登陆用户账号:{},IP:{}",userDetails.getUsername(),getIpAddress(request));

            // super.onAuthenticationSuccess(request, response, authentication);
    }

        private String getIpAddress(HttpServletRequest request) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
}