package com.xuzi.share.controller.designer;

import com.xuzi.share.entity.Designer;
import com.xuzi.share.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/designer")
public class DesDesignerController {
    @Autowired
    private FileUtil fileUtil;

    /**
     * 跳转登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/login/page")
    public String getlogin(Model model) {
        return "designer/login";
    }

    /**
     * 跳转注册页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/register/page")
    public String getRegister(Model model) {
        fileUtil.fileup(model);
        return "designer/register";
    }

    /**
     * 注册
     *
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model, Designer designer,String key,String key2) {
        String url = fileUtil.getUrl(key);
        String url2 = fileUtil.getUrl(key2);
        return "designer/login";
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

}
