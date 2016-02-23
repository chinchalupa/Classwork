package com.example.jeremy.comicviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 1/14/2016.
 */
public class ComicPagerAdapter extends FragmentPagerAdapter {

    private List<ComicFragment> comics;
    private LayoutInflater mInflater;

    public ComicPagerAdapter(FragmentManager fm, LayoutInflater mInflater) {
        super(fm);
        this.comics = new ArrayList<>();
        this.mInflater = mInflater;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return this.comics.size();
    }

    @Override
    public Fragment getItem(int position) {
        return this.comics.get(position);
    }

    public List<ComicFragment> getComics() {
        return comics;
    }

    public void addComic(ComicFragment comic) {
        ComicFragment previousFragment;
        if(!this.comics.isEmpty()) {
            previousFragment = this.comics.get(this.comics.size() - 1);
            comic.setPreviousIssueNumber(previousFragment.getIssueNumber());
            Log.d("ISSUE NUMBER: ", "" + comic.getIssueNumber());
            previousFragment.setNextIssueNumber(comic.getIssueNumber());
        }
        this.comics.add(comic);
        this.notifyDataSetChanged();

        Log.d("Change", "Changed dataset: " + this.comics.size());
    }
}
