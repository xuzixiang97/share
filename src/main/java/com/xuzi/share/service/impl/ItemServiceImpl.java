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
    public int selectRowsByKey(String keyword) {
        return itemMapper.selectRowsByKey(keyword);
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

    @Override
    public List<Item> selectByCategoryId(Integer categoryIds) {
        return itemMapper.selectByCategoryId(categoryIds);
    }

    @Override
    public List<Item> selectByDesignerId(Integer designerId) {
        return itemMapper.selectByDesignerId(designerId);
    }

    @Override
    public List<Item> selectByStyleId(String styleIds) {
        return itemMapper.selectByStyleId(styleIds);
    }

    @Override
    public Item selectById(Integer id) {
        return itemMapper.selectById(id);
    }

    @Override
    public List<Item> selectByCondition(Item item) {
        return itemMapper.selectByCondition(item);
    }

    @Override
    public int updateByCondition(Item item) {
        return itemMapper.updateByPrimaryKey(item);
    }

    @Override
    public List<Item> selectByKey(int offset, int limit, String keyword) {
        return itemMapper.selectByKey(offset,limit,keyword);
    }

}
