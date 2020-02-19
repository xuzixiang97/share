package com.xuzi.share.controller.designer;

import com.xuzi.share.constant.DesignerStatusEnum;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.service.DesignerService;
import com.xuzi.share.utils.ShareUtil;
import com.xuzi.share.utils.FileUtil;
import com.xuzi.share.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/designer")
public class DesDesignerController {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private DesignerService designerService;

    @Autowired
    private HostHolder hostHolder;

    @Value("/share")
    private String contextPath;

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
     * 登录
     *
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model, Designer designer, HttpServletResponse response, HttpSession session) {

        Map<String, Object> map = designerService.login(designer.getUsername(),designer.getPassword());
        //登录成功跳转首页
        if(map.containsKey("SuccessMessage")){
            Cookie cookie = new Cookie("designerId",map.get("designerId").toString());
            session.setAttribute("designerId",map.get("designerId").toString());
            cookie.setPath(contextPath);//cookie范围
            cookie.setMaxAge(3600*12);//cookie有效时间
            response.addCookie(cookie);
            return "redirect:/designer/index";
        }
        //注册失败返回登录页面
        model.addAttribute("usernameMsg", map.get("usernameMsg"));
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        return "designer/login";
    }

    /**
     * 登出功能
     * @return
     */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/designer/login/page";
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
    public String register(Model model, Designer designer) {

        Map<String, Object> map = designerService.register(designer);
        //注册成功跳转登录页面
        if(map.containsKey("SuccessMessage")){
            return "redirect:/designer/login/page";
        }
        //注册失败返回注册页面
        model.addAttribute("usernameMsg", map.get("usernameMsg"));
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        return "designer/register";
    }

    /**
     * 文件上传测试
     *
     * @param model
     * @return
     */
    @RequestMapping("/filetest")
    public String filetest(Model model, Designer designer,String key,String key2) {
        String url = fileUtil.getUrl(key);
        String url2 = fileUtil.getUrl(key2);
        return "designer/filetest";
    }

    // 更新头像路径
    @RequestMapping(path = "/header/url", method = RequestMethod.POST)
    @ResponseBody
    public String updateHeaderUrl(String key,String filename) {
        if (StringUtils.isBlank(filename)) {
            return ShareUtil.getJSONString(1, "文件名不能为空!");
        }
        String url = fileUtil.getUrl(key);
        Designer designer = new Designer();
        designer.setId(hostHolder.getDesigner().getId());
        designer.setHeaderUrl(url);
        designerService.updateByCondition(designer);
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


    /**
     * 跳转审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/review/page")
    public String getshenhe(Model model) {
        return "designer/review";
    }

    /**
     * 跳转等待审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/reviewing/page")
    public String getreviewing(Model model) {
        return "designer/reviewing";
    }

    /**
     * 跳转审核完成页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/reviewed/page")
    public String getreviewed(Model model) {
        return "designer/reviewed";
    }



    /**
     * 提交审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/review")
    public String shenhe(Model model,Designer designer) {
        designer.setId(hostHolder.getDesigner().getId());
        //修改审核状态为待审核
        designer.setStatus(DesignerStatusEnum.UNEXAMINEING.getStatus());
        designerService.updateByCondition(designer);
        return "designer/reviewing";
    }

}
