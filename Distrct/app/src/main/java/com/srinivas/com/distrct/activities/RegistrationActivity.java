package com.srinivas.com.distrct.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.srinivas.com.distrct.R;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private EditText et_title, et_name, et_mobile, et_address;
    private Spinner spinner_mandal, s_categorie, s_subcategorie;
    private Button btn_register;
    private String[] categorie_items = {"Select Categorie", "Education", "Hospitality", "RealEstate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initComponents();
    }

    private void initComponents() {
        setReferences();
        setonclicklistners();
        setOnClickListners();
    }

    private void setonclicklistners() {
        btn_register.setOnClickListener(this);
    }

    private void setReferences() {
        et_title = (EditText) findViewById(R.id.edt_titlename);
        et_name = (EditText) findViewById(R.id.edt_name);
        et_mobile = (EditText) findViewById(R.id.edt_phone);
        et_address = (EditText) findViewById(R.id.edt_address);
        btn_register = (Button) findViewById(R.id.btn_register);
        spinner_mandal = (Spinner) findViewById(R.id.spinner_mandal);
        s_categorie = (Spinner) findViewById(R.id.spinnr_categorie);
        s_subcategorie = (Spinner) findViewById(R.id.spinnr_subcategorie);
    }

    private void setOnClickListners() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorie_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mandal.setAdapter(adapter);
        spinner_mandal.setOnItemSelectedListener(this);
        s_categorie.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()) {
            case R.id.spinner_mandal:
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
            s_categorie.setAdapter(adapterSub);
            s_categorie.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:

                break;
        }
    }
}
