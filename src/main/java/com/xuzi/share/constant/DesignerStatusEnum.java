package com.xuzi.share.constant;

public enum DesignerStatusEnum {
    UNEXAMINE (0, "未审核"),
    UNEXAMINEING (1, "待审核"),
    EXAMINED(2, "已审核");

    /**
     * 图片路径
     */
    private Integer status;
    /**
     * 名称
     */
    private String describe;

    // 构造方法
    private DesignerStatusEnum(Integer status, String describe) {
        this.describe = describe;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String name) {
        this.describe = describe;
    }

    // 普通方法
    public static Integer getStatus(String describe) {
        for (DesignerStatusEnum c : DesignerStatusEnum.values()) {
            if (c.getDescribe().equals(describe)) {
                return c.getStatus();
            }
        }
        return null;
    }

}
