package com.xuzi.share.service.impl;

import com.xuzi.share.dao.CartMapper;
import com.xuzi.share.dao.ItemMapper;
import com.xuzi.share.entity.Cart;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.response.UserCartResponse;
import com.xuzi.share.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public int deleteById(Integer id) {
        return cartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Cart insert(Cart record) {
        cartMapper.insert(record);
        return record;
    }

    @Override
    public Cart selectById(Integer id) {
        return cartMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Cart record) {
        return cartMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserCartResponse> selectByUserId(Integer userId) {
        List<UserCartResponse> userCartResponses =new ArrayList<>();
        List<Cart> carts = cartMapper.selectByUserId(userId);
        for (Cart cart : carts) {
            UserCartResponse userCartResponse = new UserCartResponse();
            userCartResponse.setId(cart.getId());
            userCartResponse.setUserId(cart.getUserId());
            userCartResponse.setItemId(cart.getItemId());
            // TODO 目前默认授权使用
            userCartResponse.setType(1);
            Item item = itemMapper.selectByPrimaryKey(cart.getItemId());
            userCartResponse.setName(item.getName());
            userCartResponse.setDescribe(item.getDescribe());
            userCartResponse.setUnitprice(item.getAuthorizeUnitprice());
            userCartResponse.setQuantity(1);
            userCartResponse.setCategoryId(item.getCategoryIds());
            userCartResponse.setImg(item.getShowImg());
            userCartResponses.add(userCartResponse);
        }
        return userCartResponses;
    }
}
