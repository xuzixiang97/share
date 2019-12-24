package com.xuzi.share.service;


import com.xuzi.share.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 根据id删除一条记录
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 新增一条类目
     * @param category
     * @return
     */
    Category insert(Category category);

    /**
     * 根据id查询一条类目
     * @param id
     * @return
     */
    Category selectById(Integer id);

    /**
     * 修改类目信息
     * @param category
     * @return
     */
    int updateById(Category category);

    /**
     * 条件查询类目信息
     * @param category
     * @return
     */
    List<Category> selectbyCondition(Category category);

}
