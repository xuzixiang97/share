package com.xuzi.share.entity;

import lombok.Data;

@Data
public class Item {
    private Integer id;

    private String name;

    private Integer designerId;

    private String describe;

    private Integer authorizeUnitprice;

    private Integer buyoutUnitprice;

    private String categoryIds;

    private String materialIds;

    private String technologyIds;

    private String styleIds;

    private String seasonIds;

    private String showImg;

    private String effectImg;

    private String structureImg;

    private String refenenceImg;

    private String fabircImg;

    private String productInformationImg;

    private String cdrDownload;

    private String etDownload;

    private String allDownload;

    private Integer status;

    private Long createTime;

    private Long updateTime;

    private String extendedField;

    private String showImg2;

    private String showImg3;

    private String age;

    private String gender;

}