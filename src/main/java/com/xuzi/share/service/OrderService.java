package com.xuzi.share.service;


import com.xuzi.share.entity.Category;
import com.xuzi.share.entity.Designer;
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

    /**
     * 查询用户列表，分页显示（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
     List<Order> findPageByType(int offset, int limit ,int type);


    /**
     * 查询用户数量（用于分页）
     * @return
     */
     int selectRowsByType(int type);

}
