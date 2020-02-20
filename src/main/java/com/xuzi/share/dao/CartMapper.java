package com.xuzi.share.dao;

import com.xuzi.share.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Cart record);

    List<Cart> selectByUserId(Integer userId);
}