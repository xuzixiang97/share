package com.xuzi.share.entity;

public class OrderItems {
    private Integer id;

    private Integer orderId;

    private Integer eneityType;

    private Integer eneityId;

    private Integer buyType;

    private Integer quantity;

    private Integer unitprice;

    private Long createTime;

    private Long updateTime;

    private String extendedField;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getEneityType() {
        return eneityType;
    }

    public void setEneityType(Integer eneityType) {
        this.eneityType = eneityType;
    }

    public Integer getEneityId() {
        return eneityId;
    }

    public void setEneityId(Integer eneityId) {
        this.eneityId = eneityId;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Integer unitprice) {
        this.unitprice = unitprice;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtendedField() {
        return extendedField;
    }

    public void setExtendedField(String extendedField) {
        this.extendedField = extendedField == null ? null : extendedField.trim();
    }
}