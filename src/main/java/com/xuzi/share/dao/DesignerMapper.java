package com.xuzi.share.dao;

import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DesignerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Designer record);

    Designer selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Designer record);

    List<Designer> selectAll();

    List<Designer> findPage(int offset, int limit);

    int selectRows();

    Admin findByUserName(String username);
}