package com.xuzi.share.controller.admin;


import com.xuzi.share.entity.Admin;
import com.xuzi.share.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdmAdminController {
    @Autowired
    private AdminServiceImpl adminService;


    /**
     * 实际后台管理主界面
     * @param model
     * @return
     */
    @RequestMapping("")
    public String getLoginpage(Model model){
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/login";
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
            model.addAttribute("admin",map.get("admin"));
            return "redirect:/admin/index";
        }
        //登陆失败返回登录页面
        model.addAttribute("usernameMsg", map.get("usernameMsg"));
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        return "admin/login";
    }

    /**
     * 实际后台管理主界面
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String getIndex(Model model){
        return "admin/index";
    }

    /**
     * 实际后台管理主界面
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public String getWelcomePage(Model model){
        return "admin/welcome";
    }

}
