package com.xuzi.share.service;

import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.User;

import java.util.List;
import java.util.Map;

public interface DesignerService {

    /**
     * 设计师登录
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> login(String username, String password);

    /**
     * 注册设计师
     * @param designer
     * @return
     */
    Map<String, Object> register(Designer designer);

    /**
     * 修改设计师信息
     * @param designer
     * @return
     */
    Boolean updateByCondition(Designer designer);


    /**
     * 查询用户列表，分页显示（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
    List<Designer> findPage(int offset, int limit);

    /**
     * 查询用户数量（用于分页）
     * @return
     */
    int selectRows();

    List<Designer> findByStatus(Integer status);

}
