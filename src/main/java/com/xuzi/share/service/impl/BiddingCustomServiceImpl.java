package com.xuzi.share.service.impl;

import com.xuzi.share.dao.BiddingCustomMapper;
import com.xuzi.share.entity.BiddingCustom;
import com.xuzi.share.service.BiddingCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiddingCustomServiceImpl implements BiddingCustomService {
    @Autowired
    private BiddingCustomMapper biddingCustomMapper;

    @Override
    public List<BiddingCustom> findPage(int offset, int limit) {
        return biddingCustomMapper.findPage(offset,limit);
    }

    @Override
    public List<BiddingCustom> findAll() {
        return biddingCustomMapper.findAll();
    }

    @Override
    public int selectRows() {
        return biddingCustomMapper.selectRows();
    }

    @Override
    public BiddingCustom insert(BiddingCustom biddingCustom) {
        biddingCustomMapper.insert(biddingCustom);
        return biddingCustom ;
    }

    @Override
    public int update(BiddingCustom custom) {
        return biddingCustomMapper.updateByPrimaryKeySelective(custom);
    }

    @Override
    public BiddingCustom findById(Integer id) {
        return biddingCustomMapper.findById(id);
    }

    @Override
    public List<BiddingCustom> findByUserId(Integer userId) {
        return biddingCustomMapper.findByUserId(userId);
    }
}
