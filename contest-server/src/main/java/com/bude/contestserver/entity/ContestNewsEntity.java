package com.bude.contestserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_news", schema = "contesthelper", catalog = "")
@IdClass(ContestNewsEntityPK.class)
public class ContestNewsEntity {
    private Integer cid;
    private Integer nid;

    @Id
    @Column(name = "cid", nullable = false)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "nid", nullable = false)
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
        ContestNewsEntity entity = (ContestNewsEntity) o;
        return Objects.equals(cid, entity.cid) &&
                Objects.equals(nid, entity.nid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, nid);
    }
}
