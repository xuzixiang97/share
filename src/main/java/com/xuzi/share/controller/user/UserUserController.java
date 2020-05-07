package com.xuzi.share.controller.user;


import com.xuzi.share.constant.UserStatus;
import com.xuzi.share.constant.UserType;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.DesignerService;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.impl.UserServiceImpl;
import com.xuzi.share.utils.FileUtil;
import com.xuzi.share.utils.HostHolder;
import com.xuzi.share.utils.ShareUtil;
import org.apache.catalina.Session;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private DesignerService designerService;


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
            List<Item> items1 = itemService.selectByStyleId("原创上衣");//原创上衣
            List<Item> items2 = itemService.selectByStyleId("时尚群套");//时尚套裙
            List<Item> items3 = itemService.selectByStyleId("春季新品");//春季新品
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

    /**
     * 注册页面
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String getRegister(Model model, User user){
        user.setType(UserType.COMMON);
        user.setStatus(UserStatus.NORMAL);
        userService.register(user);
        return "user/login";
    }

    // 更新头像路径
    @RequestMapping(path = "/header/url", method = RequestMethod.POST)
    @ResponseBody
    public String updateHeaderUrl(String key, String filename, HttpSession session) {
        if (StringUtils.isBlank(filename)) {
            return ShareUtil.getJSONString(1, "文件名不能为空!");
        }
        Object userId = session.getAttribute("userId");
        String url = fileUtil.getUrl(key);
        User user1 = new User();
        user1.setId(Integer.parseInt(userId.toString()));
        user1.setHeaderUrl(url);
        userService.updateByCondition(user1);
        return ShareUtil.getJSONString(0,"成功好了");
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param model
     * @return
     */
    @RequestMapping(path = "/updatepassword", method = RequestMethod.POST)
    public String updatePassword(String oldPassword, String newPassword, Model model) {

        if (StringUtils.isBlank(oldPassword)) {
            model.addAttribute("oldPasswordMsg", "密码不能为空");
            return "/site/setting";
        }

        if (StringUtils.isBlank(newPassword)) {
            model.addAttribute("newPasswordMsg", "密码不能为空");
            return "/site/setting";
        }

        Designer designer = hostHolder.getDesigner();
        if (oldPassword.equals(designer.getPassword())) {
            Designer designer2 = new Designer();
            designer2.setPassword(newPassword);
            designer2.setId(hostHolder.getDesigner().getId());
            designerService.updateByCondition(designer2);
            return "redirect:/designer/logout";
        }

        model.addAttribute("oldPasswordMsg", "密码不正确");
        return "/site/setting";
    }
}
