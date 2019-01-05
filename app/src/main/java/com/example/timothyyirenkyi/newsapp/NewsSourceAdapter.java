package com.example.timothyyirenkyi.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

public class NewsSourceAdapter extends ArrayAdapter<NewsSource> {

    String webUrl;

    public NewsSourceAdapter(Context context, ArrayList<NewsSource> newsSources) {
        super(context, 0, newsSources);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.source_item_view, parent, false);
        }

        // Get the {@link NewsSource}object located at this position in the listView
        NewsSource newsSource = getItem(position);

        ViewHolder holder = new ViewHolder();
        // Find the ImageView in the source_item.xml layout with the ID news_source_imageView
        holder.icon = listItemView.findViewById(R.id.news_source_imageView);
        // Get the image resource id from the current NewsSource object and
        // set this on the news ImageView
        RequestManager requestManager = Glide.with(listItemView);
        RequestBuilder requestBuilder = requestManager.load(newsSource.getImageResourceId());
        requestBuilder.into(holder.icon);

        // Find the TextView in the source_item.xml layout with the ID news_source_textView
        holder.text = listItemView.findViewById(R.id.news_source_textView);
        // Get the news source name from the current NewsSource object and
        // set it to the news TextView
        holder.text.setText(newsSource.getSourceName());

        listItemView.setTag(holder);

        webUrl = newsSource.getmWebUrl();

        // Return the whole list item layout
        return listItemView;
    }


    public String getWebUrl() { return webUrl; }

    static class ViewHolder {
        ImageView icon;
        TextView text;
    }
}
