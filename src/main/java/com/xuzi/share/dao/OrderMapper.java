package com.xuzi.share.dao;

import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Order record);


    List<Order> selectbyCondition(Order record);

    int selectRowsByType(int type);

    List<Order> findPageByType(@Param("offset") int offset, @Param("limit") int limit, @Param("type") int type);
}