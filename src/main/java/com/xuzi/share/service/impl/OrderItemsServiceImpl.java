package com.xuzi.share.service.impl;

import com.xuzi.share.dao.ItemMapper;
import com.xuzi.share.dao.OrderItemsMapper;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.OrderItems;
import com.xuzi.share.entity.response.OrderItemDetailResponse;
import com.xuzi.share.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private ItemMapper itemMapper;
    @Override
    public int deleteById(Integer id) {
        return orderItemsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderItems insert(OrderItems record) {
        orderItemsMapper.insert(record);
        return record;
    }

    @Override
    public OrderItems selectById(Integer id) {

        return orderItemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(OrderItems record) {
        return orderItemsMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<OrderItems> selectByOrderId(OrderItems record) {
        return orderItemsMapper.selectByOrderId(record);
    }

    @Override
    public List<OrderItemDetailResponse> selectDetailByOrderId(OrderItems record) {
        List<OrderItemDetailResponse> orderItemDetailResponses = new ArrayList<>();
        List<OrderItems> itemsList = orderItemsMapper.selectByOrderId(record);
        for (OrderItems orderItems : itemsList) {
            OrderItemDetailResponse orderItemDetailResponse =new OrderItemDetailResponse();
            orderItemDetailResponse.setId(orderItems.getId());
            orderItemDetailResponse.setOrderId(orderItems.getOrderId());
            orderItemDetailResponse.setItemId(orderItems.getId());
            orderItemDetailResponse.setUnitprice(orderItems.getUnitprice());
            Item item  = itemMapper.selectByPrimaryKey(orderItems.getOrderId());
            orderItemDetailResponse.setItemName(item.getName());
            orderItemDetailResponse.setImg(item.getShowImg());
            orderItemDetailResponses.add(orderItemDetailResponse);
        }
        return orderItemDetailResponses;
    }
}
