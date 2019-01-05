package com.example.timothyyirenkyi.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsStories> {

    private String webUrl;

    public NewsAdapter(Activity context, ArrayList<NewsStories> currentNews) {
        super(context, 0, currentNews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }

        // Get the NewsStories object located at this position in the list
        NewsStories currentNews = getItem(position);

        // Find the TextView in the layout with the ID name_textView
        TextView nameTextView = listItemView.findViewById(R.id.name_textView);
        // Display the name of the current News story in that TextView
        nameTextView.setText(currentNews.getName());

        // Find the TextView in the layout with the ID title_textView
        TextView titleTextView = listItemView.findViewById(R.id.title_textView);
        // Display the title of the current News story in that TextView
        titleTextView.setText(currentNews.getTitle());

        // Find the TextView in the layout with the ID date_textView
        TextView dateTextView = listItemView.findViewById(R.id.date_textView);
        // Display the date of the current News story in that TextView
        dateTextView.setText(currentNews.getDatePub());

        webUrl = currentNews.getUrl();

        return listItemView;
    }

    public String getWebUrl() {
        return webUrl;
    }

}
