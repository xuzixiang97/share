package com.xuzi.share.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String getHello(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "/admin/xuzi";
    }

    @RequestMapping("/login")
    public String getLogin1(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/login";
    }

    @RequestMapping("/logina")
    public String getLogin3(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/login2.html";
    }
}
