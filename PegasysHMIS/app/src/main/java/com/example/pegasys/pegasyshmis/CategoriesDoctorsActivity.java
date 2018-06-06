package com.example.pegasys.pegasyshmis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pegasys.pegasyshmis.adapters.AllHealthrecordsGridviewAdapter;
import com.example.pegasys.pegasyshmis.model.AllHealthRecords;

import java.util.ArrayList;

public class CategoriesDoctorsActivity extends AppCompatActivity {
    private GridView cate_docs;
    private AllHealthrecordsGridviewAdapter gridviewAdapter;
    private ArrayList<AllHealthRecords> arrayList = new ArrayList<>();
//    private AllHealthRecords records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_doctors);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initcomponents();
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

    private void initcomponents() {
        setrefers();
        setOnclicks();
        loadDataIntoView();
    }

    private void loadDataIntoView() {
        AllHealthRecords records;
        new AllHealthRecords("Cardiology", R.drawable.doc_color);
        arrayList.add(new AllHealthRecords("Cardiology", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Allergists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Anesthesiologists. ", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Cardiologists. ", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Rectal Surgeons. ", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Dermatologists. ", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Endocrinologists. ", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Emergency Specialists. ", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Family Physicians", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Immunologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Anesthesiologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Cardiologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Colon Surgeons.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Dermatologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Endocrinologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Medicine Specialists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Anesthesiologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Cardiologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Dermatologists.", R.drawable.doc_color));
        arrayList.add(new AllHealthRecords("Endocrinologists.", R.drawable.doc_color));


        gridviewAdapter = new AllHealthrecordsGridviewAdapter(this, arrayList);
        cate_docs.setAdapter(gridviewAdapter);
        cate_docs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CategoriesDoctorsActivity.this, SubcategoryActivity.class);
                intent.putExtra("SPECIALNAME", arrayList.get(i).getName());
                startActivity(intent);

            }
        });

    }

    private void setrefers() {
        cate_docs = (GridView) findViewById(R.id.grid_categories);
    }

    private void setOnclicks() {

    }
}
