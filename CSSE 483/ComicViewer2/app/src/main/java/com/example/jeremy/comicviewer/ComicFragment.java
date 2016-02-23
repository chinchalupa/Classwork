package com.example.jeremy.comicviewer;

import android.app.AlertDialog;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Jeremy on 1/14/2016.
 */
public class ComicFragment extends Fragment {

    private int nextIssueNumber;
    private int previousIssueNumber;
    private AlertDialog alertDialog;
    private PhotoViewAttacher mAttacher;



    public ComicFragment() {
        this.nextIssueNumber = 0;
    }

    public static ComicFragment newInstance(int position, int xkcdNum, int image, String message) {
        ComicFragment fragment = new ComicFragment();
        Bundle args = new Bundle();
        args.putInt("Color", position);
        args.putInt("XKCD", xkcdNum);
        args.putInt("Image", image);
        args.putString("Message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.info:
//                Log.d("INFO: ", "info selected");
//                InfoDialogFragment infoDialogFragment = InfoDialogFragment.newInstance("Test header", "Body");
                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                String message = getArguments().getString("Message");
                String header = "Info for Issue " + getArguments().getInt("XKCD");

                builder.setMessage(message);
                builder.setTitle(header);

                alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        getArguments().putInt("Next", this.nextIssueNumber);
        getArguments().putInt("Prev", this.previousIssueNumber);
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.comic_view);
        TextView comicNumberText = (TextView) view.findViewById(R.id.comic_label);
        TextView previousNumberText = (TextView) view.findViewById(R.id.previous_comic_label);
        TextView nextNumberText = (TextView) view.findViewById(R.id.next_comic_label);

        String comicNumber = "Issue " + String.valueOf(getArguments().getInt("XKCD", 0));
        this.nextIssueNumber = getArguments().getInt("Next", 0);
        this.previousIssueNumber = getArguments().getInt("Prev", 0);
        String nextComicNumber = "Issue " + String.valueOf(this.nextIssueNumber);
        String previousComicNumber = "Issue " + String.valueOf(this.previousIssueNumber);
        int position = getArguments().getInt("Color");
        int color = Utils.getColor(position);

        comicNumberText.setText(comicNumber);
        if(this.nextIssueNumber != 0)
            nextNumberText.setText(nextComicNumber);
        else
            nextNumberText.setText("");
        if(this.previousIssueNumber != 0)
            previousNumberText.setText(previousComicNumber);
        else
            previousNumberText.setText("");

        ImageView xkcdImage = (ImageView) view.findViewById(R.id.imageView);
        xkcdImage.setImageResource(getArguments().getInt("Image", 0));

        mAttacher = new PhotoViewAttacher(xkcdImage);


        layout.setBackgroundResource(color);
        return view;
    }

    public void setNextIssueNumber(int number) {
        getArguments().putInt("Next", number);
        this.nextIssueNumber = number;
    }

    public int getNextIssueNumber() {
        return nextIssueNumber;
    }

    public int getIssueNumber() {
        return getArguments().getInt("XKCD", 0);
    }

    public int getPreviousIssueNumber() {
        return previousIssueNumber;
    }

    public void setPreviousIssueNumber(int previousIssueNumber) {
        getArguments().putInt("Prev", previousIssueNumber);
        this.previousIssueNumber = previousIssueNumber;
    }

    public AlertDialog getDialog() {
        return alertDialog;
    }
}
