package com.xuzi.share.entity;

import lombok.Data;

import java.util.List;

public class BiddingCustom {
    private Integer id;

    private Integer userId;

    private String categoryIds;

    private String describe;

    private Integer quantity;

    private String enclosure;

    private String designerIds;

    private Integer baojia;


    private List<Designer> designerIdList;

    private List<BidDesigner> bidDesigners;

    private String itemIds;

    private Integer status;

    private Long amount;

    private Long earnest;

    private Long createTime;

    private Long updateTime;

    private String endTime;

    private String extendedField;

    private String orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds == null ? null : categoryIds.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure == null ? null : enclosure.trim();
    }

    public String getDesignerIds() {
        return designerIds;
    }

    public void setDesignerIds(String designerIds) {
        this.designerIds = designerIds == null ? null : designerIds.trim();
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds == null ? null : itemIds.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getEarnest() {
        return earnest;
    }

    public void setEarnest(Long earnest) {
        this.earnest = earnest;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExtendedField() {
        return extendedField;
    }

    public void setExtendedField(String extendedField) {
        this.extendedField = extendedField == null ? null : extendedField.trim();
    }

    public List<Designer> getDesignerIdList() {
        return designerIdList;
    }

    public void setDesignerIdList(List<Designer> designerIdList) {
        this.designerIdList = designerIdList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<BidDesigner> getBidDesigners() {
        return bidDesigners;
    }

    public void setBidDesigners(List<BidDesigner> bidDesigners) {
        this.bidDesigners = bidDesigners;
    }

    public Integer getBaojia() {
        return baojia;
    }

    public void setBaojia(Integer baojia) {
        this.baojia = baojia;
    }
}