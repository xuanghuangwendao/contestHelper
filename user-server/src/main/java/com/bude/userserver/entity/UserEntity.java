package com.bude.userserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "contesthelper", catalog = "")
public class UserEntity {
    private int id;
    private String displayname;
    private String image;
    private String sign;
    private String school;
    private String grade;
    private String major;
    private String introduction;
    private String tag;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "displayname", nullable = false, length = 255)
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "sign", nullable = false, length = 255)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Basic
    @Column(name = "school", nullable = false, length = 255)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "grade", nullable = false, length = 255)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "major", nullable = false, length = 255)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "introduction", nullable = false, length = 255)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return id == entity.id &&
                Objects.equals(displayname, entity.displayname) &&
                Objects.equals(image, entity.image) &&
                Objects.equals(sign, entity.sign) &&
                Objects.equals(school, entity.school) &&
                Objects.equals(grade, entity.grade) &&
                Objects.equals(major, entity.major) &&
                Objects.equals(introduction, entity.introduction) &&
                Objects.equals(tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayname, image, sign, school, grade, major, introduction, tag);
    }
}
