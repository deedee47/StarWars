package com.deedee.fingertips.deestarwars.models;

import java.io.Serializable;

public class People implements Serializable
{
    private String gender;
    private String hair_color;
    private String height;
    private String mass;
    private String name;
    private String skin_color;
    private String created;
    private String edited;

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public String getHairColor() {
//        return hair_color;
//    }
//
//    public void setHairColor(String hairColor) {
//        this.hair_color = hairColor;
//    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //not working
//    public String getSkinColor() {
//        return skin_color;
//    }
//
//    public void setSkinColor(String skin_color) {
//        this.skin_color = skin_color;
//    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }
}