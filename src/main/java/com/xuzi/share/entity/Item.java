package com.xuzi.share.entity;

public class Item {
    private Integer id;

    private String name;

    private String describe;

    private Integer authorizeUnitprice;

    private Integer buyoutUnitprice;

    private String categoryIds;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getAuthorizeUnitprice() {
        return authorizeUnitprice;
    }

    public void setAuthorizeUnitprice(Integer authorizeUnitprice) {
        this.authorizeUnitprice = authorizeUnitprice;
    }

    public Integer getBuyoutUnitprice() {
        return buyoutUnitprice;
    }

    public void setBuyoutUnitprice(Integer buyoutUnitprice) {
        this.buyoutUnitprice = buyoutUnitprice;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds == null ? null : categoryIds.trim();
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg == null ? null : showImg.trim();
    }

    public String getEffectImg() {
        return effectImg;
    }

    public void setEffectImg(String effectImg) {
        this.effectImg = effectImg == null ? null : effectImg.trim();
    }

    public String getStructureImg() {
        return structureImg;
    }

    public void setStructureImg(String structureImg) {
        this.structureImg = structureImg == null ? null : structureImg.trim();
    }

    public String getRefenenceImg() {
        return refenenceImg;
    }

    public void setRefenenceImg(String refenenceImg) {
        this.refenenceImg = refenenceImg == null ? null : refenenceImg.trim();
    }

    public String getFabircImg() {
        return fabircImg;
    }

    public void setFabircImg(String fabircImg) {
        this.fabircImg = fabircImg == null ? null : fabircImg.trim();
    }

    public String getProductInformationImg() {
        return productInformationImg;
    }

    public void setProductInformationImg(String productInformationImg) {
        this.productInformationImg = productInformationImg == null ? null : productInformationImg.trim();
    }

    public String getCdrDownload() {
        return cdrDownload;
    }

    public void setCdrDownload(String cdrDownload) {
        this.cdrDownload = cdrDownload == null ? null : cdrDownload.trim();
    }

    public String getEtDownload() {
        return etDownload;
    }

    public void setEtDownload(String etDownload) {
        this.etDownload = etDownload == null ? null : etDownload.trim();
    }

    public String getAllDownload() {
        return allDownload;
    }

    public void setAllDownload(String allDownload) {
        this.allDownload = allDownload == null ? null : allDownload.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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