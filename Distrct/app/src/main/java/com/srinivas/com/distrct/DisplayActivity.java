package com.srinivas.com.distrct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.srinivas.com.distrct.activities.SingleSchoolActivity;
import com.srinivas.com.distrct.adapters.EducationAdapter;
import com.srinivas.com.distrct.models.educationModels;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_education;
    private EducationAdapter adapter;
    private LinearLayoutManager manager;
    private ArrayList<educationModels> list = new ArrayList<>();
    private ImageView backButon;

//    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        initComponents();
    }

    private void initComponents() {
        setReferences();
        setOnclicklistners();
        preparedataToview();
    }

    private void setReferences() {
        recycler_education = (RecyclerView) findViewById(R.id.rv_education);
        backButon = (ImageView) findViewById(R.id.imageView);


        manager = new LinearLayoutManager(getApplicationContext());
        recycler_education.setLayoutManager(manager);

    }

    private void setOnclicklistners() {
        backButon.setOnClickListener(this);
    }

    private void preparedataToview() {
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        list.add(new educationModels("The Heights Primary School", "Address", "+91 111111111", 1));
        adapter = new EducationAdapter(this, list);
        recycler_education.setAdapter(adapter);
        adapter.setonitemClicklistner(new EducationAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), SingleSchoolActivity.class);
                intent.putExtra("SchoolName",list.get(position).getScl_name());
                intent.putExtra("SchoolAddress",list.get(position).getScl_address());
                intent.putExtra("SchoolPhone",list.get(position).getScl_phonenumber());
                startActivity(intent);
            }
        });

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
