package com.example.redessociales;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.btnShare);
        shareButton.setShareContent(content);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this,"Usuario onSucces",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this,"Usuario onCancel",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this,"Usuario onError",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(MainActivity.this,"Usuario ya con Sesión",Toast.LENGTH_LONG).show();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null &&! accessToken.isExpired();

        if (isLoggedIn){
            Toast.makeText(MainActivity.this,"Usuario ya con sesión onAc"+accessToken.toString(),Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"Sin Token de Acceso onAc",Toast.LENGTH_LONG).show();
        }

    }
}