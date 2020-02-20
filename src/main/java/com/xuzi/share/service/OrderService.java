package com.xuzi.share.service;


import com.xuzi.share.entity.Category;
import com.xuzi.share.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 根据id删除一条记录
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 新增一条
     * @param order
     * @return
     */
    Order insert(Order order);

    /**
     * 根据id查询一条订单
     * @param id
     * @return
     */
    Order selectById(Integer id);

    /**
     * 修改信息
     * @param category
     * @return
     */
    int updateById(Order category);

    /**
     * 条件查询订单信息
     * @param order
     * @return
     */
    List<Order> selectbyCondition(Order order);

}
