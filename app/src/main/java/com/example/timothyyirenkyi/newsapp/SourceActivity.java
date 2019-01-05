package com.example.timothyyirenkyi.newsapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.timothyyirenkyi.newsapp.Fragments.PagerFragment;

public class SourceActivity extends AppCompatActivity {

    PagerFragment pagerFragment;
    String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        webUrl = getIntent().getStringExtra("webUrl");
        pagerFragment = PagerFragment.newInstance(webUrl);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, pagerFragment)
                .commit();
    }
}
