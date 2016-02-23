package com.example.jeremy.weatherpic;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherPicListFragment.WeatherListListener} interface
 * to handle interaction events.
 * Use the {@link WeatherPicListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherPicListFragment extends Fragment implements WeatherPicAdapter.onWeatherItemListener{

    public static final String PATH = "https://wrightjt-weatherpics.firebaseio.com/weather";

    private WeatherPicAdapter mAdapter;

    private WeatherListListener mListener;

    private static WeatherPicListFragment fragment = null;

    private String repoUrl;

    public WeatherPicListFragment() {
        // Required empty public constructor
    }

    public WeatherPicListFragment getInstance() {
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment WeatherPicListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherPicListFragment newInstance(String repoUrl) {
        fragment = new WeatherPicListFragment();
        Bundle args = new Bundle();
        args.putString("REPO", PATH);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            repoUrl = getArguments().getString("REPO");
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.show_users_uploads:
                if(mAdapter.isAllVisible()) {
                    mAdapter.setAllVisible(false);
                    item.setTitle("Show All");
                } else {
                    mAdapter.setAllVisible(true);
                    item.setTitle("Show Mine");
                }
                break;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_pic_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addWeather(mAdapter);
            }
        });

        this.mAdapter = new WeatherPicAdapter(this, repoUrl);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.recycler_view);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(this.mAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WeatherListListener) {
            mListener = (WeatherListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnWeatherListListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void loadItem(WeatherPic weatherPic) {
        mListener.loadItem(weatherPic);
    }

    @Override
    public void editItem(WeatherPic pic, WeatherPicAdapter adapter) {
        mListener.editWeather(pic, adapter);
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), "Cannot edit item", Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface WeatherListListener {
        // TODO: Update argument type and name
        void addWeather(WeatherPicAdapter adapter);
        void editWeather(WeatherPic pic, WeatherPicAdapter adapter);
        void loadItem(WeatherPic pic);


    }


}
