package com.xuzi.share.controller.designer;


import com.xuzi.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/designer")
public class DesUrlController {
    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String getLoginPage(Model model) {
        return "designer/index";
    }

    /**
     * 跳转审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/review")
    public String getshenhe(Model model) {
        return "designer/review";
    }

    /**
     * 跳转审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String getlogin(Model model) {
        return "designer/login";
    }

    /**
     * 跳转审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/login1")
    public String getlogin1(Model model) {
        return "designer/login_v2";
    }

    /**
     * 跳转审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String getRegister(Model model) {
        return "designer/register";
    }

}
