package com.bude.utils.model;


import lombok.Data;

import java.util.List;

@Data
public class ContestForPage {

    private int id;

    private String name;

    private String url;

    private String ownerName;

    private int ownerId;

    private String introduction;

    private String level;

    private String tag;

    private boolean isFollow;

    private List<NewsForList> news;

}
