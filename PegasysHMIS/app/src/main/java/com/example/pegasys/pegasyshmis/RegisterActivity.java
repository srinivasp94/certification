package com.example.pegasys.pegasyshmis;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pegasys.pegasyshmis.model.ResponseModel;
import com.example.pegasys.pegasyshmis.network.APIInterface;
import com.example.pegasys.pegasyshmis.network.APIUtils;
import com.example.pegasys.pegasyshmis.network.Common;
import com.example.pegasys.pegasyshmis.network.RetrofitResponseListener;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RetrofitResponseListener {
    private Button button_submit;
    private TextView textView;
    private EditText txt_name, txt_lastname, txt_username, txt_email, txt_password;
    private Object obj;
    private String id;
    private APIInterface mInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //removes the overdraw by nullifying the background
//        getWindow().setBackgroundDrawable(null);

        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i("DeviceID", id);
        initcomponents();

    }

    private void initcomponents() {
        setReferences();
        setonclicklistners();

    }

    private void setReferences() {
        button_submit = (Button) findViewById(R.id.bt_submit);
        textView = (TextView) findViewById(R.id.tv_login);

        txt_name = (EditText) findViewById(R.id.name_reg);
        txt_lastname = (EditText) findViewById(R.id.edt_lastname);
        txt_username = (EditText) findViewById(R.id.edt_username);
        txt_email = (EditText) findViewById(R.id.edt_email_reg);
        txt_password = (EditText) findViewById(R.id.edt_pwd_reg);
        //txt_name = (EditText) findViewById(R.id.name_reg);
    }

    private void setonclicklistners() {
        button_submit.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                if (txt_name.getText().toString().equals("") || txt_lastname.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    mInterface = APIUtils.getAPIService();
                    sendDatatoPost();

                    //----------
                    /*SignupModel signupModel = new SignupModel();

                    signupModel.firstname = txt_name.getText().toString();
                    signupModel.lastname = txt_lastname.getText().toString();
                    signupModel.username = txt_username.getText().toString();
                    signupModel.email = txt_email.getText().toString();
                    signupModel.password = txt_password.getText().toString();
                    signupModel.deviceid = id;
                    signupModel.devicelocation = "Hyderabad";

                    try {
                        obj = Class.forName(SignupModel.class.getName()).cast(signupModel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    new RetrofitRequester(this).callPostServices(obj, 1, "/pdfparser/api/MobileService/signupService");*/

                }
                break;
            case R.id.tv_login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void sendDatatoPost() {
//        APIInterface

        mInterface.callSignup(txt_name.getText().toString(),
                txt_lastname.getText().toString(),
                txt_username.getText().toString(),
                txt_email.getText().toString(),
                txt_password.getText().toString(),
                id,
                "Hyderabad").enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.i("Success", response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.i("Failure", t.toString());
            }
        });

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse.equals("") && objectResponse == null) {
            Toast.makeText(this, "Please Try again", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new Gson();
            ResponseModel responseModel = Common.getSpecificDataObject(objectResponse, ResponseModel.class);
            if (responseModel.status.equals("true")) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "" + responseModel.status + "\n" + responseModel.message, Toast.LENGTH_LONG).show();
            }


        }

    }
}
