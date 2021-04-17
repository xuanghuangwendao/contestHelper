package com.bude.zuulserver.model;


import lombok.Data;

@Data
public class UserForList {

    private int id;

    private String displayName;

    private String tag;

    private String sign;

    private boolean isFriend;


}
