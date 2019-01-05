package com.example.timothyyirenkyi.newsapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private final NewsLiveData data;

    public NewsViewModel(Application application) {
        super(application);
        data = new NewsLiveData(application);
    }
    public void setUrl(String mUrl) {
        data.setmUrl(mUrl);
    }
    public LiveData<List<NewsStories>> getData() {
        return data;
    }
}

