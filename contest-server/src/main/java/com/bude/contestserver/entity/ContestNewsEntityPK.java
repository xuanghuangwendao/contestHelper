package com.bude.contestserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ContestNewsEntityPK implements Serializable {
    private Integer cid;
    private Integer nid;

    @Column(name = "cid", nullable = false)
    @Id
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Column(name = "nid", nullable = false)
    @Id
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestNewsEntityPK that = (ContestNewsEntityPK) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(nid, that.nid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, nid);
    }
}
