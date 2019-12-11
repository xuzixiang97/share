package com.xuzi.share.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String getHello() {
        return "hello";
    }
}
