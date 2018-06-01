package com.srinivas.com.distrct.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.srinivas.com.distrct.R;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner s_categorie, s_subCate;
    private String[] categorie_items = {"Select Categorie", "Education", "Hospitality", "RealEstate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initComponents();
    }

    private void initComponents() {
        setReferences();
        setOnClickListners();
    }

    private void setReferences() {
        s_categorie = (Spinner) findViewById(R.id.spinner_categorie);
        s_subCate = (Spinner) findViewById(R.id.spinnr_subcategorie);
    }

    private void setOnClickListners() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorie_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_categorie.setAdapter(adapter);
        s_categorie.setOnItemSelectedListener(this);
        s_subCate.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()) {
            case R.id.spinner_categorie:
                selectCategorie(i);
                break;
        }
    }

    private void selectCategorie(int position) {
        if (position == 1) {
            Toast.makeText(this, "Please select Categorie", Toast.LENGTH_SHORT).show();
        } else {

            ArrayAdapter<String> adapterSub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorie_items);
            adapterSub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s_subCate.setAdapter(adapterSub);
            s_subCate.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
