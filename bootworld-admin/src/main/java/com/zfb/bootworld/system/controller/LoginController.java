package com.zfb.bootworld.system.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/9 16:01
 */
@Controller
public class LoginController {


    @GetMapping({"/", ""})
    String welcome(Model model) {

        return "redirect:/blog";
    }

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("username", "test");
        model.addAttribute("password", "123456");
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
   public void ajaxLogin(String username, String password) {


    }



}
