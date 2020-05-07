package com.xuzi.share.controller.user;

import com.xuzi.share.entity.BiddingCustom;
import com.xuzi.share.entity.Item;
import com.xuzi.share.service.BiddingCustomService;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/bidding")
public class UserBiddingCustomController {

    @Autowired
    private BiddingCustomService biddingCustomService;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(Model model, BiddingCustom custom, HttpSession session) {
        Object userId = session.getAttribute("userId");
        custom.setUserId(Integer.parseInt(userId.toString()));
        custom.setCreateTime(System.currentTimeMillis());
        custom.setUpdateTime(System.currentTimeMillis());
        custom.setEarnest((long) (custom.getAmount() * 0.3));
        custom.setStatus(1);
        biddingCustomService.insert(custom);
        return ShareUtil.getJSONString(0);
    }



}
