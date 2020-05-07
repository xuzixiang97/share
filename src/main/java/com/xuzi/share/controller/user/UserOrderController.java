package com.xuzi.share.controller.user;

import com.alibaba.fastjson.JSON;
import com.xuzi.share.constant.OrderStatus;
import com.xuzi.share.constant.OrderType;
import com.xuzi.share.entity.*;
import com.xuzi.share.service.*;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/order")
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    /**
     * 商品详情页下单
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Integer itemId, HttpSession session) {
        //TODO 暂时默认授权使用
        Integer paytype = 1;

        Item item = itemService.selectById(itemId);

        //生成订单信息
        Order order = new Order();
        if(paytype == 1){
            order.setAmount(item.getAuthorizeUnitprice());
        }
        order.setType(OrderType.COMMON_ORDER);
        order.setCreateTime(System.currentTimeMillis());
        order.setDesignerId(item.getDesignerId());
        order.setEarnest(0);
        order.setEndTime(0L);
        order.setStatus(OrderStatus.INIT);
        order.setUpdateTime(System.currentTimeMillis());
        order.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        Order insert = orderService.insert(order);

        //生成订单项信息
        OrderItems orderItems = new OrderItems();
        orderItems.setBuyType(paytype);
        orderItems.setEneityType(1);
        orderItems.setEneityId(itemId);
        orderItems.setCreateTime(System.currentTimeMillis());
        orderItems.setExtendedField("");
        orderItems.setOrderId(insert.getId());
        orderItems.setQuantity(1);
        orderItems.setUnitprice(item.getAuthorizeUnitprice());
        orderItems.setUpdateTime(System.currentTimeMillis());
        orderItemsService.insert(orderItems);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        model.addAttribute("items", itemList);
        model.addAttribute("order", order);
        model.addAttribute("amount", item.getAuthorizeUnitprice());
        return  "user/order";
    }

    /**
     * 购物车下单
     * @param model
     * @return
     */
    @RequestMapping("/cartpage")
    @ResponseBody
    @Transactional
    public String cartpage(Model model, String cartIds, HttpSession session) {
        String[] split = cartIds.split(",");
        List<Integer> cartIdList = new ArrayList<>();
        for (String s : split) {
            cartIdList.add(Integer.parseInt(s));
        }
        //生成订单信息
        Integer paytype = 1;
        Order order = new Order();
        if(paytype == 1){
            order.setAmount(0);
        }
        order.setType(OrderType.COMMON_ORDER);
        order.setCreateTime(System.currentTimeMillis());
        order.setDesignerId(0);
        order.setEarnest(0);
        order.setEndTime(0L);
        order.setStatus(OrderStatus.INIT);
        order.setUpdateTime(System.currentTimeMillis());
        order.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        Order insert = orderService.insert(order);
        List<Item> itemList = new ArrayList<>();
        for (Integer cartId : cartIdList) {
            Cart cart = cartService.selectById(cartId);
            cartService.deleteById(cart.getId());
            Item item = itemService.selectById(cart.getItemId());
            itemList.add(item);
            //生成订单项信息
            OrderItems orderItems = new OrderItems();
            orderItems.setBuyType(paytype);
            orderItems.setEneityType(1);
            orderItems.setEneityId(cart.getItemId());
            orderItems.setCreateTime(System.currentTimeMillis());
            orderItems.setExtendedField("");
            orderItems.setOrderId(insert.getId());
            orderItems.setQuantity(1);
            orderItems.setUnitprice(item.getAuthorizeUnitprice());
            orderItems.setUpdateTime(System.currentTimeMillis());
            orderItemsService.insert(orderItems);
            insert.setAmount(insert.getAmount() + item.getAuthorizeUnitprice());
            insert.setDesignerId(item.getDesignerId());
        }

        orderService.updateById(insert);
        //TODO 暂时默认授权使用
        model.addAttribute("items", itemList);
        model.addAttribute("order", insert);
        model.addAttribute("amount", order.getAmount());

        return  ShareUtil.getJSONString(0,insert.getId().toString());
    }


    /**
     * 支付完成页面
     * @param model
     * @return
     */
    @RequestMapping("/pay/finish")
    public String payPage(Model model, HttpSession session){
        Object userId = session.getAttribute("userId");
        //为你推荐商品
        List<Item> items = itemService.selectByStyleId("原创上衣");//原创上衣
        model.addAttribute("items",items);
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        return "user/ok";
    }

    /**
     * 根据订单id跳转订单页面
     * @param model
     * @return
     */
    @RequestMapping("/orderidpage")
    public String orderidpage(Model model, String orderId, HttpSession session) {
        Order order = orderService.selectById(Integer.parseInt(orderId));
        OrderItems orderItems =new OrderItems();
        orderItems.setOrderId(order.getId());
        List<OrderItems> orderItemsList = orderItemsService.selectByOrderId(orderItems);
        List<Item> itemList = new ArrayList<>();
        for (OrderItems items : orderItemsList) {
           itemList.add(itemService.selectById(items.getEneityId()));
        }
        model.addAttribute("items", itemList);
        model.addAttribute("order", order);
        model.addAttribute("amount", order.getAmount());
        return  "user/order";
    }


    /**
     * 商品详情页下单
     * @param model
     * @return
     */
    @RequestMapping("/bidding")
    public String bidding(Model model, Integer designerId,Integer amount, HttpSession session) {
        //TODO 暂时默认授权使用
        Integer paytype = 1;

        //生成订单信息
        Order order = new Order();
        if(paytype == 1){
            order.setAmount(100);
        }
        order.setType(OrderType.BIDDING_ORDER);
        order.setCreateTime(System.currentTimeMillis());
        order.setDesignerId(designerId);
        order.setEarnest(0);
        order.setEndTime(0L);
        order.setStatus(OrderStatus.INIT);
        order.setUpdateTime(System.currentTimeMillis());
        order.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        Order insert = orderService.insert(order);
        model.addAttribute("order", order);
        model.addAttribute("amount", 100);
        return  "user/bidorder";
    }


    /**
     * 申请退款
     * @param model
     * @return
     */
    @RequestMapping("/tuikuan")
    @ResponseBody
    public String tuikuan(Model model, HttpSession session,Order order,Integer amount,String reason){
        order.setRefundStatus(1);
        order.setRefundAmount(amount);
        order.setRefundReason(reason);
        order.setStatus(OrderStatus.REFUNDING);
        orderService.updateById(order);
        return ShareUtil.getJSONString(0);
    }



}
