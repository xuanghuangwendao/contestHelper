package com.bude.collectengine.model;

import lombok.Data;

@Data
public class Message {

    Integer id;

    Integer sid;

    String senderName;

    Integer aid;

    String accepterName;

    String content;

    String time;

    Integer type;

}
