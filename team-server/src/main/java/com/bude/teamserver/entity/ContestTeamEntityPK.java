package com.bude.teamserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ContestTeamEntityPK implements Serializable {
    private int cid;
    private int tid;

    @Column(name = "cid", nullable = false)
    @Id
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Column(name = "tid", nullable = false)
    @Id
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestTeamEntityPK that = (ContestTeamEntityPK) o;
        return cid == that.cid &&
                tid == that.tid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, tid);
    }
}
