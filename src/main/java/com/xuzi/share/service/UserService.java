package com.xuzi.share.service;

import com.xuzi.share.dao.AdminMapper;
import com.xuzi.share.dao.UserMapper;
import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        List<User> users = userMapper.selectAll();
        return users;
    }

    //查询讨论贴
    public List<User> findDiscussPost(int offset, int limit){
        List<User> userList = userMapper.findUserPage(offset, limit);
        return userList;
    }

    //查询帖子数量（用于分页）
    public int selectRows(){
        return  userMapper.selectRows();
    }

}
