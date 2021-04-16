package com.bude.teamserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "team_user", schema = "contesthelper", catalog = "")
@IdClass(TeamUserEntityPK.class)
public class TeamUserEntity {
    private int tid;
    private int uid;

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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
        TeamUserEntity entity = (TeamUserEntity) o;
        return tid == entity.tid &&
                uid == entity.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, uid);
    }
}
