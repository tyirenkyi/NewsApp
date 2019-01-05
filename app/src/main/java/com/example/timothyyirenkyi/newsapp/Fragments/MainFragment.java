package com.example.timothyyirenkyi.newsapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timothyyirenkyi.newsapp.MainActivity;
import com.example.timothyyirenkyi.newsapp.R;

public class MainFragment extends Fragment {

    FragmentPagerAdapter adapter;

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        adapter = new MainActivity.NewsPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        return view;
    }
}
