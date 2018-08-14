package com.android.Uga;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Chapal on 11-Jun-16.
 */
public class Firstactivity extends Activity {
    private int level;
    private int screenHeight;
    private int screenWidth;
    TextView georgiaTV,departmentTV,poultryTV,foundationTV;
    Typeface typeface1,typeface2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.activity_main1);
        typeface1= Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"Uga_fonts/Montserrat_Bold.ttf");

        georgiaTV=(TextView)findViewById(R.id.georgiaTV);
        departmentTV=(TextView)findViewById(R.id.departmentTV);
        poultryTV=(TextView)findViewById(R.id.poultryTV);
        foundationTV=(TextView)findViewById(R.id.foundationTV);

        georgiaTV.setTypeface(typeface2);
        departmentTV.setTypeface(typeface1);
        poultryTV.setTypeface(typeface2);
        foundationTV.setTypeface(typeface2);

        start();
    }

    public void start(){
    Thread Splash = new Thread() {
        @Override
        public void run() {
            try {
                super.run();
                sleep(1000);
            } catch (Exception e) {
                Log.d("hai", e + "");
            } finally {


                Intent intent = new Intent(Firstactivity.this, MainActivity.class);
                startActivity(intent);
                Firstactivity.this.finish();
            }
        }
    };
    Splash.start();
}

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}