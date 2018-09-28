package com.tiqs.rapmedix;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONObject;

public class Change_password extends AppCompatActivity {

    EditText old_pwd,new_pwd,confirm_pwd;
    Button btn_change;
    boolean isNet;
    View view;
    String UserId="";
    String pwd;
    ConnectionDetector connectionDetector;

    DataBase_Helper dataBase_helper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        dataBase_helper=new DataBase_Helper(this);

        old_pwd = (EditText) findViewById(R.id.old_pwd);
        new_pwd = (EditText) findViewById(R.id.new_pwd);
        confirm_pwd = (EditText) findViewById(R.id.confirm_pwd);
        btn_change = (Button) findViewById(R.id.change_pwd);
        view=findViewById(R.id.root);

        View myLayout = findViewById(R.id.prfl_head);
        ImageView back = (ImageView) myLayout.findViewById(R.id.mainmenu);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        connectionDetector =new ConnectionDetector(this);
        isNet=connectionDetector.isConnectingToInternet();

        DataBase_Helper db = new DataBase_Helper(this);
        UserId = db.getUserId("1");

        btn_change.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0)
            {
                if (old_pwd.getText().toString().length()==0||new_pwd.getText().toString().length()==0||(confirm_pwd .getText().toString().length()==0))
                {
                    Toast.makeText(getApplicationContext(),"Fields should not be empty",Toast.LENGTH_SHORT).show();

                }else if (old_pwd.getText().toString().equals(new_pwd .getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"old password and new password should not be same",Toast.LENGTH_SHORT).show();

                }else if (!new_pwd.getText().toString().equals(confirm_pwd .getText().toString()))
                {
                    Toast.makeText(getApplicationContext()," New and confirm  password should be same",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(isNet)
                    {
                        try {
                            JSONObject jo = new JSONObject();
                            jo.put("user_id", dataBase_helper.getUserId("1"));
                            jo.put("oldpassword", old_pwd.getText().toString().trim());
                            jo.put("newpassword", new_pwd.getText().toString().trim());
                            jo.put("confirmpassword", confirm_pwd.getText().toString().trim());
                            Log.e("data","mmmm"+jo);
                            change_pwd(jo,getResources().getString(R.string.server)+Constants.changePassword_service);
                        }catch (Exception e) {

                            e.printStackTrace();
                        }

                    }
                    else {

                        Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        finish();
                                        startActivity(getIntent());
                                    }
                                });
                        snackBar.setActionTextColor(Color.RED);
                        View sbView = snackBar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.YELLOW);
                        snackBar.show();


                    }
                }
            }

        });


    }
    public void change_pwd (JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Change_password.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {


                    Snackbar snackBar = Snackbar.make(view, "Please try Again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                }

                else {

                    try {

                        JSONObject jo = new JSONObject(result);

                        String status = jo.getString("status");
                        Log.e("sta",""+status);

                        if (status.equals("success"))
                        {
                            old_pwd.setText("");
                            new_pwd.setText("");
                            confirm_pwd.setText("");
                            finish();

                        }

                        else {

                            Toast.makeText(Change_password.this, ""+status, Toast.LENGTH_SHORT).show();
                        }


                    }catch (Exception e) {

                        e.printStackTrace();
                        Log.e("not",""+e);
                    }
                }

            }
        }); ca.execute();
    }

}
