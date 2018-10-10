package com.adolphchris.android.guardiannews.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.adolphchris.android.guardiannews.Utils.NewsUtils;
import com.adolphchris.android.guardiannews.UI.NewsFeed;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsFeedLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = NewsFeedLoader.class.getSimpleName();

    public NewsFeedLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS:NEwsFeedLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: NewsFeedLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: NewsFeedLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = NewsUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = NewsUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = NewsUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}
