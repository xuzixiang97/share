package com.xuzi.share.dao;

import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    List<User> selectAll();

    List<User> findUserPage(int offset, int limit);

    int selectRows();

    User findByUserName(String username);

    User selectByName(String userName);
}