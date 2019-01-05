package com.example.timothyyirenkyi.newsapp;

public class NewsSource {

    private String mSourceName;
    private int mImageResourceId;
    private String mWebUrl;

    public NewsSource(String sourceName, int imageResourceId, String webUrl) {
        mImageResourceId = imageResourceId;
        mSourceName = sourceName;
        mWebUrl = webUrl;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getSourceName() {
        return mSourceName;
    }

    public String getmWebUrl() { return mWebUrl; }
}
