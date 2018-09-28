package com.tiqs.rapmedix;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.firebase.receivers.MyFirebaseInstanceIDService;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;
import com.tiqs.rapmedix.utils.User;

import org.json.JSONException;
import org.json.JSONObject;


public class member_login extends Activity {
    EditText mobile_number, pwd_login;
    LinearLayout mobile;
    Button submit;
    User user_data;
    View parentLayout;
    String valid_email, emailPattern;
    String login_page_url;
    TextView register_text, sign_up;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page2);
        parentLayout = findViewById(R.id.root_view);
        mobile_number = (EditText) findViewById(R.id.mobile_number_input);
        pwd_login = (EditText) findViewById(R.id.pwd_login);
        register_text = (TextView) findViewById(R.id.forgot);
        sign_up = (TextView) findViewById(R.id.signup);
        submit = (Button) findViewById(R.id.submit);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Logging in...");

        final SharedPreferences sp = getSharedPreferences(MyFirebaseInstanceIDService.pref, this.MODE_PRIVATE);
        final String Notification = sp.getString("Notification", "Error");
        //Log.e("aa", "ccc" + Notification);
        login_page_url = Constants.login_service;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mobile_number.getText().toString().trim().equals("")) {
                    Snackbar.make(parentLayout, "Enter valid Mobile number", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view)

                                {

                                }
                            })
                            .show();
                } else {

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("mobile", mobile_number.getText().toString().trim());
                        jsonObject.put("password", pwd_login.getText().toString().trim());
                        jsonObject.put("device_token", Notification);
                        jsonObject.put("device_type", "1");
                        pDialog.show();
                        call_custom_asynch(jsonObject, login_page_url);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }


        });

        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_intent = new Intent(member_login.this, Forgot_pwd.class);
                startActivity(signup_intent);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_intent = new Intent(member_login.this, Registation_page.class);
                startActivity(signup_intent);
            }
        });

    }


    private void call_custom_asynch(JSONObject jo, String url) {
        CustomAsync ca = new CustomAsync(member_login.this, jo, url, new OnAsyncCompleteRequest() {

            @Override
            public void asyncResponse(String result) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                if (result == null || result.equals("")) {
                    Toast.makeText(member_login.this, "Please Retry", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject j = new JSONObject(result);
                        String status = j.getString("status");
                        if (status.equals("success")) {
                            String uid = j.getString("user_id");
                            String mobile = j.getString("mobile");
                            String otp = j.getString("otp");
                            if (mobile.equals(mobile_number.getText().toString())) {

                                Intent intent = new Intent(member_login.this, Otp_page.class);
                                intent.putExtra("uid", uid);
                                intent.putExtra("mobile", mobile);
                                intent.putExtra("otp", otp);
                                intent.putExtra("name", j.getString("name"));
                                startActivity(intent);
                                finish();
//                                Toast.makeText(member_login.this,"Please enter valid user",Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(member_login.this,"Please enter valid user",Toast.LENGTH_SHORT).show();
                            }
                            //  Toast.makeText(member_login.this, ""+uid, Toast.LENGTH_SHORT).show();


                        } else {

                            Toast.makeText(member_login.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


        });
        ca.execute();
    }

    public void onBackPressed() {
        //super.onBackPressed();
        //finish();
        Intent back_intent = new Intent(member_login.this, First_page.class);
        startActivity(back_intent);
    }


}