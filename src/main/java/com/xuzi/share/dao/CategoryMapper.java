package com.xuzi.share.dao;

import com.xuzi.share.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Category record);

    List<Category> selectbyCondition(Category category);
}