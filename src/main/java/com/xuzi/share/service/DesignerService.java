package com.xuzi.share.service;

import com.xuzi.share.entity.Designer;

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

}
