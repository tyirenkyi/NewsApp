package com.example.timothyyirenkyi.newsapp;

public class NewsStories {
    String name;
    String title;
    String datePub;
    String url;

    public NewsStories(String name, String title, String datePub, String url) {
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
}
