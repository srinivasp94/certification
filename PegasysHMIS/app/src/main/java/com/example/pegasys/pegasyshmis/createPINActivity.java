package com.example.pegasys.pegasyshmis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pegasys.pegasyshmis.model.ResponseModel;
import com.example.pegasys.pegasyshmis.network.APIInterface;
import com.example.pegasys.pegasyshmis.network.APIUtils;
import com.example.pegasys.pegasyshmis.network.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createPINActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_pin, et_cnfPin;
    private Button submit;
    private RelativeLayout layout;
    private APIInterface mInterface;
    private String uId, userPin;
    private SharedPreferences preferences;
    private static final String PREF_NAME = "mypreferences";
    private ProgressDialog pDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);
        pDialog = new ProgressDialog(this);
        initComponents();

    }

    private void initComponents() {
        setreferences();
        setonclick();
    }

    private void setreferences() {
        et_pin = (EditText) findViewById(R.id.edt_pin);
        et_cnfPin = (EditText) findViewById(R.id.edt_confirmpin);

        submit = (Button) findViewById(R.id.btn_updatepin);
        layout = (RelativeLayout)findViewById(R.id.rl_root);
    }

    private void setonclick() {
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_updatepin:
                mInterface = APIUtils.getAPIService();
                preferences = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                uId = preferences.getString("USER_ID", null);

                if (Common.haveInternet(this) == true) {
                    if (et_pin.getText().toString().equals(et_cnfPin.getText().toString())) {
                        callupdatePinWS();
                    } else {
                        Toast.makeText(this, "Pin not matched", Toast.LENGTH_SHORT).show();
                        Snackbar snackbar1 = Snackbar.make(layout,"Pin not matched",Snackbar.LENGTH_LONG);
                        snackbar1.show();
                    }

                } else {
                    Snackbar snackbar = Snackbar.make(layout,"No Internet Connections",Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    snackbar.show();
                    snackbar.setActionTextColor(Color.RED);
                }
                break;
        }
    }

    private void callupdatePinWS() {
        pDialog.show();
        pDialog.setCancelable(false);
        mInterface.callupdatePin(uId, et_pin.getText().toString()).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pDialog.dismiss();
                ResponseModel model = response.body();
                if (model.status == true) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("PIN", et_pin.getText().toString());
                    Log.i("PIN##", preferences.getString("PIN", null) + "  " + et_pin.getText().toString());
                    editor.apply();

                    Toast.makeText(createPINActivity.this, "" + model.message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(createPINActivity.this, ViewAllPdfsActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(createPINActivity.this, "" + model.message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(createPINActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
