package com.tiqs.rapmedix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONObject;

public class Refer_earn_page extends AppCompatActivity {

    TextView ref_code, p1, p2, p3;
    boolean isNet;
    String UserId = "", referral_code;
    View view;
    Button share_referral;
    SharedPreferences sp, ref_code_sp;
    ImageView share_via;

    public static final String ref_codee = "referral";


    public static final String refcode = "referral";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn_page);
        p1 = (TextView) findViewById(R.id.point1);
        p2 = (TextView) findViewById(R.id.point2);
        p3 = (TextView) findViewById(R.id.point3);
        ref_code = (TextView) findViewById(R.id.ref_code);
        share_via = (ImageView) findViewById(R.id.share_via);
        ConnectionDetector cd = new ConnectionDetector(Refer_earn_page.this);
        isNet = cd.isConnectingToInternet();
        View view = findViewById(R.id.hdrawer_layout);
        ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
        share_referral = (Button) findViewById(R.id.share_referral);

        ref_code_sp = getSharedPreferences(ref_codee, MODE_PRIVATE);
        ref_code.setText(ref_code_sp.getString("referral_code", "empty"));
        back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        DataBase_Helper db = new DataBase_Helper(this);
        UserId = db.getUserId("1");
        sp = getSharedPreferences(refcode, MODE_PRIVATE);
        sp.getString("file_name", null);
        Log.e("code", "" + sp);


        p1.setText("\u2022  Invite your friends to Get up to 3 Free Consultations. 1 for every successful signup through the app.");
        p2.setText("\u2022 Add your family members to get 1 Free Consultation.");
        p3.setText("\u2022 Sign Up to Rapmedix and Get 1 Free Consultation.");


        if (isNet) {

            try {

                JSONObject jo = new JSONObject();
                jo.put("user_id", UserId);
                Log.e("id", "" + jo.toString());
                referral_code(jo, getResources().getString(R.string.server) + Constants.category_id);


            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {

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

        share_via.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share_intent = new Intent(android.content.Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra("android.intent.extra.TEXT", "Hey, \nI care about you, so i thought this app would be extremely useful for you & your family for your future Doctor Consultations and Health Checkups. Signup today to avail free consultations by using referral code"
                        + ": " + ref_code_sp.getString("referral_code", "empty") + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName().toString());
                startActivity(share_intent);
            }
        });

        share_referral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager pm = getPackageManager();

                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    /*String text = "\"Hey, \\nI care about you, so i thought this app would be extremely useful for you & your family for your future Doctor Consultations and Health Checkups. Signup today to avail free consultations by using referral code\"\n" +
                            "                       +\": \"+ref_code_sp.getString(\"referral_code\",\"empty\") +\"\\n\"+\"https://play.google.com/store/apps/details?id=\"+getPackageName().toString()";
*/
                    String text = "Hey, \nI care about you, so i thought this app would be extremely useful for you & your family for your future Doctor Consultations and Health Checkups. Signup today to avail free consultations by using referral code"
                            + ": " + ref_code_sp.getString("referral_code", "empty") + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName().toString();

                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(Refer_earn_page.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();

                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("com.whatsapp")));
                }
                //"android.intent.extra.TEXT",
            }
        });
    }

    public void referral_code(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(Refer_earn_page.this, jo, url, new OnAsyncCompleteRequest() {

            Intent intent2 = getIntent();
            Bundle b = intent2.getExtras();

            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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
                } else {
                    try {
                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");
                        if (status.equals("success")) {
                            ref_code.setText(jo.getString("referral"));
                            sp.getString("file_name", "" + ref_code);
                            Log.e("code_ref", "" + sp);
                        } else {
                            Toast.makeText(Refer_earn_page.this, "" + status, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        ca.execute();
    }

}
