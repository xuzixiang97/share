package com.xuzi.share.service.impl;

import com.xuzi.share.dao.CategoryMapper;
import com.xuzi.share.entity.Category;
import com.xuzi.share.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int deleteById(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category insert(Category category) {
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int updateById(Category category) {
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public List<Category> selectbyCondition(Category category) {
        return categoryMapper.selectbyCondition(category);
    }
}
