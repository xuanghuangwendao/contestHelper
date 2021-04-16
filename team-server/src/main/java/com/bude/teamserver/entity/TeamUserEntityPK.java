package com.bude.teamserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamUserEntityPK implements Serializable {
    private int tid;
    private int uid;

    @Column(name = "tid", nullable = false)
    @Id
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Column(name = "uid", nullable = false)
    @Id
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamUserEntityPK pk = (TeamUserEntityPK) o;
        return tid == pk.tid &&
                uid == pk.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, uid);
    }
}
