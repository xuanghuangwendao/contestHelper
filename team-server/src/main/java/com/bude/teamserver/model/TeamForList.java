package com.bude.teamserver.model;


import lombok.Data;

@Data
public class TeamForList {

    private int id;

    private String name;

    private String tag;

    private String leaderName;

    private boolean isFollow;

}
