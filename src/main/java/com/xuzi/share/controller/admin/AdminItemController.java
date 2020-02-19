package com.xuzi.share.controller.admin;

import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Page;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/item")
public class AdminItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Page page) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        page.setRows(itemService.selectRows());
        page.setPath("/admin/item/page");
        List<Item> list = itemService.findPage(page.getOffset(), page.getLimit());
        model.addAttribute("items", list);
        return "admin/item";
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/add/page")
    public String addPage(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "admin/additem";
    }

    /**
     * 新增作品
     * @param model
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(Model model, Item item) {
        itemService.insert(item);
        return ShareUtil.getJSONString(0);
    }

}
