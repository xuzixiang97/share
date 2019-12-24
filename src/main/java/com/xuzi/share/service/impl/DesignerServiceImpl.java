package com.xuzi.share.service.impl;

import com.xuzi.share.dao.DesignerMapper;
import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.DesignerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class DesignerServiceImpl implements DesignerService {
    @Autowired
    private DesignerMapper designerMapper;

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        Admin admin = designerMapper.findByUserName(username);
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

    @Override
    public Map<String, Object> register(Designer designer) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (designer == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(designer.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(designer.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }

        //重复值处理
        if (designerMapper.findByUserName(designer.getUsername()) != null) {
            map.put("usernameMsg", "用户名已被注册");
        }
        designer.setUsername(designer.getUsername());
        designer.setPassword(designer.getPassword());

        // 注册用户
        designerMapper.insert(designer);
        return map;
    }
}
