package com.xuzi.share.controller.designer;


import com.xuzi.share.service.impl.UserServiceImpl;
import com.xuzi.share.utils.FileUtil;
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


    /**
     * 跳转个人信息设置
     *
     * @param model
     * @return
     */
    @RequestMapping("/test")
    public String getSddettingPage(Model model) {
        fileUtil.fileup(model);
        return "designer/filetest";
    }

    /**
     * 跳转个人信息设置
     *
     * @param model
     * @return
     */
    @RequestMapping("/itemtable")
    public String getSddettiedengPage(Model model) {
        fileUtil.fileup(model);
        return "designer/itemtable";
    }



    /**
     * 跳转个人信息设置
     *
     * @param model
     * @return
     */
    @RequestMapping("/aa")
    public String getaa(Model model) {
        fileUtil.fileup(model);
        return "form_advanced";
    }


    /**
     * 跳转普通订单
     *
     * @param model
     * @return
     */
    @RequestMapping("/common/page/v1")
    public String common(Model model) {
        return "designer/common_order";
    }

    /**
     * 跳转竞标订单
     *
     * @param model
     * @return
     */
    @RequestMapping("/bidding/page/v1")
    public String bidding(Model model) {
        fileUtil.fileup(model);
        return "designer/bidding_order";
    }

    /**
     * 跳转竞标订单
     *
     * @param model
     * @return
     */
    @RequestMapping("/mybidding/page")
    public String mybidding(Model model) {
        return "designer/mybidding";
    }






}
