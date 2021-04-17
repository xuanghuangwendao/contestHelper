package com.bude.communityserver.model;


import lombok.Data;

@Data
public class UserForPage {

    private int id;

    private String displayName;

    private String sign;

    private String school;

    private String grade;

    private String major;

    private String introduction;

    private String tag;

    private boolean isFriend;

}
