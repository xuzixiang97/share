package com.xuzi.share.dao;

import com.xuzi.share.entity.Examine;
import com.xuzi.share.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Examine record);

    Examine selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Examine record);

    List<Examine> selectAll();

    List<Examine> findPage(int offset, int limit);

    int selectRows();
}