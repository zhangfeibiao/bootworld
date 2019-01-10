package com.zfb.bootworld.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println(username);
        System.out.println(password);

    }



}
