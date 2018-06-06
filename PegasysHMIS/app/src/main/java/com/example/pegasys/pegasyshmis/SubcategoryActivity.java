package com.example.pegasys.pegasyshmis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.pegasys.pegasyshmis.model.RecycleAdapter;
import com.example.pegasys.pegasyshmis.model.specDoclist;

import java.util.ArrayList;

public class SubcategoryActivity extends AppCompatActivity {

    private RecycleAdapter adapter;
    private RecyclerView rv_listDocs;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<specDoclist> list = new ArrayList<>();
    private String specialName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCompos();

        layoutManager = new LinearLayoutManager(this);
        rv_listDocs.setLayoutManager(layoutManager);
    }

    private void initCompos() {
        setrefers();
        setOnclicks();
        loadDataIntoView();
    }

    private void setrefers() {
        rv_listDocs = (RecyclerView) findViewById(R.id.rv_doclist);
    }

    private void setOnclicks() {

    }

    private void loadDataIntoView() {
        Intent intent = getIntent();
        if (intent != null) {
            specialName = intent.getStringExtra("SPECIALNAME");
        }

        list.add(new specDoclist("1", "Dr.Rao", "10", "2", "9966332910", ""));
        list.add(new specDoclist("2", "Dr.Pradeep", "5", "3", "9966910255", ""));
        list.add(new specDoclist("3", "Dr.Naren", "14", "5", "9966391055", ""));
        list.add(new specDoclist("45", "Dr.Ramesh", "24", "9", "9969102255", ""));
        list.add(new specDoclist("5", "Dr.Srinivas", "18", "10", "9910332255", ""));
        list.add(new specDoclist("346", "Dr.Nag", "12", "11", "9966910255", ""));
        list.add(new specDoclist("36", "Dr.Srija", "2", "12", "9966910255", ""));
        list.add(new specDoclist("431", "Dr.Laxmi", "20", "15", "9991032255", ""));
        list.add(new specDoclist("61", "Dr.Misbha", "10", "16", "9991032255", ""));
        list.add(new specDoclist("15", "Dr.Prasanth", "10", "18", "9106332255", ""));
        list.add(new specDoclist("6", "Dr.Mahendra", "25", "18", "9910332255", ""));
        list.add(new specDoclist("167", "Dr.Revanth", "15", "18", "9106332255", ""));
        list.add(new specDoclist("71", "Dr.Goutham", "10", "19", "9910332255", ""));
        list.add(new specDoclist("67", "Dr.Raghu", "10", "20", "9969102255", ""));
        list.add(new specDoclist("9", "Dr.Dillep", "5", "20", "9966910255", ""));
        list.add(new specDoclist("81", "Dr.Babu", "3", "21", "9966391055", ""));

        adapter = new RecycleAdapter(this, list, specialName);
        rv_listDocs.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent1 = new Intent(SubcategoryActivity.this, DescriptionActivity.class);
                intent1.putExtra("NAME", list.get(position).Doc_name);
                intent1.putExtra("EXPERIANCE", list.get(position).experience);
                intent1.putExtra("DISTANCE", list.get(position).distance);
                intent1.putExtra("PHONE", list.get(position).phone);
                intent1.putExtra("SPECIAL", specialName);
                startActivity(intent1);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
