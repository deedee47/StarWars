package com.deedee.fingertips.deestarwars.models;

import java.util.List;

public class Film {
    private String title;
    private String opening_crawl;
    private String created;
    private String edited;
    private Long comment_count;
    private String total_height_in_cm;
    private String total_height_in_feet;
    private int people_count;
    private List<People> people;
    private List<String> characters;

    public int getPeople_count() {
        return people_count;
    }

    public void setPeople_count(int people_count) {
        this.people_count = people_count;
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

    public Long getComment_count() {
        return comment_count;
    }

    public void setComment_count(Long comment_count) {
        this.comment_count = comment_count;
    }

    public String getTotal_height_in_cm() {
        return total_height_in_cm;
    }

    public void setTotal_height_in_cm(String total_height_in_cm) {
        this.total_height_in_cm = total_height_in_cm;
    }

    public String getTotal_height_in_feet() {
        return total_height_in_feet;
    }

    public void setTotal_height_in_feet(String total_height_in_feet) {
        this.total_height_in_feet = total_height_in_feet;
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
