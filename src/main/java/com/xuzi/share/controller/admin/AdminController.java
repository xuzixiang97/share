package com.xuzi.share.controller.admin;


import com.xuzi.share.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/login/page")
    public String getLoginPage(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/loginxuzi";
    }

    @RequestMapping("/login")
    public String login(Model model, Admin admin) {

        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/loginxuzi";
    }

}
