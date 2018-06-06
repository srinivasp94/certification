package com.example.pegasys.pegasyshmis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pegasys.pegasyshmis.model.LoginResponseModel;
import com.example.pegasys.pegasyshmis.network.APIInterface;
import com.example.pegasys.pegasyshmis.network.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private TextView signup;
    private EditText edt_username, edt_pwd;
    private Spinner spinner_type;
    private String[] spinneritems = {"Select type", "Doctor", "USer"};
    private APIInterface mInterface;
    private SharedPreferences mPreferences;
    private static final String PREF_NAME = "mypreferences";
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(this);
        initUi();
    }

    private void initUi() {
        setReferences();
        setonclicklistners();
        loadDataonUI();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,spinneritems);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_type.setAdapter(adapter);
//        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(LoginActivity.this, "" + i, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }


    private void setReferences() {
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.tv_signup);
        edt_username = (EditText) findViewById(R.id.et_login_username);
        edt_pwd = (EditText) findViewById(R.id.password);
        spinner_type = (Spinner) findViewById(R.id.spinner_select_type);
    }

    private void setonclicklistners() {
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    private void loadDataonUI() {


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (edt_username.getText().toString().equals("") || edt_pwd.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    mInterface = APIUtils.getAPIService();
                    callLoginWS();


                }
                break;
            case R.id.tv_signup:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void callLoginWS() {
        pDialog.show();
        pDialog.setCancelable(false);
        mInterface.callLogin(edt_username.getText().toString(), edt_pwd.getText().toString())
                .enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        pDialog.dismiss();
                        Log.i("Success", response.body().toString());
//                        Gson gson = new GsonBuilder().create();
//                        LoginResponseModel model = gson.fromJson(response.body().toString(),LoginResponseModel.class);
                        LoginResponseModel model = response.body();
                        Log.i("LOGG", model.message);
                        if (model.status == true) {

                            mPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPreferences.edit();
                            editor.putString("USER_ID", model.user.userid);
                            editor.putString("USER_Name", model.user.username);
                            editor.putString("NAME", model.user.firstName + model.user.lastName);
                            editor.putString("PIN", model.user.userpin);
                            editor.putString("User_Status", model.user.userStatus);
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, createPINActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "" +model.message , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                        pDialog.dismiss();
                        Log.i("onFailure", t.toString());
                    }
                });

    }
}
