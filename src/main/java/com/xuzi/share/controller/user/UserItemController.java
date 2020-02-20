package com.xuzi.share.controller.user;

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
@RequestMapping("/user/item")
public class UserItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/detail")
    public String getPage(Model model,Integer id) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        Item itemDetail = itemService.selectById(id);
        model.addAttribute("item",itemDetail);
        return  "user/proDetail";
    }



}
