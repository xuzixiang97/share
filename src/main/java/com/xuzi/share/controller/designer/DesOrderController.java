package com.xuzi.share.controller.designer;


import com.alibaba.fastjson.JSON;
import com.xuzi.share.constant.OrderStatus;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Order;
import com.xuzi.share.entity.Page;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.OrderService;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/designer/order")
public class DesOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Page page , Integer type , HttpSession httpSession) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        page.setRows(orderService.selectRowsByType(type));
        page.setPath("/admin/order/page");
        List<Order> orders = orderService.findPageByType(page.getOffset(), page.getLimit(),type);
        Object designerId = httpSession.getAttribute("designerId");
        for (Order order : orders) {
            if(Integer.parseInt(designerId.toString()) != order.getDesignerId()){
                orders.remove(order);
            }
        }
        model.addAttribute("orders", orders);
        return "admin/order";
    }



    /**
     * 退款数据
     * @param model
     * @return
     */
    @RequestMapping("/refunddata")
    @ResponseBody
    public String refunddata(Model model,HttpSession session) {
        Object designerId = session.getAttribute("designerId");
        Order order = new Order();
        order.setDesignerId(Integer.parseInt(designerId.toString()));
        order.setStatus(OrderStatus.REFUNDING);
        List<Order> orders = orderService.selectbyCondition(order);
        for (Order order1 : orders) {
           order1.setOrderNo("#000" + order1.getId().toString());
        }
        String s = JSON.toJSONString(orders);
        return s;
    }


    /**
     * 同意退款
     * @param model
     * @return
     */
    @RequestMapping("/ok")
    @ResponseBody
    public String ok(Model model,HttpSession session,Order order) {
        order.setStatus(OrderStatus.REFUNDED);
        orderService.updateById(order);
        return ShareUtil.getJSONString(0);
    }


    /**
     * 拒绝退款
     * @param model
     * @return
     */
    @RequestMapping("/no")
    @ResponseBody
    public String no(Model model,HttpSession session,Order order) {
        order.setStatus(OrderStatus.UNREFUND);
        orderService.updateById(order);
        return ShareUtil.getJSONString(0);
    }




}
