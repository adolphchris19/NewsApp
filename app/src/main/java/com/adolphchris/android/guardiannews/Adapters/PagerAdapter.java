package com.adolphchris.android.guardiannews.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.adolphchris.android.guardiannews.Fragments.LifestyleFragment;
import com.adolphchris.android.guardiannews.Fragments.NewsFragment;
import com.adolphchris.android.guardiannews.Fragments.SportFragment;
import com.adolphchris.android.guardiannews.Fragments.TechnologyFragment;
import com.adolphchris.android.guardiannews.R;

public class PagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewsFragment();
        } else if (position == 1) {
            return new TechnologyFragment();
        } else if (position == 2) {
            return new SportFragment();
        } else {
            return new LifestyleFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.news);
        } else if (position == 1){
            return mContext.getString(R.string.technology);
        } else if (position == 2){
            return mContext.getString(R.string.sport);
        } else {
            return mContext.getString(R.string.lifestyle);
        }
    }
}
