package com.xuzi.share.dao;

import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Item record);

    List<Item> selectAll();

    List<Item> findPage(int offset, int limit);

    int selectRows();
}