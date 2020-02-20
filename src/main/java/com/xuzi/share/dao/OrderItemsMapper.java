package com.xuzi.share.dao;

import com.xuzi.share.entity.OrderItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItems record);

    int insertSelective(OrderItems record);

    OrderItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(OrderItems record);

    List<OrderItems> selectByOrderId(OrderItems record);
}