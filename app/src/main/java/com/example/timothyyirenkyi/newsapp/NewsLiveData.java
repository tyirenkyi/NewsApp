package com.example.timothyyirenkyi.newsapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import java.net.URL;
import java.util.List;

public class NewsLiveData extends LiveData<List<NewsStories>> {
    private String mUrl = "https://newsapi.org/v2/top-headlines?category=general&apiKey=1bff5f606ee54cd48c4362f6429247b5";

    private final Context context;

    public NewsLiveData(Context context) {
        this.context = context;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
        loadData();
    }

    private void loadData() {
        new AsyncTask<URL, Void, List<NewsStories>>() {
            @Override
            protected List<NewsStories> doInBackground(URL... urls) {
                List<NewsStories> newsStories = QueryUtils.fetchNewsData(mUrl);
                return newsStories;
            }

            @Override
            protected void onPostExecute(List<NewsStories> newsStories) {
                setValue(newsStories);
            }
        }.execute();
    }
}
