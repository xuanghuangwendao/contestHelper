package com.bude.collectengine.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ContestNewsEntityPK implements Serializable {
    private int cid;
    private int nid;

    @Column(name = "cid", nullable = false)
    @Id
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Column(name = "nid", nullable = false)
    @Id
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
        ContestNewsEntityPK that = (ContestNewsEntityPK) o;
        return cid == that.cid &&
                nid == that.nid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, nid);
    }
}
