package com.xuzi.share.service.impl;

import com.xuzi.share.dao.OrderMapper;
import com.xuzi.share.entity.Order;
import com.xuzi.share.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int deleteById(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Order insert(Order record) {
        orderMapper.insert(record);
        return record;
    }

    @Override
    public Order selectById(Integer id) {

        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<Order> selectbyCondition(Order record) {
        return orderMapper.selectbyCondition(record);
    }
}
