package com.example.jeremy.weatherpics;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by Jeremy on 1/21/2016.
 */
public class WeatherPicAdapter extends RecyclerView.Adapter<WeatherPicAdapter.ViewHolder> {

    public static final String PATH = "https://wrightjt-weatherpics.firebaseio.com/weather";
    private List<WeatherPic> weatherPicList;
    private Firebase mWeatherPicsRef;
    private DialogFragmentCallback mCallback;

    public WeatherPicAdapter(DialogFragmentCallback callback, Context context) {
        mCallback = callback;
        this.weatherPicList = new ArrayList<>();
        Firebase.setAndroidContext(context);
        mWeatherPicsRef = new Firebase(PATH);
        mWeatherPicsRef.addChildEventListener(new WeatherPicsChildEventListener());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WeatherPic weatherPic = weatherPicList.get(position);
        holder.mName.setText(weatherPic.getName());
        holder.mUrl.setText(weatherPic.getUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onSelect(weatherPicList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.weatherPicList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_row_view, parent, false);
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

    class WeatherPicsChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            WeatherPic pic = dataSnapshot.getValue(WeatherPic.class);
            pic.setKey(dataSnapshot.getKey());
            weatherPicList.add(0, pic);
            notifyDataSetChanged();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }

    public interface DialogFragmentCallback {
        public void onEdit(WeatherPic weatherPic);
        public void onSelect(WeatherPic weatherPic);
    }
}
