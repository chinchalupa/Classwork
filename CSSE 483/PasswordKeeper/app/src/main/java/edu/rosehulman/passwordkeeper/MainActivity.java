package edu.rosehulman.passwordkeeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, PasswordFragment.OnLogoutListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Firebase.setAndroidContext(this);
        }
        switchToPasswordFragment(Constants.FIREBASE_URL);
    }

    private boolean isExpired(AuthData authData) {
        return (System.currentTimeMillis() / 1000) >= authData.getExpires();
    }

    @Override
    public void onLogin(String email, String password) {
        //TODO: Log user in with username & password
    }

    @Override
    public void onGoogleLogin() {
        //TODO: Log user in with Google Account
    }

    private void onGoogleLoginWithToken(String oAuthToken) {
        //TODO: Log user in with Google OAuth Token
    }

    @Override
    public void onRosefireLogin(String email, String password) {

    }

    @Override
    public void onLogout() {
        //TODO: Log the user out.
    }

    // MARK: Provided Helper Methods
    private void switchToLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, new LoginFragment(), "Login");
        ft.commit();
    }

    private void switchToPasswordFragment(String repoUrl) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment passwordFragment = new PasswordFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FIREBASE, repoUrl);
        passwordFragment.setArguments(args);
        ft.replace(R.id.fragment, passwordFragment, "Passwords");
        ft.commit();
    }

    private void showLoginError(String message) {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("Login");
        loginFragment.onLoginError(message);
    }

}
