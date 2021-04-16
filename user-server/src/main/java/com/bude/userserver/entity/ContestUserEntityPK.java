package com.bude.userserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ContestUserEntityPK implements Serializable {
    private int cid;
    private int uid;

    @Column(name = "cid", nullable = false)
    @Id
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
        ContestUserEntityPK that = (ContestUserEntityPK) o;
        return cid == that.cid &&
                uid == that.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid);
    }
}
