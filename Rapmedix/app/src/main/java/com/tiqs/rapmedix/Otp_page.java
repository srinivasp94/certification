package com.tiqs.rapmedix;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;
import com.tiqs.rapmedix.utils.User;

import org.json.JSONObject;

import java.util.List;


/**
 * Created by ADMIN on 6/14/2016.
 */
public class Otp_page extends Activity {
    TextView resend_otp;
    EditText OTP;
    Button Submit;
    String uid, Name, Trip_id, Trip_id1, Datee, Type;
    static String Mobile, uri, otp, resend_otp_url;
    ConnectionDetector cd;
    BroadcastReceiver broadcastReceiver;
    static boolean isInternentAvailable;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 125;
    View rootview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_otp);

        rootview = findViewById(R.id.Otp_rootview);

        cd = new ConnectionDetector(Otp_page.this);
        isInternentAvailable = cd.isConnectingToInternet();

        Intent intent = getIntent();


        uid = intent.getStringExtra("uid");
        Mobile = intent.getStringExtra("mobile");
        otp = intent.getStringExtra("otp");
        Name = intent.getStringExtra("name");

        Log.e("otp_", Name + "otp  " + otp + "  mob  " + Mobile + "  uid  " + uid);

        uri = getString(R.string.server) + Constants.loginotp;
        resend_otp_url = Constants.resend_otp;
        OTP = (EditText) findViewById(R.id.logmobileotp);
        Submit = (Button) findViewById(R.id.submit);
        resend_otp = (TextView) findViewById(R.id.resend_otp);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!OTP.getText().toString().equals(otp)) {

                    Snackbar.make(rootview, "Entered OTP is invalid", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view)

                                {

                                }
                            })
                            .show();
                } else {

                    if (isInternentAvailable) {

                        try {

                            JSONObject jo = new JSONObject();
                            jo.put("mobile", Mobile);
                            callOtp(jo, uri);

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    } else
                        Toast.makeText(getApplicationContext(), "Please check Internent Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInternentAvailable) {

                    try {

                        String otpdata = OTP.getText().toString().trim();

                        JSONObject jo = new JSONObject();
                        jo.put("mobile", Mobile);
                        resendOtp(jo, resend_otp_url);

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Please check Internent Connection", Toast.LENGTH_SHORT).show();

            }
        });


        if (isReadStorageAllowed()) {
            //If permission is already having then showing the toast
            //Toast.makeText(Otp_page.this,"You already have the permission",Toast.LENGTH_LONG).show();
            //Existing the method with return
            return;
        } else requestStoragePermission();


        //If the app has not the permission then asking for the permission


        /*broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {
             Log.e("mesg","mesg");
                final Bundle bundle = intent.getExtras();

               //  public void recivedSms(String message) {
                    try {
                        //Intent inte = new Intent();
                        String message = bundle.getString("message");
                        Log.e("mesg","mesg"+message);

                        OTP.setText(message);
                        if (OTP.getText().toString().equals(message)) {
                            if (isInternentAvailable) {
                                try {
                                    String otpdat = OTP.getText().toString().trim();
                                    JSONObject jom = new JSONObject();
                                    jom.put("mobile", Mobile);
                                    callOtp(jom, uri);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else
                                Toast.makeText(getApplicationContext(), "Please check Internent Connection", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
            }


        };
        registerReceiver(broadcastReceiver, new IntentFilter("SMS_SENT"));*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final Bundle bundle = intent.getExtras();

                //  public void recivedSms(String message) {
                try {
                    //Intent inte = new Intent();
                    String message = bundle.getString("message");

                    OTP.setText(message);
                    if (OTP.getText().toString().equals(message)) {
                        if (isInternentAvailable) {
                            try {
                                String otpdat = OTP.getText().toString().trim();
                                JSONObject jom = new JSONObject();
                                jom.put("mobile", Mobile);
                                callOtp(jom, uri);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Please check Internent Connection", Toast.LENGTH_SHORT).show();
                    } else {

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }


        };
        registerReceiver(broadcastReceiver, new IntentFilter("SMS_SENT"));
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else return false;

        //If permission is not granted returning false
    }

    //Requesting permission
    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 0);

    }


    public void callOtp(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Otp_page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                } else {

                    try {

                        JSONObject j = new JSONObject(result);

                        String status = j.getString("status");

                        if (status.equals("success")) {

                            DataBase_Helper dh = new DataBase_Helper(Otp_page.this);
                            int c = dh.getUserCount();
                            if (c == 0) {
                                dh.insertUserId(new User("1", uid, Mobile, Name));
                            } else {
                                dh.updatetUserId(new User("1", uid, Mobile, Name));
                            }
                            List<User> li = dh.getUserData();
                            for (User u : li) {
                                String log = "username" + " " + u.getName() + " mobile " + u.getMobile() + " user id " + u.getUid();
                                Log.e("db data", log);
                            }
                            //  Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Otp_page.this, Home_Page.class);
                            startActivity(intent);
                            unregisterReceiver(broadcastReceiver);
                            finish();


                        } else {

                            Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }

        });

        ca.execute();
    }

    public void resendOtp(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Otp_page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                } else {

                    try {

                        JSONObject j = new JSONObject(result);

                        String status = j.getString("status");

                        if (status.equals("success")) {
                            otp = j.getString("otp");

                          /*  DataBase_Helper dh=new DataBase_Helper(Otp_page.this);
                            int c=dh.getUserCount();
                            if(c==0)
                            {
                                dh.insertUserId(new User("1",uid,Mobile));
                            }
                            else
                            {
                                dh.updatetUserId(new User("1",uid,Mobile));
                            }
                            //dh.insertData(new User(name,email,))
                            List<User> li=dh.getUserData();
                            for( User u: li)
                            {
                                String log="username"+" "+u.getName()+" mobile "+u.getMobile()+" user id "+u.getUid();
                                Log.e("db data",log);
                            }
                            finish();
                            Intent intent = new Intent(Otp_page.this, Home_Page.class);
                            startActivity(intent);*/
                        } else {

                            Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }

        });

        ca.execute();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(Otp_page.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            Log.e("exp", e + "exp");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == 0) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Displaying a toast
                // Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                // Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


}
