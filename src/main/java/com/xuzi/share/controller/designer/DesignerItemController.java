package com.xuzi.share.controller.designer;

import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Page;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.utils.BeanUtil;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/designer/item")
public class DesignerItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Page page, HttpSession session) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        page.setRows(itemService.selectRows());
        page.setPath("/admin/item/page");
        Object designerId = session.getAttribute("designerId");
        List<Item> list = itemService.findPageByDesignerId(page.getOffset(), page.getLimit(),Integer.parseInt(designerId.toString()));
        model.addAttribute("items", list);
        return "designer/item";
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/add/page")
    public String addPage(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        return "designer/additem1";
    }

    /**
     * 新增作品
     * @param model
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(Model model, Item item, Designer designer,HttpSession session) {
        Object designerId = session.getAttribute("designerId");
        //todo 目前是测试
        item.setDesignerId(Integer.parseInt(designerId.toString()));
        Item item1 = BeanUtil.initClassInfo(Item.class);
        item1.setDesignerId(item.getDesignerId());
        item1.setName(item.getName());
        item1.setDescribe(item.getDescribe());
        itemService.insert(item1);
        return ShareUtil.getJSONString(0);
    }

}
