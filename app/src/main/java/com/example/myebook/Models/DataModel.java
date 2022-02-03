package com.example.myebook.Models;


import org.parceler.Parcel;

@Parcel
public class DataModel {

    String title, description, moral;
    String thumbnail_url;

    public DataModel(String title, String description, String moral, String thumbnail_url) {
        this.title = title;
        this.description = description;
        this.moral = moral;
        this.thumbnail_url = thumbnail_url;
    }

    public DataModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoral() {
        return moral;
    }

    public void setMoral(String moral) {
        this.moral = moral;
    }

    public String getthumbnail_url() {
        return thumbnail_url;
    }

    public void setthumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }
}
