package com.example.timothyyirenkyi.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryUtils {
    private QueryUtils() {}

    public static List<NewsStories> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem making the HTTp request", e);
        }

        List<NewsStories> newsStories = extractNewsStories(jsonResponse);

        // Return the {@link NewsStories} object
        return newsStories;
    }

    /**
     * Returns a new URL object from the given string URL.
     * @param stringUrl the string from which a URL object will made
     * @return the URL object
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
            return url;
        }catch (MalformedURLException e) {
            Log.e("QueryUtils", "Error creating URL object", e);
        }
        return url;
    }

    /**
     * Make the HTTP request and return the jsonResponse
     * @param url the URL object returned from the createUrl method
     * @return the JSON response from the server
     * @throws IOException
     */
    private static String makeHttpRequest (URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("QueryUtils", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest method signature specifies that an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * read the server output
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * parse the JSON response and return a list of{@link NewsStories} objects
     * @param newsJSON the JSON response returned by the makeHttpRequest method
     * @return a list of {@link NewsStories} objects
     */
    private static List<NewsStories> extractNewsStories(String newsJSON) {
        // if the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        List<NewsStories> newsStories = new ArrayList<>();

        try {
            JSONObject responseJSON = new JSONObject(newsJSON);
            JSONArray articlesArray = responseJSON.optJSONArray("articles");

            for (int index = 0; index < articlesArray.length(); index ++) {
                JSONObject newsObjectJSON = articlesArray.optJSONObject(index);
                JSONObject source = newsObjectJSON.optJSONObject("source");
                String name = source.optString("name");
                String title = newsObjectJSON.optString("title");
                String publishedAt = newsObjectJSON.optString("publishedAt");
                String date = getDate(publishedAt);

                String url = newsObjectJSON.optString("url");
                int id = index;
                newsStories.add(new NewsStories(id, name, title, date, url));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the newsStories
        return newsStories;
    }

    private static String getDate(String publishedAt) {
        String publishDate = publishedAt.substring(0, 10);
        String month = publishDate.substring(5, 7);
        String monthInWords = " ";
        switch (month) {
            case "01":
                monthInWords = "January";
                break;
            case "02":
                monthInWords = "February";
                break;
            case "03":
                monthInWords = "March";
                break;
            case "04":
                monthInWords = "April";
                break;
            case "05":
                monthInWords = "May";
                break;
            case "06":
                monthInWords = "June";
                break;
            case "07":
                monthInWords = "July";
                break;
            case "08":
                monthInWords = "August";
                break;
            case "09":
                monthInWords = "September";
                break;
            case "10":
                monthInWords = "October";
                break;
            case "11":
                monthInWords = "November";
                break;
            case "12":
                monthInWords = "December";
                break;
        }

        String day = publishDate.substring(8, 10);

        String date = monthInWords + " " + day;
        return date;
    }
}
