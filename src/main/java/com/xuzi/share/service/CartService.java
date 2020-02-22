package com.xuzi.share.service;

import com.xuzi.share.entity.Cart;
import com.xuzi.share.entity.response.UserCartResponse;

import java.util.List;

public interface CartService {
    int deleteById(Integer id);

    Cart insert(Cart record);

    Cart selectById(Integer id);

    //UserCartResponse selectDetailById(Long id);

    int updateById(Cart record);

    List<UserCartResponse> selectByUserId(Integer userId);
}
