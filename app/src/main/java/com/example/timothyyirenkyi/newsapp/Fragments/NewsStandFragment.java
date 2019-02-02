package com.example.timothyyirenkyi.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timothyyirenkyi.newsapp.MainActivity;
import com.example.timothyyirenkyi.newsapp.NewsSource;
import com.example.timothyyirenkyi.newsapp.NewsSourceAdapter;
import com.example.timothyyirenkyi.newsapp.R;
import com.example.timothyyirenkyi.newsapp.SourceActivity;
import com.example.timothyyirenkyi.newsapp.databinding.FragmentNewsStandBinding;

import java.util.ArrayList;

public class NewsStandFragment extends Fragment {

    NewsSourceAdapter adapter;

    private String webUrl;

    public static NewsStandFragment newInstance() {
        NewsStandFragment fragment = new NewsStandFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentNewsStandBinding binding = FragmentNewsStandBinding.inflate(getLayoutInflater(), container, false);

        View view = binding.getRoot();

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Create a list of NewsSources
        final ArrayList<NewsSource> newsSources = new ArrayList<>();

        newsSources.add(new NewsSource("Financial Times", R.drawable.financialtimes, "https://newsapi.org/v2/everything?sources=financial-times&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Fortune", R.drawable.fortune, "https://newsapi.org/v2/everything?sources=fortune&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Wall Street Journal", R.drawable.wsj, "https://newsapi.org/v2/everything?sources=the-wall-street-journal&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("The Economist", R.drawable.economist, "https://newsapi.org/v2/everything?sources=the-economist&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Business Insider", R.drawable.insider, "https://newsapi.org/v2/everything?sources=business-insider&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("CNBC", R.drawable.cnbc, "https://newsapi.org/v2/everything?sources=cnbc&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("The Verge", R.drawable.theverge, "https://newsapi.org/v2/everything?sources=the-verge&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Ars Technica", R.drawable.arstechnica, "https://newsapi.org/v2/everything?sources=ars-technica&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Techcrunch", R.drawable.techcrunch, "https://newsapi.org/v2/everything?sources=techcrunch&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Wired", R.drawable.wired, "https://newsapi.org/v2/everything?sources=wired&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Recode", R.drawable.recode, "https://newsapi.org/v2/everything?sources=recodeapiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("BBC Sport", R.drawable.bbcsport, "https://newsapi.org/v2/everything?sources=bbc-sport&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("ESPN", R.drawable.esn, "https://newsapi.org/v2/everything?sources=espn&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Fox Sports", R.drawable.foxsports, "https://newsapi.org/v2/everything?sources=fox-sports&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("NFL News", R.drawable.nfl, "https://newsapi.org/v2/everything?sources=nfl-news&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("talkSPORT", R.drawable.talksport, "https://newsapi.org/v2/everything?sources=talksport&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("BuzzFeed", R.drawable.buzzfeed, "https://newsapi.org/v2/everything?sources=buzzfeed&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Entertainment Weekly", R.drawable.entweekly, "https://newsapi.org/v2/everything?sources=entertainment-weekly&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("MTV News", R.drawable.mtvnews, "https://newsapi.org/v2/everything?sources=mtv-news&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Daily Mail", R.drawable.dailymail, "https://newsapi.org/v2/everything?sources=daily-mail&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Medical News Today", R.drawable.mednews, "https://newsapi.org/v2/everything?sources=medical-news-today&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("National Geographic", R.drawable.natgeo, "https://newsapi.org/v2/everything?sources=national-geographic&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("New Scientist", R.drawable.newscience, "https://newsapi.org/v2/everything?sources=new-scientist&apiKey=1bff5f606ee54cd48c4362f6429247b5"));
        newsSources.add(new NewsSource("Next Big Future", R.drawable.nextbigfuture, "https://newsapi.org/v2/everything?sources=next-big-future&apiKey=1bff5f606ee54cd48c4362f6429247b5"));

        adapter = new NewsSourceAdapter(getActivity(), newsSources);

        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsSource newsSource = newsSources.get(i);
                webUrl = newsSource.getmWebUrl();
                if (webUrl != null) {
                    Intent intent = new Intent(getContext(), SourceActivity.class);
                    intent.putExtra("webUrl", webUrl);
                    startActivity(intent);
                }
            }
        });

        return view;
    }



}
