package com.xuzi.share.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UrlController {

    /**
     * 后台管理员首页 展示统计等信息
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public String getWelcomPage(Model model){
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/html/welcome";
    }
}
