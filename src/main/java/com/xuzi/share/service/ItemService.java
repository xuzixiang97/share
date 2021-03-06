package com.xuzi.share.service;

import com.xuzi.share.entity.Category;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.User;

import java.util.List;

public interface ItemService {

    /**
     * 查询作品（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
    List<Item> findPage(int offset, int limit);

    /**
     * 查询作品（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
    List<Item> findPageByCategoryId(int offset, int limit, String categoryId);


    /**
     * 查询作品数量（用于分页，管理员后台）
     * @return
     */
    int selectRows();

    /**
     * 查询作品数量（用于分页，管理员后台）
     * @return
     */
    int selectRowsByCategoryId(String categoryId);

    /**
     *
     * @param keyword
     * @return
     */
    int selectRowsByKey(String keyword);

    /**
     * 查询作品（用于管理员后台）
     * @param offset  起始行
     * @param limit 每页显示上限
     * @return
     */
    List<Item> findPageByDesignerId(int offset, int limit, Integer designerId);

    /**
     * 查询作品数量（用于分页，管理员后台）
     * @return
     */
    int selectRowsByDesignerId(Integer designerId);

    /**
     * 新增一条商品
     * @param item
     * @return
     */
    Item insert(Item item);

    /**
     * 根据类目查询商品列表
     * @param categoryIds
     * @return
     */
    List<Item> selectByCategoryId(Integer categoryIds);

    /**
     * 根据设计师查询商品列表
     * @param designerId
     * @return
     */
    List<Item> selectByDesignerId(Integer designerId);

    /**
     * 根据风格查询商品列表
     * @param styleIds
     * @return
     */
    List<Item> selectByStyleId(String styleIds);

    /**
     * 根基id查询商品详情
     * @param Id
     * @return
     */
     Item selectById(Integer Id);

    /**
     *
     * @param item
     * @return
     */
     List<Item> selectByCondition(Item item);

     int updateByCondition(Item item);

     List<Item> selectByKey(int offset, int limit, String keyword);

}
