package com.xuzi.share.dao;

import com.xuzi.share.entity.BiddingCustom;
import com.xuzi.share.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BiddingCustomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BiddingCustom record);

    int insertSelective(BiddingCustom record);


    List<BiddingCustom> findPage(@Param("offset") int offset, @Param("limit") int limit);

    List<BiddingCustom> findByUserId(Integer userId);

    List<BiddingCustom> findAll();

    BiddingCustom findById(Integer id);

    int selectRows();

    BiddingCustom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BiddingCustom record);

    int updateByPrimaryKey(BiddingCustom record);
}