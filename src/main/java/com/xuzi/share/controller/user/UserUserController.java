package com.xuzi.share.controller.user;


import com.xuzi.share.entity.Page;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserUserController {
    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     * @param model
     * @return
     */
    @RequestMapping("/login/page")
    public String getLoginPage(Model model){
        return "user/login";
    }

    /**
     * 用户登录
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String getLogin(Model model){
        return "user/index";
    }

    /**
     * 跳转注册页面
     * @param model
     * @return
     */
    @RequestMapping("/register/page")
    public String getRegisterPage(Model model){
        return "user/reg";
    }
}
