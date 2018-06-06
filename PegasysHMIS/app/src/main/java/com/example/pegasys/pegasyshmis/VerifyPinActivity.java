package com.example.pegasys.pegasyshmis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VerifyPinActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_pin;
    private Button btn_next;
    private SharedPreferences preferences;
    private static final String PREF_NAME = "mypreferences";
    private String userPin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_pin);
        initUi();

    }

    private void initUi() {
        setreferences();
        setonclicks();
    }

    private void setreferences() {
        editText_pin = (EditText) findViewById(R.id.edt_verifypin);
        btn_next = (Button) findViewById(R.id.btn_next);
    }

    private void setonclicks() {
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                preferences = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                userPin = preferences.getString("PIN", null);
                if (userPin.equals(editText_pin.getText().toString().trim())) {
                    Intent intent = new Intent(VerifyPinActivity.this, ViewAllPdfsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, " Enter Correct PIN ", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
