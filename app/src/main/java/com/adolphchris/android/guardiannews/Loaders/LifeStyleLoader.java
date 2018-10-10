package com.adolphchris.android.guardiannews.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.adolphchris.android.guardiannews.Utils.LifeStyleUtils;
import com.adolphchris.android.guardiannews.UI.NewsFeed;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class LifeStyleLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = LifeStyleLoader.class.getSimpleName();

    public LifeStyleLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: lifestyleLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: lifestyleLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: lifestyleLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed is called");
        URL url = LifeStyleUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = LifeStyleUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = LifeStyleUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}
