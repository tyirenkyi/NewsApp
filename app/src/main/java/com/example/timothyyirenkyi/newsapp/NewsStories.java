package com.example.timothyyirenkyi.newsapp;

public class NewsStories {
    String name;
    String title;
    String datePub;
    String url;
    int id;

    public NewsStories(int id, String name, String title, String datePub, String url) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.datePub = datePub;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getDatePub() {
        return datePub;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
