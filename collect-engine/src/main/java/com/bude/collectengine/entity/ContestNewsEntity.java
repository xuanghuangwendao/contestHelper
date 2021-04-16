package com.bude.collectengine.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_news", schema = "contesthelper", catalog = "")
@IdClass(ContestNewsEntityPK.class)
public class ContestNewsEntity {
    private int cid;
    private int nid;

    @Id
    @Column(name = "cid", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "nid", nullable = false)
    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestNewsEntity that = (ContestNewsEntity) o;
        return cid == that.cid &&
                nid == that.nid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, nid);
    }

}
