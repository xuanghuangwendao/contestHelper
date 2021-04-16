package com.bude.contestserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest", schema = "contesthelper", catalog = "")
public class ContestEntity {
    private Integer id;
    private String name;
    private String url;
    private Integer owner;
    private String introduction;
    private String tag;
    private String level;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "owner", nullable = false)
    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "introduction", nullable = false, length = 1500)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "tag", nullable = false, length = 255)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "level", nullable = false, length = 255)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestEntity entity = (ContestEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(url, entity.url) &&
                Objects.equals(owner, entity.owner) &&
                Objects.equals(introduction, entity.introduction) &&
                Objects.equals(tag, entity.tag) &&
                Objects.equals(level, entity.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, owner, introduction, tag, level);
    }
}
