package com.bude.contestserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ContestUserEntityPK implements Serializable {
    private Integer cid;
    private Integer uid;

    @Column(name = "cid", nullable = false)
    @Id
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Column(name = "uid", nullable = false)
    @Id
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestUserEntityPK that = (ContestUserEntityPK) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid);
    }
}
