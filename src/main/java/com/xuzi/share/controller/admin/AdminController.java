package com.xuzi.share.controller.admin;


import com.xuzi.share.entity.Admin;
import com.xuzi.share.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping("/login/page")
    public String getLoginPage(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/login";
    }

    /**
     * 跳转到后台管理
     * @param model
     * @return
     */
    @RequestMapping("/index/page")
    public String getIndexPage(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/index";
    }

    /**
     * 管理员登录
     * @param model
     * @param admin
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model, Admin admin) {
        Map<String, Object> map = adminService.login(admin.getUsername(), admin.getPassword());
        //登录成功跳转管理员页面
        if(map.containsKey("SuccessMessage")){
            return "redirect:/admin/index/page";
        }
        //登陆失败返回登录页面
        model.addAttribute("usernameMsg", map.get("usernameMsg"));
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        return "admin/login";
    }

}
