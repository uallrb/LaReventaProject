package com.example.harshvardhansingh.lareventaproject;

/**
 * Created by Harsh Vardhan Singh on 18/05/16.
 */
public class TopDealsBean {
    private String name;
    private int numOfSongs;
    private int thumbnail;
    private int price;

    public TopDealsBean() {
    }

    public TopDealsBean(String name,int price , int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int numOfSongs) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
