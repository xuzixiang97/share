package com.xuzi.share.entity;

import lombok.Data;

@Data
public class Category {
    private Integer id;

    private String name;

    private Integer level;

    private String describe;

    private Integer parentId;

    private Integer ancestorId;

    private String extendedField;

}