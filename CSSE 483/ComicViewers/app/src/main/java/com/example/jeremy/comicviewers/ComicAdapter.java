package com.example.jeremy.comicviewers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Jeremy on 1/13/2016.
 */
public class ComicAdapter extends FragmentPagerAdapter {

    private List<Fragment> comicList;

    public ComicAdapter(FragmentManager supportFragmentManager, List<Fragment> fragments) {
        super(supportFragmentManager);
        this.comicList = fragments;
        Log.d("DATA: ", "" + this.comicList.size());
    }

    @Override
    public Fragment getItem(int position) {
        return this.comicList.get(position);
    }

    @Override
    public int getCount() {
        return this.comicList.size();
    }
}
