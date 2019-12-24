package com.xuzi.share.service.impl;

import com.xuzi.share.dao.AdminMapper;
import com.xuzi.share.entity.Admin;
import com.xuzi.share.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        Admin admin = adminMapper.findByUserName(username);
        //校验管理员账号是否存在
        if (admin == null) {
            map.put("usernameMsg", "用户名不存在");
            return map;
        }
        if (!admin.getPassword().equals(password)) {
            map.put("passwordMsg", "密码错误");
            return map;
        }
        map.put("SuccessMessage", "登录成功");
        return map;
    }

}
