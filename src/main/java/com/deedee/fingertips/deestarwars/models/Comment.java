package com.deedee.fingertips.deestarwars.models;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "description")
    private String  description;

    @Column(name = "created_date_utc")
    private Timestamp createdDateUtc;

    @Column(name = "ip_address")
    private  String ipAddress;

    public Comment() {
    }

    public Comment(int movie_id, String description, String ip_address) {
        this.movieId = movie_id;
        this.description = description;
        this.ipAddress = ip_address;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedDateUtc() {
        return createdDateUtc;
    }

    public void setCreatedDateUtc(Timestamp createdDateUtc) {
        this.createdDateUtc = createdDateUtc;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
