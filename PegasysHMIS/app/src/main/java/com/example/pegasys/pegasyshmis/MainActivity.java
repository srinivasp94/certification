package com.example.pegasys.pegasyshmis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button desk, prov, invset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initcomponents();
    }


    private void initcomponents() {
        setrefers();
        setOnclicks();
        loadDataIntoView();
    }

    private void setrefers() {
        desk = (Button) findViewById(R.id.desk);
        prov = (Button) findViewById(R.id.provisional);
        invset = (Button) findViewById(R.id.invesatigation);
    }

    private void setOnclicks() {
        desk.setOnClickListener(this);
        prov.setOnClickListener(this);
        invset.setOnClickListener(this);
    }

    private void loadDataIntoView() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.provisional:
                Intent intent = new Intent(MainActivity.this, DoctordeskActivity.class);
                startActivity(intent);
                break;
            case R.id.desk:
                Intent intent1 = new Intent(MainActivity.this, CategoriesDoctorsActivity.class);
                startActivity(intent1);
                break;
            case R.id.invesatigation:
                Intent i= new Intent(MainActivity.this, ForminvestActivity.class);
                startActivity(i);
                break;
        }
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
