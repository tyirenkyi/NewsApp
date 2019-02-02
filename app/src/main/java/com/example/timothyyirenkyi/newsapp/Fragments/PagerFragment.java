package com.example.timothyyirenkyi.newsapp.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.timothyyirenkyi.newsapp.NewsAdapter;
import com.example.timothyyirenkyi.newsapp.NewsStories;
import com.example.timothyyirenkyi.newsapp.NewsViewModel;
import com.example.timothyyirenkyi.newsapp.R;
import com.example.timothyyirenkyi.newsapp.databinding.FragmentPageBinding;


import java.util.ArrayList;
import java.util.List;

public class PagerFragment extends Fragment{
    NewsAdapter newsAdapter;

    private static final String NEWS_API_SEARCH_URL = "IOU";

    private String webUrl;

    private NewsViewModel nModel;

    private String mPackageNameToBind;

    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.chrome.dev";

    CustomTabsClient mClient;

    CustomTabsSession mCustomTabsSession;

    CustomTabsServiceConnection mCustomTabsServiceConnection;

    CustomTabsIntent customTabsIntent;

    // newInstance constructor for creating fragment with arguments
    public static PagerFragment newInstance(String webUrl) {
        PagerFragment pagerFragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putString(NEWS_API_SEARCH_URL, webUrl);
        pagerFragment.setArguments(args);
        return pagerFragment;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPackageNameToBind = getActivity().getPackageName();

        mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                mClient = customTabsClient;
                mClient.warmup(0);
                mCustomTabsSession = mClient.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mClient = null;
            }
        };
    }

    // Inflate the view for the fragment based on layout XML
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPageBinding binding = FragmentPageBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        newsAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsStories>());
        binding.pageList.setAdapter(newsAdapter);


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

        binding.pageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                webUrl = newsAdapter.getItem(i).getUrl();
                if (webUrl != null) {
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    builder.setToolbarColor(getResources().getColor(R.color.colorPrimaryDark));
                    builder.setShowTitle(true);
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(getContext(), Uri.parse(webUrl));
                }
            }
        });
        return view;
    }
}