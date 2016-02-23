package com.example.jeremy.weatherpic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements WeatherPicListFragment.WeatherListListener, WeatherPicFragment.WeatherPicListener, LoginFragment.OnLoginListener {


    private static final String PATH = "https://wrightjt-weatherpics.firebaseio.com/";
    private static final String WEATHER_PATH = "weather";
    private static final String USERS = "users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null) {
            Firebase.setAndroidContext(this);
        }

        Firebase firebase = new Firebase(PATH);
        if(firebase.getAuth() == null || isExpired(firebase.getAuth())) {
            switchToLoginFragment();
        } else {
            switchToListFragment(PATH + "users/" + firebase.getAuth().getUid());
        }
    }

    private boolean isExpired(AuthData authData) {
        return (System.currentTimeMillis() / 1000) >= authData.getExpires();
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
        else if(id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchToListFragment(String repoUrl) {
        Fragment fragment = WeatherPicListFragment.newInstance(repoUrl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragment, "list");
        ft.commit();
    }

    private void switchToLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, new LoginFragment(), "list");
        ft.commit();
    }



    @Override
    public void loadItem(WeatherPic weatherPic) {
        Fragment fragment = WeatherPicFragment.newInstance(weatherPic.getName(), weatherPic.getUrl());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragment, "item");
        ft.addToBackStack("List");
        ft.commit();
    }

    public void openDialog(final WeatherPic pic, final WeatherPicAdapter adapter) {
        DialogFragment dialogFragment = new DialogFragment() {

            private final Firebase firebase = new Firebase(PATH);

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                if (pic != null) {
                    builder.setTitle("Edit the weather");
                } else {
                    builder.setTitle("Create weather");
                }
                View view = LayoutInflater.from(getContext()).inflate(R.layout.creation_dialog, null);

                final EditText nameField = (EditText) view.findViewById(R.id.new_image_name);
                final EditText urlField = (EditText) view.findViewById(R.id.new_image_url);
                if (pic != null) {
                    nameField.setText(pic.getName());
                    urlField.setText(pic.getUrl());
                }

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameField.getText().toString();
                        String url = urlField.getText().toString();
                        String uid = firebase.getAuth().getUid();

                        if(url.length() == 0) {
                            url = Util.randomImageUrl();
                        }

                        if (pic != null) {
                            adapter.updateWeather(pic, name, url);
                        } else {
                            adapter.addWeather(name, url, uid);
                        }
                    }
                });

                if (pic != null) {
                    builder.setNeutralButton("delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.removeWeather(pic);
                        }
                    });
                }

                builder.setView(view);
                return builder.create();
            }

        };
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onLogin(String email, String password) {
        Firebase firebase = new Firebase(PATH);
        firebase.authWithPassword(email, password, new MyAuthResultHandler());
    }

    @Override
    public void onTwitterLogin() {
//        HashMap<String, String> options = new HashMap<>();
//        options.put("oauth_token", getResources().getString(R.string.twitter_app_key));
//        options.put("oauth_token_secret", getResources().getString(R.string.twitter_app_secret));
//        options.put("user_id", "MaximusChilus");
//
//        Firebase firebase = new Firebase(PATH);
//        Log.d("LOGIN", "login");
//        firebase.authWithOAuthToken("twitter", options, new MyAuthResultHandler());
        startActivityForResult(new Intent(this, TwitterOAuthActivity.class), 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, String> options = new HashMap<>();
        if (requestCode == 1) {
            options.put("oauth_token", data.getStringExtra("oauth_token"));
            options.put("oauth_token_secret", data.getStringExtra("oauth_token_secret"));
            options.put("user_id", data.getStringExtra("user_id"));
            authWithFirebase("twitter", options);
        }
    }

    private void authWithFirebase(String auth, Map<String, String> option) {
        Firebase firebase = new Firebase(PATH);
        Log.d("LOGIN", "login");
        Log.d("Token", option.get("oauth_token"));
        Log.d("User_id", option.get("user_id"));
        if(option.get("error") == null) {
            firebase.authWithOAuthToken(auth, option, new MyAuthResultHandler());
            switchToListFragment(PATH);
        }
    }

    public void logout() {
        Firebase firebase = new Firebase(PATH);
        firebase.unauth();
        switchToLoginFragment();
    }

    @Override
    public void editWeather(WeatherPic pic, WeatherPicAdapter adapter) {
        openDialog(pic, adapter);
    }

    @Override
    public void addWeather(WeatherPicAdapter adapter) {
        openDialog(null, adapter);
    }



    class MyAuthResultHandler implements Firebase.AuthResultHandler {
        @Override
        public void onAuthenticated(AuthData authData) {
            switchToListFragment(PATH + "users/" + authData.getUid());
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Log.d("Login", "Login error");
        }
    }
}
