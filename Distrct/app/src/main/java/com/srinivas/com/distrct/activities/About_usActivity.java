package com.srinivas.com.distrct.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.srinivas.com.distrct.R;

public class About_usActivity extends AppCompatActivity {
    private ImageView backbutton;
    private TextView aout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        backbutton = (ImageView) findViewById(R.id.imageView);
        aout = (TextView) findViewById(R.id.about);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        aout.setText(Html.fromHtml("<small><pre>" + getResources().getString(R.string.abouttwo) + "</pre></small>"));

    }
}
