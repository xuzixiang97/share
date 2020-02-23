package com.xuzi.share.service;

import com.xuzi.share.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 查询用户列表，分页显示（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
    List<User> findUserPage(int offset, int limit);

    /**
     * 查询用户数量（用于分页）
     * @return
     */
    int selectRows();

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> login(String username, String password);

    /**
     * 注册用户
     * @param user
     * @return
     */
    Map<String, Object> register(User user);

    User findById(Integer id);
}
