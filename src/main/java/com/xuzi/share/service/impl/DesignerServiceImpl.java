package com.xuzi.share.service.impl;

import com.xuzi.share.constant.DeafultHraderEnum;
import com.xuzi.share.constant.DesignerStatusEnum;
import com.xuzi.share.dao.DesignerMapper;
import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.DesignerService;
import com.xuzi.share.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DesignerServiceImpl implements DesignerService {
    @Autowired
    private DesignerMapper designerMapper;
    @Autowired
    HostHolder hostHolder;

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        Designer designer = designerMapper.findByUserName(username);
        //校验管理员账号是否存在
        if (designer == null) {
            map.put("usernameMsg", "用户名不存在");
            return map;
        }
        if (!designer.getPassword().equals(password)) {
            map.put("passwordMsg", "密码错误");
            return map;
        }
        map.put("SuccessMessage", "登录成功");
        map.put("designerId", designer.getId());
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
            return map;
        }
        designer.setUsername(designer.getUsername());
        designer.setPassword(designer.getPassword());
        //设置状态为未审核
        designer.setStatus(DesignerStatusEnum.UNEXAMINE.getStatus());
        //设置默认头像
        designer.setHeaderUrl(DeafultHraderEnum.DSEIGNER.getUrl());
        // 注册用户
        designerMapper.insert(designer);
        map.put("SuccessMessage", "登录成功");

        return map;
    }

    @Override
    public Boolean updateByCondition(Designer designer) {
        int i = designerMapper.updateByPrimaryKey(designer);
        return (i>0);
    }

    public List<Designer> findPage(int offset, int limit){
        List<Designer> userList = designerMapper.findPage(offset, limit);
        return userList;
    }

    public int selectRows(){
        return  designerMapper.selectRows();
    }

    @Override
    public List<Designer> findByStatus(Integer status) {
        return designerMapper.findByStatus(status);
    }
}
