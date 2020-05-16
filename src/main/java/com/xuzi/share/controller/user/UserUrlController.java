package com.xuzi.share.controller.user;


import com.alibaba.fastjson.JSON;
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
import java.util.Date;
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
        for (Order order1 : orders) {
            //设置订单编号
            order1.setOrderNo("#P00" + order1.getId());
            //设置订单时间
            order1.setTime(new Date(order1.getCreateTime()).toString());
            //设置订单内容
            if(order1.getBiddingId()!=null){
                BiddingCustom byId = biddingCustomService.findById(order1.getBiddingId());
                order1.setDescribe(byId.getDescribe());
                order1.setEndline(byId.getEndTime());
            }
        }
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
        for (Order order1 : orders) {
            //设置订单编号
            order1.setOrderNo("#J00" + order1.getId());
            //设置订单时间
            order1.setTime(new Date(order1.getCreateTime()).toString());
            //设置订单内容
            if(order1.getBiddingId()!=null){
                BiddingCustom byId = biddingCustomService.findById(order1.getBiddingId());
                order1.setDescribe(byId.getDescribe());
                order1.setEndline(byId.getEndTime());
            }
        }
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
                List<BidDesigner> designerList1 = JSON.parseArray(biddingCustom.getDesignerIds(), BidDesigner.class);
                ArrayList<Designer> list = new ArrayList<>();
                for (BidDesigner s : designerList1) {
                    Designer designer = designerService.selectById(s.getDesignerId());
                    s.setDesignerName(designer.getUsername());
                    list.add(designer);
                }
                biddingCustom.setBidDesigners(designerList1);
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
    @RequestMapping("/fenlei")
    public String search(Model model, HttpSession session,String categoryId,Page page) {
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        page.setRows(itemService.selectRowsByCategoryId(categoryId));
        page.setPath("/user/fenlei?categoryId="+categoryId);
        List<Item> items = itemService.findPageByCategoryId(page.getOffset(), page.getLimit(), categoryId);
        model.addAttribute("items", items);

        return "user/fenlei";
    }

    /**
     * 跳转搜索结果页面
     * @param model
     * @return
     */
    @RequestMapping("/search/page1")
    public String searchres(Model model, HttpSession session,String keyword,Page page,Item item) {
        page.setRows(itemService.selectRowsByKey(keyword));
        page.setPath("/user/search/page1?keyword="+keyword);
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);
        model.addAttribute("keyword", keyword);
        List<Item> items = itemService.selectByKey(page.getOffset(), page.getLimit(), keyword);
        ArrayList<Item> list = new ArrayList<>();
        List<Item> items1 = itemService.selectByCondition(item);
        list.addAll(items1);
        List<Integer> ids  = new ArrayList<>();
        List<Item> res  = new ArrayList<>();
        for (Item item1 : items) {
            for (Item item2 : items1) {
                if(item1.getId()==item2.getId()){
                    ids.add(item1.getId());
                }
            }
        }

        for (Integer id : ids) {
            for (Item item1 : items) {
                if(item1.getId()==id){
                    res.add(item1);
                }
            }
        }

        model.addAttribute("items", res);
        return "user/searchres";
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
