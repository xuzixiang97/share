package com.xuzi.share.controller.designer;


import com.xuzi.share.entity.Designer;
import com.xuzi.share.service.impl.UserServiceImpl;
import com.xuzi.share.utils.FileUtil;
import com.xuzi.share.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/designer")
public class DesUrlController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FileUtil fileUtil;

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

    /**
     * 跳转设计师平台
     *
     * @param model
     * @return
     */
    @RequestMapping("/index/page")
    public String getIndexPage(Model model) {
        return "designer/index";
    }


    /**
     * 跳转个人信息设置
     *
     * @param model
     * @return
     */
    @RequestMapping("/setting")
    public String getSettingPage(Model model) {
        fileUtil.fileup(model);
        return "designer/setting";
    }






}
