package com.xuzi.share.controller.admin;


import com.alibaba.fastjson.JSON;
import com.xuzi.share.constant.OrderType;
import com.xuzi.share.constant.ReviewStatus;
import com.xuzi.share.entity.*;
import com.xuzi.share.service.BiddingCustomService;
import com.xuzi.share.service.DesignerService;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.OrderService;
import com.xuzi.share.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/data")
public class AdmDataController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private DesignerService designerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BiddingCustomService biddingCustomService;
    @Autowired
    private ItemService itemService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public String userdata(Model model) {
        List<User> userPage = userService.findUserPage(0, 10000000);
        String s = JSON.toJSONString(userPage);
        return s;
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/designer")
    @ResponseBody
    public String designerdata(Model model) {
        List<Designer> page = designerService.findPage(0, 100000);
        String s = JSON.toJSONString(page);
        return s;
    }


    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/review")
    @ResponseBody
    public String review(Model model) {
        List<Designer> byStatus = designerService.findByStatus(ReviewStatus.reviewing);
        String s = JSON.toJSONString(byStatus);
        return s;
    }

    /**
     * 普通订单数据
     * @param model
     * @return
     */
    @RequestMapping("/common")
    @ResponseBody
    public String commonorderdata(Model model, HttpSession session, Order order) {
        order.setType(OrderType.COMMON_ORDER);
        List<Order> orders = orderService.selectbyCondition(order);
        for (Order order1 : orders) {
            //设置订单编号
            order1.setOrderNo("#P00" + order1.getId());
            //设置用户名名称
            User user = userService.findById(order1.getUserId());
            order1.setUsername(user.getUsername());
            //设置订单时间
            order1.setTime(new Date(order1.getCreateTime()).toString());
        }
        String s = JSON.toJSONString(orders);
        return s;
    }


    /**
     * 定制订单数据
     * @param model
     * @return
     */
    @RequestMapping("/bidding")
    @ResponseBody
    public String biddingorderdata(Model model,HttpSession session,Order order) {
        order.setType(OrderType.BIDDING_ORDER);
        List<Order> orders = orderService.selectbyCondition(order);
        for (Order order1 : orders) {
            //设置订单编号
            order1.setOrderNo("#J00" + order1.getId());
            //设置用户名名称
            User user = userService.findById(order1.getUserId());
            order1.setUsername(user.getUsername());
            //设置订单时间
            order1.setTime(new Date(order1.getCreateTime()).toString());
            //设置订单内容
            if(order1.getBiddingId()!=null){
                BiddingCustom byId = biddingCustomService.findById(order1.getBiddingId());
                order1.setDescribe(byId.getDescribe());
                order1.setEndline(byId.getEndTime());
            }
        }
        String s = JSON.toJSONString(orders);
        return s;
    }

    /**
     * 作品数据
     * @param model
     * @return
     */
    @RequestMapping("/item")
    @ResponseBody
    public String itemdata(Model model,HttpSession session) {
        List<Item> items = itemService.selectByKey(1,10000,"");
        String s = JSON.toJSONString(items);
        return s;
    }


}
