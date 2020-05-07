package com.xuzi.share.service;

import com.xuzi.share.entity.BiddingCustom;
import com.xuzi.share.entity.Item;

import java.util.List;

public interface BiddingCustomService {

    /**
     * 查询作品（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
    List<BiddingCustom> findPage(int offset, int limit);

    /**
     * 查询作品数量（用于分页，管理员后台）
     * @return
     */
    int selectRows();

    /**
     * 新增一条商品
     * @param biddingCustom
     * @return
     */
    BiddingCustom insert(BiddingCustom biddingCustom);

    int update(BiddingCustom custom);

    BiddingCustom findById(Integer id);

    List<BiddingCustom> findByUserId(Integer userId);
}
