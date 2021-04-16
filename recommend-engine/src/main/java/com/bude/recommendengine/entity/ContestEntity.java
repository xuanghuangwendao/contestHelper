package com.bude.recommendengine.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest", schema = "contesthelper", catalog = "")
public class ContestEntity {
    private int id;
    private String name;
    private String image;
    private String url;
    private int owner;
    private String introduction;
    private String tag;
    private String level;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "owner")
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "level")
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
        return id == entity.id &&
                owner == entity.owner &&
                Objects.equals(name, entity.name) &&
                Objects.equals(image, entity.image) &&
                Objects.equals(url, entity.url) &&
                Objects.equals(introduction, entity.introduction) &&
                Objects.equals(tag, entity.tag) &&
                Objects.equals(level, entity.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, url, owner, introduction, tag, level);
    }
}
