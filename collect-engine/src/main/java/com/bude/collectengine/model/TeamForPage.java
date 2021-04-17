package com.bude.collectengine.model;

import lombok.Data;

@Data
public class TeamForPage {

    private int id;

    private String name;

    private String sign;

    private String leaderName;

    private int leaderId;

    private String contestName;

    private int contestId;

    private String tag;

    private boolean check;


}
