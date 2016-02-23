package com.example.jeremy.comicviewers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 1/13/2016.
 */
public class ComicViewerActivity extends FragmentActivity {

    private ComicAdapter comicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = getFragments();
        Log.d("Create", "CREATED");

        this.comicAdapter = new ComicAdapter(this.getSupportFragmentManager(), fragments);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(this.comicAdapter);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Comic());
        fragments.add(new Comic());
        return fragments;
    }
}
