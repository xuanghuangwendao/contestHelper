package com.bude.userserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_user", schema = "contesthelper", catalog = "")
@IdClass(ContestUserEntityPK.class)
public class ContestUserEntity {
    private int cid;
    private int uid;

    @Id
    @Column(name = "cid", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "uid", nullable = false)
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
        ContestUserEntity entity = (ContestUserEntity) o;
        return cid == entity.cid &&
                uid == entity.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid);
    }
}
