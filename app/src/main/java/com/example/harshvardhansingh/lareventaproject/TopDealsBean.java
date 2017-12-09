package com.example.harshvardhansingh.lareventaproject;

public class TopDealsBean {
    private String name;
    private int numOfSongs;
    private int thumbnail;
    private int price,sno;

    public TopDealsBean() {
    }

    public TopDealsBean(String name,int price , int thumbnail,int sno) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.price=price;
        this.sno=sno;
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

    public int getSno() {
        return sno;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
