package com.xuzi.share.service.impl;

import com.xuzi.share.dao.ItemMapper;
import com.xuzi.share.entity.Category;
import com.xuzi.share.entity.Item;
import com.xuzi.share.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> findPage(int offset, int limit) {
        return itemMapper.findPage(offset,limit);
    }

    @Override
    public int selectRows() {
        return itemMapper.selectRows();
    }

    @Override
    public List<Item> findPageByDesignerId(int offset, int limit, Integer designerId) {
        return itemMapper.findPageByDesignerId(offset,limit,designerId);
    }

    @Override
    public int selectRowsByDesignerId(Integer designerId) {
        return itemMapper.selectRows();
    }

    @Override
    public Item insert(Item item) {
        itemMapper.insert(item);
        return item;
    }

}
