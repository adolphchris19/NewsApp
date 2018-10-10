package com.adolphchris.android.guardiannews.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.adolphchris.android.guardiannews.UI.NewsFeed;
import com.adolphchris.android.guardiannews.Utils.SportUtils;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SportLoader  extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = SportLoader.class.getSimpleName();

    public SportLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: SportLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: SportLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: SportLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = SportUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = SportUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = SportUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

