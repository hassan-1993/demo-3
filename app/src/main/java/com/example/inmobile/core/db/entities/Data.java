package com.example.inmobile.core.db.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Data implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("Id")
    String id;



    String link;
    String description;
    String title;

    public Data(String id, String link, String description, String title) {
        this.id = id;
        this.link = link;
        this.description = description;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
