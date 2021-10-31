package com.davis.imdbsample.mvp.model;

import androidx.room.Entity;

import java.io.Serializable;
@Entity(tableName = "movieCast")
public class MovieCast implements Serializable {
    String id;
    String name;
    String image;
    String asCharacter;

    public MovieCast() {
    }

    public MovieCast(String id, String name, String image, String asCharacter) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.asCharacter = asCharacter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAsCharacter() {
        return asCharacter;
    }

    public void setAsCharacter(String asCharacter) {
        this.asCharacter = asCharacter;
    }
}
