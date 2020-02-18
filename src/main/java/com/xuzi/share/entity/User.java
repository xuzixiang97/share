package com.xuzi.share.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String nickName;

    private String email;

    private Integer type;

    private Integer status;

    private String headerUrl;

    private String extendedField;


}