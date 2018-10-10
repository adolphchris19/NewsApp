package com.adolphchris.android.guardiannews.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.adolphchris.android.guardiannews.UI.NewsFeed;
import com.adolphchris.android.guardiannews.Utils.TechUtils;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TechLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = TechLoader.class.getSimpleName();

    public TechLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: TechLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: TechLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: TechLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = TechUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = TechUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = TechUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

