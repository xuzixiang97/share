package com.xuzi.share.controller.admin;


import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Page;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.DesignerService;
import com.xuzi.share.service.impl.UserServiceImpl;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/admin/designer")
public class AdmDesignerController {
    @Autowired
    private DesignerService designerService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Page page) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        page.setRows(designerService.selectRows());
        page.setPath("/admin/designer/page");
        List<Designer> designers = designerService.findPage(page.getOffset(), page.getLimit());
        model.addAttribute("designers", designers);
        return "admin/designer";
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/review")
    public String review(Model model) {
        List<Designer> designers = designerService.findByStatus(1);
        model.addAttribute("designers", designers);
        return "admin/designereview";
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/reviewed")
    @ResponseBody
    public String review(Model model, Integer designerId) {
        Designer designer = new Designer();
        designer.setId(designerId);
        designer.setStatus(2);
        designerService.updateByCondition(designer);
        return ShareUtil.getJSONString(0);
    }

}
