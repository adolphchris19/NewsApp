package com.adolphchris.android.guardiannews.Utils;

import android.net.Uri;
import android.util.Log;

import com.adolphchris.android.guardiannews.UI.NewsFeed;

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
import java.util.ArrayList;
import java.util.List;

public class TechUtils {
    private static final String TAG = TechUtils.class.getSimpleName();
    private static final String LOG_TAG = TechUtils.class.getSimpleName();
    private static final int MAX_CONNECTION_TIMEOUT= (Constants.CONNECT_TIMEOUT);
    private static final int MAX_READ_TIME= (Constants.READ_TIMEOUT);

    public static String StringUrlBuilder(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter((Constants.ORDER_BY_PARAM), (Constants.NEWEST_PARAM))
                .appendQueryParameter((Constants.PAGE_SIZE_PARAM), (Constants.NEWS_QUERY_NUM_PARAM))
                .appendQueryParameter((Constants.SHOW_TAGS_PARAM), (Constants.CONTRIBUTOR_PARAM))
                .appendQueryParameter((Constants.SHOW_FIELDS_PARAM), (Constants.JSON_KEY_THUMBNAIL))
                .appendQueryParameter((Constants.QUERY_PARAM), "technology, science")
                .appendQueryParameter((Constants.API_KEY_PARAM), (Constants.API_KEY));
        String url = builder.build().toString();
        return url;
    }

    public static URL createUrl(){
        Log.i(LOG_TAG, "TEST:  CreateUrl is called");
        String stringUrl = StringUrlBuilder();
        try {
            return new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "CreateUrl: Error creating URL", e);
            return null;
        }
    }

    public static String makeHttpConnection(URL url) throws IOException {
        Log.i(LOG_TAG, "TEST:  makeHttpConnection is called");
        String jsonResponse = " ";

        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod((Constants.REQUEST_METHOD_GET));
            urlConnection.setConnectTimeout(MAX_CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(MAX_READ_TIME);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == urlConnection.HTTP_OK){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(TAG, "makeHttpConnection: problem establishing connection", e);
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        Log.i(LOG_TAG, "TEST:  readFromStream is called");
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                stringBuilder.append(line);
                line = reader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    public static List<NewsFeed> extractFeatureFromJson(String jsonResponse) {
        Log.i(LOG_TAG, "TEST:  extractFeatureFromJson is called");

        List<NewsFeed> newsFeeds = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            JSONObject responseJSONObject = baseJsonResponse.getJSONObject((Constants.JSON_KEY_RESPONSE));

            JSONArray newsFeedArray = responseJSONObject.getJSONArray((Constants.JSON_KEY_RESULTS));

            for (int i = 0; i< newsFeedArray.length(); i++){
                JSONObject currentNewsFeed = newsFeedArray.getJSONObject(i);
                JSONObject fields = currentNewsFeed.getJSONObject((Constants.JSON_KEY_FIELDS));
                String image = fields.getString((Constants.JSON_KEY_THUMBNAIL));
                String webTitle = currentNewsFeed.getString((Constants.JSON_KEY_WEB_TITLE));
                String date = currentNewsFeed.getString((Constants.JSON_KEY_WEB_PUBLICATION_DATE));
                String url = currentNewsFeed.getString((Constants.JSON_KEY_WEB_URL));


                JSONArray tagsArray = currentNewsFeed.getJSONArray((Constants.JSON_KEY_TAGS));

                String author = " ";
                if (tagsArray.length() == 0) {
                    author = null;
                } else {
                    for (int j = 0; j<tagsArray.length();j++) {
                        JSONObject contributorTag = tagsArray.getJSONObject(j);
                        author = contributorTag.getString((Constants.JSON_KEY_WEB_TITLE));
                    }
                }
                NewsFeed newsFeed = new NewsFeed(image,webTitle,date,author,url);
                newsFeeds.add(newsFeed);
            }
        } catch (JSONException e) {
            Log.e("TechUtils", "Problem parsing  NewsFeed JSON result", e);
        }
        return newsFeeds;
    }
}