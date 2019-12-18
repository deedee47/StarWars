package com.deedee.fingertips.deestarwars.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Movie {
    private String title;
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    private String created;
    private String edited;
    private Long commentCount;
    private String totalHeightInCm;
    private String totalHeightInFeet;
    private int peopleCount;
    private List<People> people;
    private List<String> characters;

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

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

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getTotalHeightInCm() {
        return totalHeightInCm;
    }

    public void setTotalHeightInCm(String totalHeightInCm) {
        this.totalHeightInCm = totalHeightInCm;
    }

    public String getTotalHeightInFeet() {
        return totalHeightInFeet;
    }

    public void setTotalHeightInFeet(String totalHeightInFeet) {
        this.totalHeightInFeet = totalHeightInFeet;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }
}
