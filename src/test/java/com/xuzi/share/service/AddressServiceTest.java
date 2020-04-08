package com.xuzi.share.service;

import com.xuzi.share.ShareApplication;
import com.xuzi.share.constant.UserStatus;
import com.xuzi.share.constant.UserType;
import com.xuzi.share.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ShareApplication.class)
public class AddressServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void insertUser() {
        for(int i = 0; i < 100; i++){
            User user = new User();
            user.setUsername("测试用户" + i);
            user.setNickName("测试用户" + i);
            user.setHeaderUrl("http://q8gozduwf.bkt.clouddn.com/user.jpg");
            user.setPassword("123456");
            user.setEmail("test"+i+"@163.com");
            user.setType(UserType.COMMON);
            user.setStatus(UserStatus.NORMAL);
            userService.register(user);
        }
    }

    @Test
    public void insertCategory() {

    }
}