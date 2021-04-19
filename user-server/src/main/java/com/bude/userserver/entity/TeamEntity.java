package com.bude.userserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "team", schema = "contesthelper", catalog = "")
public class TeamEntity {
    private int id;
    private String name;
    private String sign;
    private Integer leader;
    private Integer contest;
    private String tag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sign", nullable = true, length = 255)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Basic
    @Column(name = "leader", nullable = true)
    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    @Basic
    @Column(name = "contest", nullable = true)
    public Integer getContest() {
        return contest;
    }

    public void setContest(Integer contest) {
        this.contest = contest;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = 255)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamEntity entity = (TeamEntity) o;
        return id == entity.id &&
                Objects.equals(name, entity.name) &&
                Objects.equals(sign, entity.sign) &&
                Objects.equals(leader, entity.leader) &&
                Objects.equals(contest, entity.contest) &&
                Objects.equals(tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sign, leader, contest, tag);
    }
}
