package com.xuzi.share.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;

    private Integer userId;

    private Integer designerId;

    private Integer type;

    private Integer status;

    private Integer amount;

    private Integer earnest;

    private Long createTime;

    private Long updateTime;

    private Long endTime;

    private String extendedField;

    private Integer refundStatus;

    private String refundReason;

    private String refuseReason;

    private Integer refundAmount;

    private String orderNo;

}