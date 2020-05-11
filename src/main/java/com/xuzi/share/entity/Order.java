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

    private Integer biddingId;

    private String resource;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 描述
     */
    private String describe;

    /**
     * 订单时间
     */
    private String time;

    /**
     * 用户名
     */
    private String username;

    /**
     * 最后期限
     */
    private String endline;

}