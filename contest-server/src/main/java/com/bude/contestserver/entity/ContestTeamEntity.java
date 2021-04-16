package com.bude.contestserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_team", schema = "contesthelper", catalog = "")
@IdClass(ContestTeamEntityPK.class)
public class ContestTeamEntity {
    private Integer cid;
    private Integer tid;

    @Id
    @Column(name = "cid", nullable = false)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "tid", nullable = false)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestTeamEntity that = (ContestTeamEntity) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(tid, that.tid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, tid);
    }
}
