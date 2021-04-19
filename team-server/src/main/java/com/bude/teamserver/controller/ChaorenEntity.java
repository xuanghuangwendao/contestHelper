package com.bude.teamserver.controller;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chaoren", schema = "contesthelper", catalog = "")
public class ChaorenEntity {
    private int id;
    private String time;
    private String data;
    private String qq;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 255)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "data", nullable = true, length = 255)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 255)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChaorenEntity entity = (ChaorenEntity) o;
        return id == entity.id &&
                Objects.equals(time, entity.time) &&
                Objects.equals(data, entity.data) &&
                Objects.equals(qq, entity.qq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, data, qq);
    }
}
