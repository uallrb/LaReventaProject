package com.example.harshvardhansingh.lareventaproject;

/**
 * Created by Harsh Vardhan Singh on 18/05/16.
 */
public class CategoryBean {
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public CategoryBean() {
    }

    public CategoryBean(String name , int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
