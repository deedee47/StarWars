package com.deedee.fingertips.deestarwars.models;

import java.util.List;

public class Movie {
    private String title;
    private String opening_crawl;
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

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public void setOpening_crawl(String opening_crawl) {
        this.opening_crawl = opening_crawl;
    }
//not working
//    public String getOpeningCrawl() {
//        return opening_crawl;
//    }
//
//    public void setOpeningCrawl(String opening_crawl) {
//        this.opening_crawl = opening_crawl;
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
