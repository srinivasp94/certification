package com.srinivas.com.distrct.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.srinivas.com.distrct.R;

public class SingleSchoolActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    private String scl_phone;
    private String scl_Addres;
    private String scl_name;
    private TextView tv_name, tv_address, tv_phone;
    private Button btn_book;
    private ImageView backButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_school);

        intent = getIntent();
        scl_name = intent.getStringExtra("SchoolName");
        scl_Addres = intent.getStringExtra("SchoolAddress");
        scl_phone = intent.getStringExtra("SchoolPhone");

        initComponents();
    }

    private void initComponents() {
        setReferences();
        setonclicklistners();
    }

    private void setReferences() {
        tv_name = (TextView) findViewById(R.id.txt_sclname);
        tv_address = (TextView) findViewById(R.id.txt_school_address);
        backButon = (ImageView) findViewById(R.id.imageView);

        tv_name.setText("" + scl_name);
        tv_address.setText(scl_Addres);
    }

    private void setonclicklistners() {
        backButon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                finish();
                break;
        }
    }
}
