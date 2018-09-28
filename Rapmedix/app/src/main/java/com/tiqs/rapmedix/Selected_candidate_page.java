package com.tiqs.rapmedix;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Selected_candidate_page extends AppCompatActivity {
    TextView member_name;
    EditText mobile_number;
    Spinner offers;
    String[] offerss = {"Select Offer", "Offer 1", "Offer 2"};
    String UserId = "", Url = "", Hospital_id = "", DoctorId = "", Doctor_Working_details_id = "", AppDate = "", AppTime = "", AppDay = "", Fee = "", Name = "", MobileNo = "", DoctorName = "";

    ConnectionDetector connectionDetector;
    boolean isInternetAvailable;

    TextView Namee, Desig, Qualification, Rating, Experience, price, service_tax, total;
    RadioGroup radioGroup;
    ImageView Imageview, plus_add_member;
    RadioButton fre_con, off_con;
    Button booknow;
    String discountt = "", coupon_type = "";
    DataBase_Helper dataBase_helper;
    SharedPreferences sp, ref_code_sp;
    public static final String cost = "price_c";

    boolean isNet;
    ArrayList<Famili_member_dat_model> famili_member_dat_models;
    ExpandableHeightGridView1 family_members_list;
    ArrayList names, Mobile_numbers;
    ArrayAdapter dataAdapter_age;
    int hos_pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selected_candidate_page);

        ref_code_sp = getSharedPreferences("referral", MODE_PRIVATE);

        member_name = (TextView) findViewById(R.id.member_name);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        fre_con = (RadioButton) findViewById(R.id.fre_con);
        off_con = (RadioButton) findViewById(R.id.off_con);
        booknow = (Button) findViewById(R.id.booknow);

        names = new ArrayList();
        Mobile_numbers = new ArrayList();
        hos_pos = getIntent().getIntExtra("hos_pos", 0);
        DoctorId = getIntent().getStringExtra("doctor_id");
        Doctor_Working_details_id = getIntent().getStringExtra("doctorworkingdetails_id");
        Hospital_id = getIntent().getStringExtra("hospital_id");
        Fee = getIntent().getStringExtra("price");

        Log.e("detsild", new DataBase_Helper(this).getUserMobileNumber("1") + "Aa" + new DataBase_Helper(this).getUserName("1"));

        connectionDetector = new ConnectionDetector(this);
        isInternetAvailable = connectionDetector.isConnectingToInternet();


        sp = getSharedPreferences(cost, MODE_PRIVATE);


        Namee = (TextView) findViewById(R.id.doctor_name);
        Desig = (TextView) findViewById(R.id.doctor_desig);

        price = (TextView) findViewById(R.id.price);
        service_tax = (TextView) findViewById(R.id.service_tax);
        total = (TextView) findViewById(R.id.total);
        this.isNet = new ConnectionDetector(Selected_candidate_page.this).isConnectingToInternet();
        if (isInternetAvailable) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("doctor_id", DoctorId);
                jsonObject.put("hospital_id", Hospital_id);
                jsonObject.put("doctorworkingdetails_id", Doctor_Working_details_id);
                jsonObject.put("user_id", new DataBase_Helper(this).getUserId("1"));
                Log.e("aa", "Aa" + jsonObject);
                getFamilyDetails(jsonObject, getResources().getString(R.string.webData) + Constants.checkout_service);

            } catch (Exception e) {
                Log.e("exception", "" + e.toString());
            }
        } else {
            Snackbar.make(findViewById(R.id.root_view), "Please Check Your Internet Connectivity", Snackbar.LENGTH_LONG).show();
        }

        Qualification = (TextView) findViewById(R.id.doctor_quli);
        Experience = (TextView) findViewById(R.id.doctor_exp);
        Imageview = (ImageView) findViewById(R.id.doctorImg);
        plus_add_member = (ImageView) findViewById(R.id.plus_add_member);

        Qualification.setText(getIntent().getStringExtra("date"));
        Experience.setText(getIntent().getStringExtra("time"));
        dataBase_helper = new DataBase_Helper(this);

        plus_add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_plus = new Intent(Selected_candidate_page.this, Add_family_member.class);
                intent_plus.putExtra("Add_Family", "1");
                startActivity(intent_plus);
            }
        });
        ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
        back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        member_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNet) {
                    try {
                        JSONObject jo = new JSONObject();
                        jo.put("user_id", new DataBase_Helper(Selected_candidate_page.this).getUserId("1"));
                        //names.add(jo.getString("username"));


                        getPerson_Details(jo, getString(R.string.server) + Constants.selectFamily);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        });


        /*Snackbar snackBar = Snackbar.make(Selected_candidate_page.this.findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
            public void onClick(View view) {
                Selected_candidate_page.this.finish();
                Selected_candidate_page.this.startActivity(Selected_candidate_page.this.getIntent());
            }
        });
        snackBar.show();*/


    }

    public void getPerson_Details(JSONObject jo, String url) {
        new CustomAsync(Selected_candidate_page.this, jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {
                if (result.equals(pl.droidsonroids.gif.BuildConfig.VERSION_NAME) || result == null) {
                    Snackbar snackBar = Snackbar.make(Selected_candidate_page.this.findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                        public void onClick(View view) {
                            Selected_candidate_page.this.finish();
                            Selected_candidate_page.this.startActivity(Selected_candidate_page.this.getIntent());
                        }
                    });
                    snackBar.show();
                    return;
                }
                try {
                    JSONObject jo = new JSONObject(result);
                    names.clear();
                    Mobile_numbers.clear();
                    names.add(dataBase_helper.getUserName("1"));
                    Mobile_numbers.add(dataBase_helper.getUserMobileNumber("1"));

                    if (jo.getString("status").equals("success")) {

                        //names.add("You");
                        JSONArray ja = jo.getJSONArray("familydata");

                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject j = ja.getJSONObject(i);
                            names.add(j.getString("name"));
                            if (j.getString("mobile").equals("0")) {
                                Mobile_numbers.add(dataBase_helper.getUserMobileNumber("1"));
                            } else {
                                Mobile_numbers.add(j.getString("mobile"));
                            }
                        }
                        // dataAdapter_age.notifyDataSetChanged();


                    } else if (jo.getString("status").equals("No data found")) {
                    }
                    final Dialog dialog = new Dialog(Selected_candidate_page.this);
                    dialog.setContentView(R.layout.person_details);
                    ListView listView = (ListView) dialog.findViewById(R.id.person_details_list);
                    ArrayAdapter dataAdapter_age = new ArrayAdapter(Selected_candidate_page.this, R.layout.spinner_item, names);
                    listView.setAdapter(dataAdapter_age);
                    //dialog.getWindow().setTitleColor();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            member_name.setText(names.get(position).toString());
                            /*if(Mobile_numbers.equals(0))
                            {

                                mobile_number.setText(dataBase_helper.getUserMobileNumber("1"));
                            }
                            else {
                                mobile_number.setText(Mobile_numbers.get(position).toString());
                            }*/
                            mobile_number.setText(Mobile_numbers.get(position).toString());
                            dialog.dismiss();

                        }
                    });

                    dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
                    dialog.setTitle("Select Person");
                    Window window = dialog.getWindow();
                    //window.setLayout(500,500);
                    //dialog.getWindow().setBackgroundDrawableResource(R.color.colorAccent);
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Exception", e.toString());
                }
            }
        }).execute(new String[0]);
    }


    public void getFamilyDetails(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Selected_candidate_page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {

                    Snackbar snackBar = Snackbar.make(Selected_candidate_page.this.findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Selected_candidate_page.this.finish();
                                    startActivity(Selected_candidate_page.this.getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                } else {

                    try {

                        final JSONObject jo = new JSONObject(result);

                        String status = jo.getString("res_status");

                        if (status.equals("success")) {

                            Namee.setText(jo.getString("name"));
                            Desig.setText(jo.getString("hospital_name"));

                            Glide
                                    .with(Selected_candidate_page.this)
                                    .load(jo.getString("profile_pic"))
                                    .centerCrop()
                                    .placeholder(R.drawable.doctor_icon)
                                    .crossFade()
                                    .into(Imageview);


                            String off_con_avail = jo.getString("consultation");

                            if (off_con_avail.equals("0")) {

                                off_con.setVisibility(View.GONE);

                            } else {
                                off_con.setText(jo.getDouble("consultation") + "% OFF");
                            }
                            price.setText(Fee);

                            service_tax.setText("" + Math.round(Integer.parseInt(Fee) * (jo.getDouble("consultation_service_tax") / 100)));
                            Log.e("zzzaaa", "Aa");

                            total.setText("" + (Integer.parseInt(Fee) + Double.parseDouble(service_tax.getText().toString())));

                            fre_con.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (buttonView.isChecked()) {
                                        coupon_type = "1";

                                        //  off_con.setVisibility(View.VISIBLE);
                                        price.setText("0");
                                        service_tax.setText("0");
                                        total.setText("0");
                                    }
                                }
                            });


                            off_con.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                    if (buttonView.isChecked()) {
                                        try {
                                            coupon_type = "2";
                                            //   fre_con.setVisibility(View.GONE);


                                            Log.e("aaaaaaaa", "price" + Fee);

                                            if (sp.getString("Fee", "null").equals("null"))

                                            {

                                                //  price.setText("" + Math.round((Double.parseDouble(price.getText().toString()) - (Integer.parseInt(price.getText().toString()) * (jo.getDouble("consultation") / 100)))));
                                                Log.e("ffeeeeee", "Aa" + Fee);
                                                double pprice = ((Integer.parseInt(Fee)) - (Math.round(Integer.parseInt(Fee) * (jo.getDouble("consultation") / 100))));
                                                price.setText("" + pprice);
                                                Log.e("original", "" + pprice);
                                                service_tax.setText("" + Math.round(Double.parseDouble(price.getText().toString()) * (jo.getDouble("consultation_service_tax") / 100)));
                                                //total.setText("" + Math.round(Double.parseDouble(price.getText().toString()) + Double.parseDouble(service_tax.getText().toString())));
                                                total.setText("" + Math.round(Double.parseDouble(price.getText().toString()) + (Double.parseDouble(price.getText().toString()) * (jo.getDouble("consultation_service_tax") / 100))));
                                                //discountt = "" + (Integer.parseInt(price.getText().toString()) * (jo.getDouble("consultation_service_tax") / 100));
                                                discountt = "" + (Double.parseDouble(Fee) * (jo.getDouble("consultation") / 100));


                                                Log.e("disc", "" + discountt.toString().trim());
                                            } else {
                                                price.setText(sp.getString("price", "0"));
                                                service_tax.setText(sp.getString("serice_tax", "0"));
                                                total.setText(sp.getString("total", "0"));
                                                discountt = price.getText().toString();
                                            }

                                            SharedPreferences.Editor editor = sp.edit();

                                            editor.putString("price", price.getText().toString().trim());
                                            editor.putString("serice_tax", service_tax.getText().toString().trim().substring(0, 2));
                                            editor.putString("total", total.getText().toString().trim());
                                            editor.commit();
                                            Log.e("aaaaaaaa", "price" + sp.getString("price", "0") + "   " + sp.getString("serice_tax", "0"));


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Log.e("aa", "Aa" + e.toString());

                                        }
                                    }


                                }
                            });

                            if (!jo.getString("user_freecoupon").equals("1")) {
                                fre_con.setVisibility(View.GONE);
                            }
                            booknow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (member_name.getText().toString().trim().equals("") || mobile_number.getText().toString().trim().equals("")) {

                                        Snackbar snackBar = Snackbar.make(view, "Fields Should not be Empty!", Snackbar.LENGTH_SHORT)
                                                .setAction("", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                    }
                                                });
                                        snackBar.setActionTextColor(Color.RED);
                                        View sbView = snackBar.getView();
                                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                        textView.setTextColor(Color.YELLOW);
                                        snackBar.show();
                                    } else {
                                        if (isInternetAvailable) {
                                            try {

                                                JSONObject jsonObject = new JSONObject();
                                                jsonObject.put("doctor_id", DoctorId);
                                                jsonObject.put("appointment_date", Qualification.getText().toString().trim());
                                                jsonObject.put("appointment_time", Experience.getText().toString().trim());
                                                jsonObject.put("fee", Fee);
                                                jsonObject.put("hospital_id", Hospital_id);
                                                jsonObject.put("doctorworkingdetails_id", Doctor_Working_details_id);
                                                jsonObject.put("patient_name", member_name.getText().toString().trim());
                                                jsonObject.put("patient_mobile", mobile_number.getText().toString().trim());
                                                jsonObject.put("discount", discountt);
                                                jsonObject.put("sub_total", price.getText().toString().trim());
                                                jsonObject.put("servicetax", service_tax.getText().toString().trim());
                                                jsonObject.put("total_payble_amount", total.getText().toString().trim());
                                                jsonObject.put("coupon_type", coupon_type);
                                                jsonObject.put("category_name", ref_code_sp.getString("spec_name", "em"));


                                                jsonObject.put("user_id", new DataBase_Helper(Selected_candidate_page.this).getUserId("1"));
                                                Log.e("aa", "Aa" + jsonObject);
                                                confirm_booking(jsonObject, getResources().getString(R.string.webData) + Constants.bookanappointment);
                                            } catch (Exception e) {
                                                Log.e("excep", "" + e.toString());

                                            }


                                        } else {
                                            Snackbar.make(findViewById(R.id.root_view), "Please Check Your Internet Connectivity", Snackbar.LENGTH_LONG).show();

                                        }
                                        //Toast.makeText(Selected_candidate_page.this, "Your request  successfully submitted", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        } else {
                            Snackbar.make(findViewById(R.id.root_view), "" + status, Snackbar.LENGTH_LONG).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }
        });
        ca.execute();
    }

    public void dismiss() {
        Selected_candidate_page.this.finish();
        Intent i = new Intent(Selected_candidate_page.this, Home_Page.class);  //your class
        startActivity(i);

    }


    public void confirm_booking(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Selected_candidate_page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {

                    Snackbar snackBar = Snackbar.make(Selected_candidate_page.this.findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Selected_candidate_page.this.finish();
                                    startActivity(Selected_candidate_page.this.getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                } else {

                    try {

                        final JSONObject jo = new JSONObject(result);

                        String status = jo.getString("res_status");

                        if (status.equals("success")) {
                            Toast.makeText(Selected_candidate_page.this, "Your Appointment is booked", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Selected_candidate_page.this, Home_Page.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                        } else {
                            Snackbar.make(findViewById(R.id.root_view), "" + status, Snackbar.LENGTH_LONG).show();
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
