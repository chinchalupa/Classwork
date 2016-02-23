package com.example.jeremy.comicviewer;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GetComicTask.ComicConsumer {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ComicPagerAdapter mComicPagerAdapter;

    public static ComicFragment fragment;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mComicPagerAdapter = new ComicPagerAdapter(getSupportFragmentManager(), getLayoutInflater());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mComicPagerAdapter);
        final int xkcd = Utils.getRandomCleanIssue();
//        mComicPagerAdapter.addComic(ComicFragment.newInstance(mComicPagerAdapter.getCount(), xkcd));
//        mComicPagerAdapter.addComic(fragment);
        String message = "Spiders are dangerous. Please do not eat them.";
        addXKCDComic(R.drawable.alternate_universe, message);
        message = "I always hated tail recursion...";
        addXKCDComic(R.drawable.functional, message);
        message = "Giraffes should not be genetically modified at home.";
        addXKCDComic(R.drawable.giraffes, message);
        message = "I'm going to hate my alarm this morning.";
        addXKCDComic(R.drawable.phone_alarm, message);
        message = "Someday I'll ride a horse.";
        addXKCDComic(R.drawable.shadowfacts, message);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addXKCDComic(R.drawable.quadcopter, "The future is here!");
            }
        });

    }

    public void addXKCDComic(int drawable, String message) {
        int xkcd = Utils.getRandomCleanIssue();
        String urlString = String.format("http://xkcd.com/%d/info.0.json", xkcd);
        fragment = ComicFragment.newInstance(mComicPagerAdapter.getCount(), xkcd, drawable, message);
        mComicPagerAdapter.addComic(fragment);

//        new GetComicTask(this).execute(urlString);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComicLoaded(Comic comic) {

        Log.d("TITLE: ", comic.getTitle());
    }
}
