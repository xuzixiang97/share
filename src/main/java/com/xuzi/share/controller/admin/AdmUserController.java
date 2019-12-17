package com.xuzi.share.controller.admin;


import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.Page;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.AdminService;
import com.xuzi.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/user")
public class AdmUserController {
    @Autowired
    private UserService userService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Page page) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        page.setRows(userService.selectRows());
        page.setPath("/admin/user/page");

        List<User> users = userService.findDiscussPost(page.getOffset(), page.getLimit());
        model.addAttribute("users", users);
        return "admin/user";
    }

}
