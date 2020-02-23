package com.xuzi.share.controller.user;

import com.xuzi.share.constant.OrderStatus;
import com.xuzi.share.constant.OrderType;
import com.xuzi.share.entity.Cart;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Order;
import com.xuzi.share.entity.OrderItems;
import com.xuzi.share.entity.response.UserCartResponse;
import com.xuzi.share.service.CartService;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.OrderItemsService;
import com.xuzi.share.service.OrderService;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/cart")
public class UserCartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsService orderItemsService;

    /**
     * 跳转购物车页面
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, HttpSession session) {
        Object userId = session.getAttribute("userId");
        List<UserCartResponse> userCartResponses = cartService.selectByUserId(Integer.parseInt(userId.toString()));
        model.addAttribute("carts", userCartResponses);
        return  "user/cart";
    }


    /**
     * 添加购物车
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model, Integer itemId, HttpSession session) {
        Object userId = session.getAttribute("userId");
        Cart cart = new Cart();
        cart.setItemId(itemId);
        cart.setUserId(Integer.parseInt(userId.toString()));
        cartService.insert(cart);
        //获取其他购物车商品
        List<UserCartResponse> userCartResponses = cartService.selectByUserId(Integer.parseInt(userId.toString()));
        model.addAttribute("carts", userCartResponses);
        return  "user/cart";
    }

    /**
     * 删除购物车商品
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Model model, Integer id, HttpSession session) {
        Object userId = session.getAttribute("userId");
        cartService.deleteById(id);
        //获取其他购物车商品
        List<UserCartResponse> userCartResponses = cartService.selectByUserId(Integer.parseInt(userId.toString()));
        model.addAttribute("carts", userCartResponses);
        return  "user/cart";
    }

}
