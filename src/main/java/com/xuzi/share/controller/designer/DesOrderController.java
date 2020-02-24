package com.xuzi.share.controller.designer;


import com.xuzi.share.entity.Order;
import com.xuzi.share.entity.Page;
import com.xuzi.share.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/designer/order")
public class DesOrderController {
    @Autowired
    private OrderService orderService;

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



}
