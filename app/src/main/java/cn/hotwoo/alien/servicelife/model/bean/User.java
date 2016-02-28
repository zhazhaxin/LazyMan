package cn.hotwoo.alien.servicelife.model.bean;

import java.io.Serializable;

/**
 * Created by alien on 2015/8/13.
 */
public class User implements Serializable{
    private int id;
    private long birth;
    private int gender;
    private int age;
    private long phone;
    private long qq;
    private String name;
    private String sign;
    private String face;
    private String school;
    private String major;
    private String intro;
    private String loverSpace;
    private String lovePassword;

    public String getLoverSpace() {
        return loverSpace;
    }

    public void setLoverSpace(String loverSpace) {
        this.loverSpace = loverSpace;
    }

    public String getLovePassword() {
        return lovePassword;
    }

    public void setLovePassword(String lovePassword) {
        this.lovePassword = lovePassword;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", birth=" + birth +
                ", gender=" + gender +
                ", age=" + age +
                ", phone=" + phone +
                ", qq=" + qq +
                ", name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                ", face='" + face + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", intro='" + intro + '\'' +
                ", loverSpace='" + loverSpace + '\'' +
                ", lovePassword='" + lovePassword + '\'' +
                '}';
    }
}
