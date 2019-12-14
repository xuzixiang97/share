package com.xuzi.share.entity;

public class Admin {
    private Integer id;

    private String username;

    private String password;

    private Integer isVailed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIsVailed() {
        return isVailed;
    }

    public void setIsVailed(Integer isVailed) {
        this.isVailed = isVailed;
    }
}