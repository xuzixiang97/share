package com.xuzi.share.constant;

public enum DeafultHraderEnum {
    DSEIGNER("http://q2ozeie6p.bkt.clouddn.com/designer", "设计师"),
    User("#", "用户");

    /**
     * 图片路径
     */
    private String url;
    /**
     * 名称
     */
    private String name;

    // 构造方法
    private DeafultHraderEnum(String url, String name) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 普通方法
    public static String getUrl(String name) {
        for (DeafultHraderEnum c : DeafultHraderEnum.values()) {
            if (c.getName().equals(name)) {
                return c.getUrl();
            }
        }
        return null;
    }

}
