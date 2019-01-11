package com.zfb.bootworld.system.controller;

import com.sun.deploy.net.HttpUtils;
import com.zfb.bootworld.entity.SysUser;
import com.zfb.bootworld.service.permission.PermissionService;
import com.zfb.bootworld.system.annotation.Log;
import com.zfb.bootworld.system.common.Response;
import com.zfb.bootworld.system.common.Tree;
import com.zfb.bootworld.system.enums.ErrorCodeEnum;
import com.zfb.bootworld.system.vo.system.SysPermissionVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/9 16:01
 */
@Api(tags = "登录管理")
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private PermissionService permissionService;


    @GetMapping({"/", ""})
    String welcome(Model model) {

        return "redirect:/blog";
    }


    @GetMapping("/login")
    String login(HttpServletRequest request,Model model,@RequestParam(value = "error", required = false) String error) {

        model.addAttribute("username", "test");
        model.addAttribute("password", "123456");



        return "login";
    }

    @PostMapping("/loginPageAjax")
    @ResponseBody
    public Response loginPageAjax(HttpServletRequest request) {

        return Response.success("您已经登陆超时，请退出重新登陆！");
    }


    /**
     * 管理系统的主页
     *
     * @param model
     * @return
     */
    @Log("访问主页")
    @GetMapping({"/index"})
    String index(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // List<Tree<SysPermissionVO>> pemissionList = permissionService.listPermissionsTree(user.getId());

        // model.addAttribute("pemissionList", pemissionList);

        model.addAttribute("user", user);

        return "index";
    }

    @Log("获取用户信息")
    @GetMapping("/getUserInfo")
    @ResponseBody
    public Response getUserInfo() {
        SysUser user = new SysUser();
        user.setUsername("zhangsan");

        return Response.success(user);
    }

}
