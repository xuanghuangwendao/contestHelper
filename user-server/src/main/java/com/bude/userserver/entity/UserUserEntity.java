package com.bude.userserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_user", schema = "contesthelper", catalog = "")
@IdClass(UserUserEntityPK.class)
public class UserUserEntity {
    private int uu1;
    private int uu2;

    @Id
    @Column(name = "uu1", nullable = false)
    public int getUu1() {
        return uu1;
    }

    public void setUu1(int uu1) {
        this.uu1 = uu1;
    }

    @Id
    @Column(name = "uu2", nullable = false)
    public int getUu2() {
        return uu2;
    }

    public void setUu2(int uu2) {
        this.uu2 = uu2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUserEntity entity = (UserUserEntity) o;
        return uu1 == entity.uu1 &&
                uu2 == entity.uu2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uu1, uu2);
    }
}
