package com.xuzi.share.entity;

public class Category {
    private Integer id;

    private Long name;

    private Integer level;

    private String describe;

    private Integer parentId;

    private Integer ancestorId;

    private String extendedField;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAncestorId() {
        return ancestorId;
    }

    public void setAncestorId(Integer ancestorId) {
        this.ancestorId = ancestorId;
    }

    public String getExtendedField() {
        return extendedField;
    }

    public void setExtendedField(String extendedField) {
        this.extendedField = extendedField == null ? null : extendedField.trim();
    }
}