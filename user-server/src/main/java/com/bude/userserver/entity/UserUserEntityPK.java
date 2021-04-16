package com.bude.userserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserUserEntityPK implements Serializable {
    private int uu1;
    private int uu2;

    @Column(name = "uu1", nullable = false)
    @Id
    public int getUu1() {
        return uu1;
    }

    public void setUu1(int uu1) {
        this.uu1 = uu1;
    }

    @Column(name = "uu2", nullable = false)
    @Id
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
        UserUserEntityPK that = (UserUserEntityPK) o;
        return uu1 == that.uu1 &&
                uu2 == that.uu2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uu1, uu2);
    }
}
