package com.xuzi.share.controller.designer;


import com.xuzi.share.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/designer")
public class DesUrlController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 跳转设计师平台
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String getLoginPage(Model model) {
        return "designer/index";
    }




}
