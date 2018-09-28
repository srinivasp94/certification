package com.tiqs.rapmedix;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/**
 * Created by TechIq on 7/27/2017.
 */

public class Prescription_List extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prescription_imgae);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SubsamplingScaleImageView viewPager = (SubsamplingScaleImageView) findViewById(R.id.view_pager);

        Intent intent = getIntent();


    }
}
