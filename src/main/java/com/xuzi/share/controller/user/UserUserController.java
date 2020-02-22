package com.xuzi.share.controller.user;


import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserUserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ItemService itemService;

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
    public String getLogin(Model model, User user, HttpSession session){
        Map<String, Object> map = userService.login(user.getUsername(), user.getPassword());

        //登录成功跳转管理员页面
        if(map.containsKey("SuccessMessage")){
            session.setAttribute("userId",map.get("userId").toString());
            List<Item> items1 = itemService.selectByCategoryId(301);//原创上衣
            List<Item> items2 = itemService.selectByCategoryId(303);//时尚套裙
            List<Item> items3 = itemService.selectByCategoryId(501);//春季新品
            model.addAttribute("items1", items1);
            model.addAttribute("items2", items2);
            model.addAttribute("items3", items3);
            model.addAttribute("user", map.get("user"));
            return "user/index";
        }
        //登陆失败返回登录页面
        model.addAttribute("usernameMsg", map.get("usernameMsg"));
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        return "user/login";
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
