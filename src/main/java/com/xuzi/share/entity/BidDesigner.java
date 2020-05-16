package com.xuzi.share.entity;

import lombok.Data;

@Data
public class BidDesigner {
    private Integer designerId;
    private Integer price;
    private String status;
    private String remark;
    private  String designerName;
}
