package com.xuzi.share.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UrlController {

    /**
     * 后台管理员首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "index";
    }

    /**
     * 后台管理页面，展示统计等信息
     * @param model
     * @return
     */
    @RequestMapping("/index1")
    public String getIndex1(Model model){
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "index_v1";
    }

    @RequestMapping("/index2")
    public String getIndex2(Model model){
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "index_V2";
    }

}
