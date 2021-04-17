package com.bude.contestserver.model;


import lombok.Data;

@Data
public class ContestForList {

    private int id;

    private String name;

    private String ownerName;

    private String lastNews;

    private String tag;

    private double score;


}
