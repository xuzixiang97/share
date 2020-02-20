package com.xuzi.share.service;



import com.xuzi.share.entity.OrderItems;
import com.xuzi.share.entity.response.OrderItemDetailResponse;

import java.util.List;

public interface OrderItemsService {
    int deleteById(Integer id);

    OrderItems insert(OrderItems record);

    OrderItems selectById(Integer id);

    int updateById(OrderItems record);


    /**
     * 根据订单号查询订单详情 orderId
     * @return
     */
    List<OrderItems> selectByOrderId(OrderItems record);

    /**
     * 根据订单号查询订单详情 orderId(超详细信息)
     * @return
     */
    List<OrderItemDetailResponse> selectDetailByOrderId(OrderItems record);
}