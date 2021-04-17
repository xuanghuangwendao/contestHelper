package com.bude.collectengine.model;

import lombok.Data;

@Data
public class PostItem {
    private int id;

    private String userName;

    private int userId;

    private int postId;

    private String content;

    private String time;

    private int order;
}
