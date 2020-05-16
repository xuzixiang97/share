package com.xuzi.share.dao;

import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Item record);

    List<Item> selectAll();

    List<Item> findPage(@Param("offset") int offset, @Param("limit") int limit);

    List<Item> findPageByCategoryId(@Param("offset") int offset, @Param("limit") int limit,@Param("categoryIds") String categoryIds);

    int selectRows();

    int selectRowsByCategoryId(String categoryIds);

    List<Item> findPageByDesignerId(@Param("offset") int offset, @Param("limit") int limit,@Param("designerId") int designerId);

    int selectRowsByDesignerId();

    List<Item> selectByCategoryId(Integer categoryIds);

    List<Item> selectByDesignerId(Integer designerId);

    Item selectById(Integer id);

    List<Item> selectByStyleId(String styleIds);


    List<Item> selectByKey(@Param("offset") int offset, @Param("limit") int limit,@Param("keyword") String keyword);

    int selectRowsByKey(String keyword);

    List<Item> selectByCondition(Item item);
}