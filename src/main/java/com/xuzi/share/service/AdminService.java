package com.xuzi.share.service;
import java.util.Map;

public interface AdminService {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> login(String username, String password);

}
