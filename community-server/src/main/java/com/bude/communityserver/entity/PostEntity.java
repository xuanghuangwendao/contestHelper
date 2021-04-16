package com.bude.communityserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "contesthelper", catalog = "")
public class PostEntity {
    private int id;
    private Integer uid;
    private Integer cid;
    private String title;
    private Timestamp time;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "cid", nullable = true)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity entity = (PostEntity) o;
        return id == entity.id &&
                Objects.equals(uid, entity.uid) &&
                Objects.equals(cid, entity.cid) &&
                Objects.equals(title, entity.title) &&
                Objects.equals(time, entity.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, cid, title, time);
    }
}
