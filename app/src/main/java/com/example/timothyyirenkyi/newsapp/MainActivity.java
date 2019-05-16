package com.example.timothyyirenkyi.newsapp;



import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.timothyyirenkyi.newsapp.Fragments.PagerFragment;
import com.example.timothyyirenkyi.newsapp.Fragments.MainFragment;
import com.example.timothyyirenkyi.newsapp.Fragments.NewsStandFragment;
import com.example.timothyyirenkyi.newsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    MainFragment mainFragment;
    NewsStandFragment newsStandFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance();
            newsStandFragment = NewsStandFragment.newInstance();
        }


        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_frame_layout, newsStandFragment, "NewsStand")
                .detach(newsStandFragment)
                .add(R.id.fragment_frame_layout, mainFragment, "Main")
                .commit();

        binding.bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_headlines:
                        fragmentManager.beginTransaction()
                                .detach(newsStandFragment)
                                .attach(mainFragment)
                                .commit();
                        break;
                    case R.id.nav_newsstand:
                        fragmentManager.beginTransaction()
                                .detach(mainFragment)
                                .attach(newsStandFragment)
                                .commit();
                        break;
                }
                return true;
            }
        });

    }

    public static class NewsPagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public NewsPagerAdapter(FragmentManager fragmentManager, int numOfTabs) {
            super(fragmentManager);
            this.mNumOfTabs = numOfTabs;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0: /* Fragment # 2 - This will show BusinessFragment */
                    return PagerFragment.newInstance("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=1bff5f606ee54cd48c4362f6429247b5");
                case 1: /* Fragment # 4 - This will show HealthFragment */
                    return PagerFragment.newInstance("https://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=1bff5f606ee54cd48c4362f6429247b5");
                case 2: /* Fragment # 5 - This will show ScienceFragment */
                    return PagerFragment.newInstance("https://newsapi.org/v2/top-headlines?country=us&category=science&apiKey=1bff5f606ee54cd48c4362f6429247b5");
                case 3: /* Fragment # 6 - This will show SportsFragment */
                    return PagerFragment.newInstance("https://newsapi.org/v2/top-headlines?category=sports&country=gb&apiKey=1bff5f606ee54cd48c4362f6429247b5");
                case 4: /* Fragment # 7 - This will show TechFragment */
                    return PagerFragment.newInstance("https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=1bff5f606ee54cd48c4362f6429247b5");
                default:
                    return null;
            }
        }
    }


}
