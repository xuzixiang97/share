package com.xuzi.share.entity;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String nickName;

    private String email;

    private Integer type;

    private Integer status;

    private String headerUrl;

    private Integer isVailed;

    private String extendedField;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl == null ? null : headerUrl.trim();
    }

    public Integer getIsVailed() {
        return isVailed;
    }

    public void setIsVailed(Integer isVailed) {
        this.isVailed = isVailed;
    }

    public String getExtendedField() {
        return extendedField;
    }

    public void setExtendedField(String extendedField) {
        this.extendedField = extendedField == null ? null : extendedField.trim();
    }
}