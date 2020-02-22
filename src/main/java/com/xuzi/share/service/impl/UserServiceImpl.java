package com.xuzi.share.service.impl;

import com.xuzi.share.dao.AdminMapper;
import com.xuzi.share.dao.UserMapper;
import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findUserPage(int offset, int limit){
        List<User> userList = userMapper.findUserPage(offset, limit);
        return userList;
    }

    public int selectRows(){
        return  userMapper.selectRows();
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.findByUserName(username);
        //校验管理员账号是否存在
        if (user == null) {
            map.put("usernameMsg", "用户名不存在");
            return map;
        }
        if (!user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码错误");
            return map;
        }
        map.put("SuccessMessage", "登录成功");
        map.put("userId", user.getId());
        map.put("user", user);
        return map;
    }
    @Override
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }

        //重复值处理
        if (userMapper.findByUserName(user.getUsername()) != null) {
            map.put("usernameMsg", "用户名已被注册");
        }
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());

        // 注册用户
        userMapper.insert(user);
        return map;
    }

}
