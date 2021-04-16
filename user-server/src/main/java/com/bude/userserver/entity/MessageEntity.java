package com.bude.userserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "contesthelper", catalog = "")
public class MessageEntity {
    private int id;
    private int sid;
    private int aid;
    private Timestamp time;
    private String content;
    private int type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "aid", nullable = false)
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity entity = (MessageEntity) o;
        return id == entity.id &&
                sid == entity.sid &&
                aid == entity.aid &&
                type == entity.type &&
                Objects.equals(time, entity.time) &&
                Objects.equals(content, entity.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sid, aid, time, content, type);
    }
}
