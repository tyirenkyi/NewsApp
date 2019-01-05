package com.example.timothyyirenkyi.newsapp.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.timothyyirenkyi.newsapp.NewsAdapter;
import com.example.timothyyirenkyi.newsapp.NewsStories;
import com.example.timothyyirenkyi.newsapp.NewsViewModel;
import com.example.timothyyirenkyi.newsapp.databinding.FragmentLatestBinding;

import java.util.ArrayList;
import java.util.List;

public class PagerFragment extends Fragment{
    NewsAdapter newsAdapter;

    private static final String NEWS_API_SEARCH_URL = "IOU";

    private String webUrl;

    private NewsViewModel nModel;
    // newInstance constructor for creating fragment with arguments
    public static PagerFragment newInstance(String webUrl) {
        PagerFragment pagerFragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putString(NEWS_API_SEARCH_URL, webUrl);
        pagerFragment.setArguments(args);
        return pagerFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLatestBinding binding = FragmentLatestBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        newsAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsStories>());
        binding.latestListView.setAdapter(newsAdapter);


        // Get the ViewModel
        nModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        Bundle args = getArguments();
        if (args != null) {
            nModel.setUrl(args.getString(NEWS_API_SEARCH_URL));
        }

        // Create the observer which updates the UI
        final Observer<List<NewsStories>> newsObserver = new Observer<List<NewsStories>>() {
            @Override
            public void onChanged(@Nullable List<NewsStories> newsStories) {
                // Update the UI
                if (newsStories != null) {
                    newsAdapter.addAll(newsStories);
                }

            }
        };

        // Observe the LiveData, passing in this activity as the
        // LifecycleOwner and the Observer
        nModel.getData().observe(this, newsObserver);

        binding.latestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                webUrl = newsAdapter.getItem(i).getUrl();
                if (webUrl != null) {
                    Uri webPage = Uri.parse(webUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
                    if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });
        return view;
    }
}
