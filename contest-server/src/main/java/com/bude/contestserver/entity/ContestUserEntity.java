package com.bude.contestserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_user", schema = "contesthelper", catalog = "")
@IdClass(ContestUserEntityPK.class)
public class ContestUserEntity {
    private Integer cid;
    private Integer uid;

    @Id
    @Column(name = "cid", nullable = false)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "uid", nullable = false)
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
        ContestUserEntity entity = (ContestUserEntity) o;
        return Objects.equals(cid, entity.cid) &&
                Objects.equals(uid, entity.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid);
    }
}
