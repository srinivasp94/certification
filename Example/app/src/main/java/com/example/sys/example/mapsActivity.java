package com.example.sys.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class mapsActivity extends AppCompatActivity {
    TextView loca, click;
    private int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        loca = (TextView) findViewById(R.id.locationtext);
        click = (TextView) findViewById(R.id.setloca);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mapsActivity.this, MapActivity.class);
                startActivityForResult(intent, 10);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

                String requiredValue = data.getStringExtra("Key");
                String str_loca = data.getStringExtra("Address");
                loca.setText(str_loca);
            }
        } catch (Exception ex) {
            Toast.makeText(mapsActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }


    }
}
