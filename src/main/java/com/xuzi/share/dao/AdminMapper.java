package com.xuzi.share.dao;

import com.xuzi.share.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Admin record);

    Admin findByUserName(String username);
}