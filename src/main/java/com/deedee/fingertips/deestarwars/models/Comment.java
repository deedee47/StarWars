package com.deedee.fingertips.deestarwars.models;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "description", length = 500)
    private String  description;

    @ApiModelProperty(hidden = true)
    @Column(name = "created_date_utc")
    private Timestamp createdDateUtc;

    @ApiModelProperty(hidden = true)
    @Column(name = "ip_address")
    private  String ipAddress;

    public Comment() {
    }

    public Comment(int movieId, String description) {
        this.movieId = movieId;
        this.description = description;
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
