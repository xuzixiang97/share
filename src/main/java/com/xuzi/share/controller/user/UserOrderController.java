package com.xuzi.share.controller.user;

import com.xuzi.share.constant.OrderStatus;
import com.xuzi.share.constant.OrderType;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Order;
import com.xuzi.share.entity.OrderItems;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.OrderItemsService;
import com.xuzi.share.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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


        model.addAttribute("item", item);
        model.addAttribute("order", order);
        return  "user/order";
    }

    /**
     * 支付完成页面
     * @param model
     * @return
     */
    @RequestMapping("/pay/finish")
    public String payPage(Model model){
        //为你推荐商品
        List<Item> items = itemService.selectByCategoryId(303);
        model.addAttribute("items",items);
        return "user/ok";
    }



}
