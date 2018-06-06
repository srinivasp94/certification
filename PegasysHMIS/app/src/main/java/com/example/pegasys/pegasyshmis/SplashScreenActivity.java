package com.example.pegasys.pegasyshmis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView textView;
    private static int SPLASH_TIME_OUT = 3000;
    private SharedPreferences mPreferences;
    private static final String PREF_NAME = "mypreferences";
    private String name,uid,u_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initUI();

    }

    private void initUI() {
        textView = (TextView)findViewById(R.id.loading);
        mPreferences = getApplicationContext().getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        uid = mPreferences.getString("USER_ID",null);
        u_pin= mPreferences.getString("PIN",null);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               if (uid != null) {
                   Intent intent = new Intent(SplashScreenActivity.this, VerifyPinActivity.class);
                   startActivity(intent);
                   finish();
               } else {
                   Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                   startActivity(intent);
                   finish();
               }
           }
       },SPLASH_TIME_OUT);

    }
}
