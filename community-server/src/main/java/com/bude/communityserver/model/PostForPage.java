package com.bude.communityserver.model;

import lombok.Data;

import java.util.List;


@Data
public class PostForPage {
    private int id;

    private String title;

    private String userName;

    private int userId;

    private String contestName;

    private int contestId;

    private List<PostItem> items;


    private String time;

}
