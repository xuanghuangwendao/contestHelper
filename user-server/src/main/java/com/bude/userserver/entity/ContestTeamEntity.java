package com.bude.userserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_team", schema = "contesthelper", catalog = "")
@IdClass(ContestTeamEntityPK.class)
public class ContestTeamEntity {
    private int cid;
    private int tid;

    @Id
    @Column(name = "cid", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "tid", nullable = false)
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
        ContestTeamEntity that = (ContestTeamEntity) o;
        return cid == that.cid &&
                tid == that.tid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, tid);
    }
}
