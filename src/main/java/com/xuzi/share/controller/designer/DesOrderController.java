package com.xuzi.share.controller.designer;


import com.alibaba.fastjson.JSON;
import com.xuzi.share.constant.OrderStatus;
import com.xuzi.share.constant.OrderType;
import com.xuzi.share.entity.*;
import com.xuzi.share.service.BiddingCustomService;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.OrderService;
import com.xuzi.share.service.UserService;
import com.xuzi.share.utils.FileUtil;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/designer/order")
public class DesOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private BiddingCustomService biddingCustomService;

    @Autowired
    private FileUtil fileUtil;

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
            if(order1.getType().equals(OrderType.COMMON_ORDER)){
                order1.setOrderNo("#P00" + order1.getId());
            }
            if(order1.getType().equals(OrderType.BIDDING_ORDER)){
                order1.setOrderNo("#J00" + order1.getId());
            }

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


    /**
     * 拒绝退款
     * @param model
     * @return
     */
    @RequestMapping("/up")
    @ResponseBody
    public String up(Model model,HttpSession session,Order order,String key) {
        String url = fileUtil.getUrl(key);
        order.setResource(url);
        orderService.updateById(order);
        return ShareUtil.getJSONString(0);
    }


    /**
     * 普通订单数据
     * @param model
     * @return
     */
    @RequestMapping("/common/data")
    @ResponseBody
    public String commonorderdata(Model model,HttpSession session,Order order) {
        Object designerId = session.getAttribute("designerId");
        order.setDesignerId(Integer.parseInt(designerId.toString()));
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
    @RequestMapping("/bidding/data")
    @ResponseBody
    public String biddingorderdata(Model model,HttpSession session,Order order) {
        Object designerId = session.getAttribute("designerId");
        order.setDesignerId(Integer.parseInt(designerId.toString()));
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
     * 定制订单数据
     * @param model
     * @return
     */
    @RequestMapping("/bidding/resource")
    @ResponseBody
    public String addResourxe(Model model,HttpSession session,Order order) {

        return ShareUtil.getJSONString(0);
    }

}
