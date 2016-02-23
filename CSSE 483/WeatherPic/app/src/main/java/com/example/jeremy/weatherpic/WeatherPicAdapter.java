package com.example.jeremy.weatherpic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 1/21/2016.
 */
public class WeatherPicAdapter extends RecyclerView.Adapter<WeatherPicAdapter.ViewHolder> {

    private List<WeatherPic> allWeatherList;
    private List<WeatherPic> weatherPicList;
    private List<WeatherPic> yourPicList;
    private Firebase mWeatherPicsRef;
    private onWeatherItemListener mCallback;
    private boolean isAllVisible;


    public WeatherPicAdapter(WeatherPicListFragment ownerFragment, String repoUrl) {
        mCallback = (onWeatherItemListener) ownerFragment;
        this.allWeatherList = new ArrayList<>();
        this.weatherPicList = new ArrayList<>();
        this.yourPicList = new ArrayList<>();
        this.mWeatherPicsRef = new Firebase(repoUrl);
        this.mWeatherPicsRef.addChildEventListener(new WeatherEventListener());
        this.isAllVisible = false;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WeatherPic weatherPic = weatherPicList.get(position);
        holder.mName.setText(weatherPic.getName());
        holder.mUrl.setText(weatherPic.getUrl());

        final WeatherPicAdapter adapter = this;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.loadItem(weatherPicList.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                WeatherPic pic = weatherPicList.get(position);

                if(mWeatherPicsRef.getAuth().getUid().equals(pic.getUid())) {
                    mCallback.editItem(weatherPicList.get(position), adapter);
                } else {
                    mCallback.displayError();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.weatherPicList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name_text);
            mUrl = (TextView) itemView.findViewById(R.id.url_text);
        }
    }

    public void updateWeather(WeatherPic pic, String newName, String newUrl) {
        pic.setName(newName);
        pic.setUrl(newUrl);
        mWeatherPicsRef.child(pic.getKey()).setValue(pic);
    }

    public void removeWeather(WeatherPic pic) {
        mWeatherPicsRef.child(pic.getKey()).removeValue();
    }

    public void addWeather(String name, String url, String auth) {
        WeatherPic pic = new WeatherPic(name, url, auth);
        mWeatherPicsRef.push().setValue(pic);
    }



    public void setAllVisible(boolean isAllVisible) {
        this.isAllVisible = isAllVisible;
        swapData(isAllVisible);
    }

    public boolean isAllVisible() {
        return isAllVisible;
    }

    public void swapData(boolean isAllVisible) {
        if(isAllVisible) {
            this.weatherPicList = allWeatherList;
        } else {
            this.weatherPicList = yourPicList;
        }
        notifyDataSetChanged();
    }

    public interface onWeatherItemListener {
        void loadItem(WeatherPic weatherPic);

        void editItem(WeatherPic pic, WeatherPicAdapter adapter);

        void displayError();
    }

    private class WeatherEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            WeatherPic weather = dataSnapshot.getValue(WeatherPic.class);
            weather.setKey(dataSnapshot.getKey());
            allWeatherList.add(0, weather);

            if(mWeatherPicsRef.getAuth() == null) {

            } else {
                if (mWeatherPicsRef.getAuth().getUid().equals(weather.getUid())) {
                    yourPicList.add(0, weather);
                }
            }
            swapData(isAllVisible);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            WeatherPic weatherPic = dataSnapshot.getValue(WeatherPic.class);
            for(WeatherPic pic : weatherPicList) {
                if(pic.getKey().equals(key)) {
                    pic.setValues(weatherPic);
                    break;
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for(WeatherPic pic : weatherPicList) {
                if(pic.getKey().equals(key)) {
                    weatherPicList.remove(pic);
                    allWeatherList.remove(pic);
                    break;
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }
}
