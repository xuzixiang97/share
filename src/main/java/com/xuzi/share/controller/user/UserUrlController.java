package com.xuzi.share.controller.user;


import com.xuzi.share.constant.OrderType;
import com.xuzi.share.entity.*;
import com.xuzi.share.entity.response.OrderDetail;
import com.xuzi.share.entity.response.OrderItemDetailResponse;
import com.xuzi.share.service.*;
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

    @Autowired
    private BiddingCustomService biddingCustomService;

    @Autowired
    private DesignerService designerService;
    @Autowired
    private FileUtil fileUtil;

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
    @RequestMapping("/myorderq2/page")
    public String myorderq2(Model model, HttpSession session) throws InvocationTargetException, IllegalAccessException {
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
        return "user/myorderq2";
    }


    /**
     * 跳转个人页面
     * @param model
     * @return
     */
    @RequestMapping("/index/page")
    public String index(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        List<Item> items1 = itemService.selectByStyleId("原创上衣");//原创上衣
        List<Item> items2 = itemService.selectByStyleId("时尚群套");//时尚套裙
        List<Item> items3 = itemService.selectByStyleId("春季新品");//春季新品
        model.addAttribute("items1", items1);
        model.addAttribute("items2", items2);
        model.addAttribute("items3", items3);
        return "user/index";
    }

    /**
     * 跳转设计师个人主页
     * @param model
     * @return
     */
    @RequestMapping("/designer/page")
    public String designer1(Model model, HttpSession session,Integer designerId) {
        Designer designer = designerService.selectById(designerId);
        List<Item> items = itemService.selectByDesignerId(designerId);
        model.addAttribute("designer", designer);
        model.addAttribute("items", items);
        return "user/designerindex";
    }

    /**
     * 跳转分类页面
     * @param model
     * @return
     */
    @RequestMapping("/fenlei/page")
    public String feblei(Model model, HttpSession session,String designerId) {
        Item item = new Item();
        List<Item> items = itemService.selectByCondition(item);
        model.addAttribute("items", items);
        return "user/flowerDer";
    }

    /**
     * 跳转定制页面
     * @param model
     * @return
     */
    @RequestMapping("/dingzhi/page")
    public String dingzhi(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        List<Item> items1 = itemService.selectByStyleId("原创上衣");//原创上衣
        List<Item> items2 = itemService.selectByStyleId("时尚群套");//时尚套裙
        List<Item> items3 = itemService.selectByStyleId("春季新品");//春季新品
        model.addAttribute("items1", items1);
        model.addAttribute("items2", items2);
        model.addAttribute("items3", items3);

        List<BiddingCustom> biddingCustomList = biddingCustomService.findByUserId(Integer.parseInt(userId.toString()));

        for (BiddingCustom biddingCustom : biddingCustomList) {
            if (biddingCustom.getDesignerIds()!=null){
                String[] split = biddingCustom.getDesignerIds().split(",");
                ArrayList<Designer> list = new ArrayList<>();
                for (String s : split) {
                    Designer designer = designerService.selectById(Integer.parseInt(s));
                    list.add(designer);
                }
                biddingCustom.setDesignerIdList(list);
            }
        }

        model.addAttribute("biddingCustomList", biddingCustomList);
        return "user/dingzhi1";
    }


    /**
     * 跳转定制页面
     * @param model
     * @return
     */
    @RequestMapping("/dingzhi2/page")
    public String dingzhi2(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        List<Item> items1 = itemService.selectByStyleId("原创上衣");//原创上衣
        List<Item> items2 = itemService.selectByStyleId("时尚群套");//时尚套裙
        List<Item> items3 = itemService.selectByStyleId("春季新品");//春季新品
        model.addAttribute("items1", items1);
        model.addAttribute("items2", items2);
        model.addAttribute("items3", items3);
        return "user/dingzhi2";
    }

    /**
     * 跳转搜索结果页面
     * @param model
     * @return
     */
    @RequestMapping("/search/page")
    public String search(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        List<Item> items1 = itemService.selectByStyleId("原创上衣");//原创上衣
        List<Item> items2 = itemService.selectByStyleId("时尚群套");//时尚套裙
        List<Item> items3 = itemService.selectByStyleId("春季新品");//春季新品
        model.addAttribute("items1", items1);
        model.addAttribute("items2", items2);
        model.addAttribute("items3", items3);
        return "user/search";
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

    /**
     * 跳转个人信息设置
     *
     * @param model
     * @return
     */
    @RequestMapping("/setting")
    public String getSettingPage(Model model) {
        fileUtil.fileup(model);
        return "user/setting";
    }

    /**
     * 跳转个人订单页面
     * @param model
     * @return
     */
    @RequestMapping("/myorderxq/page")
    public String myorderxq(Model model, HttpSession session, Integer orderId) throws InvocationTargetException, IllegalAccessException {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        Order order = orderService.selectById(orderId);
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);
        List<OrderItemDetailResponse> orderItemDetailResponses = orderItemsService.selectDetailByOrderId(orderItems);
        Integer itemId = orderItemDetailResponses.get(0).getItemId();
        Item item = itemService.selectById(itemId);
        model.addAttribute("item", item);
        model.addAttribute("order", order);
        return "user/myorderxq";
    }


}
