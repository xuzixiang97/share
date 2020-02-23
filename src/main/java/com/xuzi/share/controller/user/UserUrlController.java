package com.xuzi.share.controller.user;


import com.xuzi.share.constant.OrderType;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Order;
import com.xuzi.share.entity.OrderItems;
import com.xuzi.share.entity.User;
import com.xuzi.share.entity.response.OrderDetail;
import com.xuzi.share.entity.response.OrderItemDetailResponse;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.OrderItemsService;
import com.xuzi.share.service.OrderService;
import com.xuzi.share.service.UserService;
import com.xuzi.share.service.impl.UserServiceImpl;
import com.xuzi.share.utils.BeanUtil;
import com.xuzi.share.utils.FileUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserUrlController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderItemsService orderItemsService;

    @RequestMapping("/index")
    public String getLoginPage(Model model) {
        return "designer/index";
    }

    /**
     * 跳转个人中心页面
     * @param model
     * @return
     */
    @RequestMapping("/mygxin/page")
    public String getIndexPage(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        return "user/mygxin";
    }

    /**
     * 跳转个人订单页面
     * @param model
     * @return
     */
    @RequestMapping("/myorderq/page")
    public String myorderq(Model model, HttpSession session) throws InvocationTargetException, IllegalAccessException {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        Order order = new Order();
        order.setUserId(Integer.parseInt(userId.toString()));
        List<Order> orders = orderService.selectbyCondition(order);
        // todo 目前只有普通订单
        Map<Integer,List<Order>> map = orders.stream().collect(Collectors.groupingBy(Order::getType));
        model.addAttribute("user", user);
        model.addAttribute("common_orders", transfer(map.get(OrderType.COMMON_ORDER)));
        model.addAttribute("person_orders", transfer(map.get(OrderType.PERSON_ORDER)));
        model.addAttribute("bidding_order", transfer(map.get(OrderType.BIDDING_ORDER)));
        return "user/myorderq";
    }

    /**
     * 跳转个人订单页面
     * @param model
     * @return
     */
    @RequestMapping("/index/page")
    public String index(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        List<Item> items1 = itemService.selectByCategoryId(301);//原创上衣
        List<Item> items2 = itemService.selectByCategoryId(303);//时尚套裙
        List<Item> items3 = itemService.selectByCategoryId(501);//春季新品
        model.addAttribute("items1", items1);
        model.addAttribute("items2", items2);
        model.addAttribute("items3", items3);
        return "user/index";
    }

    public List<OrderDetail> transfer(List<Order> orderList) throws InvocationTargetException, IllegalAccessException {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        if(orderList != null && orderList.size() >0){
            for (Order order : orderList) {
                OrderDetail orderDetail = new OrderDetail();
                BeanUtils.copyProperties(orderDetail,order);
                OrderItems orderItems = new OrderItems();
                orderItems.setOrderId(order.getId());
                List<OrderItemDetailResponse> orderItemDetailResponses = orderItemsService.selectDetailByOrderId(orderItems);
                orderDetail.setOrderItemList(orderItemDetailResponses);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                orderDetail.setDate(sdf.format(orderDetail.getCreateTime()));
                orderDetailList.add(orderDetail);
            }
        }
        return  orderDetailList;
    }


}
