package com.tiqs.rapmedix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.adapters.DoctorsDescription_adapter;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;
import com.tiqs.rapmedix.utils.CustomAsync1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import at.blogc.android.views.ExpandableTextView;
import pl.droidsonroids.gif.BuildConfig;

public class DoctorDescription extends Activity {
    public static final String hopital_name = "hospital_name";
    ArrayList<String> BookedSlots = new ArrayList();
    String DImage = BuildConfig.VERSION_NAME;
    String ser_image = BuildConfig.VERSION_NAME;
    String ser_image2 = BuildConfig.VERSION_NAME;
    ArrayList<String> DatesOfSlots = new ArrayList();
    ExpandableTextView Description;
    TextView Desig;
    TextView Experience;
    ImageView Imageview;
    TextView Name;
    ArrayList<String> NameOfWeekDays = new ArrayList();
    ArrayList<JSONObject> Object = new ArrayList();
    LinearLayout Overview;
    TextView Qualification;
    TextView Rating;
    LinearLayout Review;
    LinearLayout Service;
    ArrayList<String> SlotsStatus = new ArrayList();
    String TodayDate = BuildConfig.VERSION_NAME;
    String Url = BuildConfig.VERSION_NAME;
    String UserId = BuildConfig.VERSION_NAME;
    ArrayList<String> WeekDays = new ArrayList();
    ArrayList<String> datess = new ArrayList();
    ArrayList<String> doc_details = new ArrayList();
    String doctorId = BuildConfig.VERSION_NAME;
    String doctorName = BuildConfig.VERSION_NAME;
    String hospitalName = BuildConfig.VERSION_NAME;
    View header;
    boolean isNet;
    ListView listView;
    Typeface monster;
    Typeface robo;
    String slotTime = BuildConfig.VERSION_NAME;
    TabLayout tabs_cat;
    String[] tabs_cats = new String[]{"OVERVIEW", "SERVICES", "PROFILE"};
    ViewPager viewPager_home;
    DoctorsDescription_adapter add_member_adapter;
    String time;
    TextView timeW, text_exp;
    View view;
    LinearLayout root_layout;
    ImageView service_image, service_image2;
    TextView image_text;
    ExpandableHeightGridView1 profile_list, list_awards, reg_num, qualification, seminars, service_details;
    ArrayList<String> profile_array_list = new ArrayList<String>();
    ArrayList<String> awards_array = new ArrayList<String>();
    ArrayList<String> reg_num_array = new ArrayList<String>();
    ArrayList<String> qualification_array = new ArrayList<String>();
    ArrayList<String> seminars_array = new ArrayList<String>();
    ArrayList<String> service_array_text = new ArrayList<String>();
    ArrayList<String> service_array_image = new ArrayList<String>();
    SharedPreferences sp;
    String Hospital_name = "", Doctor_Working_details_id = "";
    SelectSlotsAdapter selectSlotsAdapter;

    public static String getCalculatedDate(String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_desc);
        Name = (TextView) findViewById(R.id.doctor_name);
        Desig = (TextView) findViewById(R.id.doctor_desig);
        Qualification = (TextView) findViewById(R.id.doctor_quli);
        Rating = (TextView) findViewById(R.id.doctor_rating);
        Experience = (TextView) findViewById(R.id.doctor_exp);
        Overview = (LinearLayout) findViewById(R.id.overView);
        Service = (LinearLayout) findViewById(R.id.service);
        Review = (LinearLayout) findViewById(R.id.review);
        Imageview = (ImageView) findViewById(R.id.doctorImg);
        view = findViewById(R.id.doctor_relative);
        header = findViewById(R.id.dctr_desc);
        View back = (ImageView) this.header.findViewById(R.id.mainmenu);
        tabs_cat = (TabLayout) findViewById(R.id.tabs_cat);
        viewPager_home = (ViewPager) findViewById(R.id.view_pager);
        root_layout = (LinearLayout) findViewById(R.id.root_layout);

        //test1 = (TextView) findViewById(R.id.test1);
        //test_year = (TextView) findViewById(R.id.test1_year);

        back.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DoctorDescription.this.onBackPressed();
            }
        });

        monster = Typeface.createFromAsset(getAssets(), "monster.ttf");
        robo = Typeface.createFromAsset(getAssets(), "robo.ttf");
        Name.setTypeface(monster);
        Desig.setTypeface(monster);
        Qualification.setTypeface(monster);
        Experience.setTypeface(monster);
        Rating.setTypeface(monster);
        Url = getString(R.string.serverData) + Constants.doctorDesc;
        doctorId = getIntent().getExtras().getString("doctorId");
        Overview.setBackgroundColor(Color.parseColor("#ed3237"));
        isNet = new ConnectionDetector(this).isConnectingToInternet();
        UserId = new DataBase_Helper(this).getUserId("1");


        for (String text : tabs_cats) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabs, this.tabs_cat, false);
            ((TextView) relativeLayout.findViewById(R.id.tab_title)).setText(text);
            this.tabs_cat.addTab(tabs_cat.newTab().setCustomView(relativeLayout));
        }

        //root_layout.removeAllViews();
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.overview, null);
        root_layout.addView(v);
        listView = (ListView) v.findViewById(R.id.doctor_list);
        listView.setFocusable(false);
        Description = (ExpandableTextView) findViewById(R.id.doctor_desc);
        Description.setTypeface(robo);
        Description.setAnimationDuration(1000);
        Description.setExpandInterpolator(new OvershootInterpolator());
        Description.setCollapseInterpolator(new OvershootInterpolator());
        TodayDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        Description.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Description.isExpanded()) {
                    Description.collapse();
                } else {
                    Description.expand();
                }
            }
        });
        Doctor_Working_details_id = getIntent().getStringExtra("doctorworkingdetails_id");
        Hospital_name = getIntent().getStringExtra("hospital_name");

        if (isNet) {
            try {
                JSONObject jo = new JSONObject();
                jo.put("doctor_id", doctorId);
                doctorDetails(jo, Url);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        /*for (int i=0;i<tabs_cats.length;i++)
        {
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tabs, tabs_cat, false);
            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tabs_cats[i]);

            //   tabLayout.addTab(tabLayout.newTab().setText(maincatt.getString("category_name")));
            tabs_cat.addTab(tabs_cat.newTab().setCustomView(relativeLayout));

        }*/


    }

    public void doctorDetails(JSONObject jo, String url) {
        new CustomAsync(this, jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {
                if (result == null || result.equals(BuildConfig.VERSION_NAME)) {
                    Snackbar snackBar = Snackbar.make(DoctorDescription.this.view, "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new OnClickListener() {
                        public void onClick(View view) {
                            DoctorDescription.this.finish();
                            DoctorDescription.this.startActivity(DoctorDescription.this.getIntent());
                        }
                    });
                    snackBar.show();
                    return;
                }
                try {
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    if (status.equals("success")) {
                        Object.clear();
                        JSONObject doctoeDet = jo.getJSONArray("doctordatails").getJSONObject(0);
                        String DName = doctoeDet.getString("name");
                        DoctorDescription.this.doctorName = doctoeDet.getString("name");
                        //DoctorDescription.this.hospitalName = doctoeDet.getString("working_hospitals");


                        String DDesignation = doctoeDet.getString("specialisation_name");
                        String DQualification = doctoeDet.getString("degree_name");
                        String DExperience = doctoeDet.getString("experience");
                        String DAbout = doctoeDet.getString("aboutus");

                        JSONObject doctHos = jo.getJSONArray("doctorworkingdatails").getJSONObject(0);
                        DoctorDescription.this.hospitalName = doctHos.getString("hospital_name");

                        DoctorDescription.this.DImage = DoctorDescription.this.getString(R.string.serverHost) + "uploads/doctor_image/" + doctoeDet.getString("profile_pic");
                        if (DName.length() > 11) {
                            DName = DName.substring(0, 11) + "...";
                        }
                        Name.setText(DName);
                        Desig.setText(DDesignation);
                        Qualification.setText(DQualification);
                        Experience.setText(DExperience + " Years Experience");
                        Description.setText(DAbout);
                        Glide.with(DoctorDescription.this)
                                .load(DoctorDescription.this.DImage)
                                .centerCrop().placeholder(R.drawable.doctor_icon)
                                .crossFade()
                                .into(DoctorDescription.this.Imageview);
                        JSONArray doctorWorking = jo.getJSONArray("doctorworkingdatails");
                        for (int i = 0; i < doctorWorking.length(); i++) {
                            DoctorDescription.this.Object.add(doctorWorking.getJSONObject(i));

                        }
                        DoctorDescription.this.listView.setAdapter(new DoctorDescriptionAdapter(DoctorDescription.this, DoctorDescription.this.Object));

                        tabs_cat.addOnTabSelectedListener(new OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(Tab tab) {
                                if (tab.getPosition() == 0) {
                                    root_layout.removeAllViews();
                                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View v = vi.inflate(R.layout.overview, null);
                                    root_layout.addView(v);
                                    listView = (ListView) v.findViewById(R.id.doctor_list);
                                    listView.setFocusable(false);
                                    Description = (ExpandableTextView) findViewById(R.id.doctor_desc);
                                    Description.setTypeface(robo);
                                    Description.setAnimationDuration(1000);
                                    Description.setExpandInterpolator(new OvershootInterpolator());
                                    Description.setCollapseInterpolator(new OvershootInterpolator());
                                    TodayDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                                    Description.setOnClickListener(new OnClickListener() {
                                        public void onClick(View v) {
                                            if (Description.isExpanded()) {
                                                Description.collapse();
                                            } else {
                                                Description.expand();
                                            }
                                        }
                                    });

                                    if (isNet) {
                                        try {
                                            JSONObject jo = new JSONObject();
                                            jo.put("doctor_id", doctorId);
                                            doctorDetails(jo, Url);
                                            return;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            return;
                                        }
                                    }
                                } else if (tab.getPosition() == 1) {
                                    //service_details.setNumColumns(2);
                                    root_layout.removeAllViews();
                                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View v = vi.inflate(R.layout.services, null);

                                    service_image = (ImageView) v.findViewById(R.id.service_image);
                                    service_details = (ExpandableHeightGridView1) v.findViewById(R.id.service_grid);

                                    service_details.setVisibility(View.VISIBLE);

                                    Log.e("ser", "" + service_array_text.size());
                                    root_layout.addView(v);
                                    if (isNet) {

                                        try {

                                            JSONObject jo = new JSONObject();
                                            jo.put("doctor_id", doctorId);

                                            doctor_services(jo, getResources().getString(R.string.serverData) + Constants.doctordescription_service);


                                        } catch (Exception e) {

                                            e.printStackTrace();
                                            Log.e("log", "" + e);
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

                                } else if (tab.getPosition() == 2) {
                                    root_layout.removeAllViews();
                                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View v = vi.inflate(R.layout.profile_doc, null);

                                    profile_list = (ExpandableHeightGridView1) v.findViewById(R.id.profile_list);
                                    qualification = (ExpandableHeightGridView1) v.findViewById(R.id.text_qual);
                                    list_awards = (ExpandableHeightGridView1) v.findViewById(R.id.list_awards);
                                    reg_num = (ExpandableHeightGridView1) v.findViewById(R.id.reg_num);
                                    seminars = (ExpandableHeightGridView1) v.findViewById(R.id.seminars);

                                    text_exp = (TextView) v.findViewById(R.id.doc_exp);

                                    ListAdapter ma = new ListAdapter(DoctorDescription.this, profile_array_list);
                                    ListAdapter awrds = new ListAdapter(DoctorDescription.this, awards_array);
                                    ListAdapter register_num = new ListAdapter(DoctorDescription.this, reg_num_array);
                                    ListAdapter doc_qual = new ListAdapter(DoctorDescription.this, qualification_array);
                                    ListAdapter seminars_list = new ListAdapter(DoctorDescription.this, seminars_array);

                                    profile_list.setAdapter(ma);
                                    qualification.setAdapter(doc_qual);
                                    list_awards.setAdapter(awrds);
                                    reg_num.setAdapter(register_num);
                                    seminars.setAdapter(seminars_list);

                                    root_layout.addView(v);

                                    if (isNet) {

                                        try {

                                            JSONObject jo = new JSONObject();
                                            jo.put("doctor_id", doctorId);

                                            profile_doc(jo, getResources().getString(R.string.serverData) + Constants.doctordescription_service);
                                            Log.e("doc_exp", "");

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

                                } else if (tab.getPosition() == 3) {

                                }

                            }

                            @Override
                            public void onTabUnselected(Tab tab) {

                            }

                            @Override
                            public void onTabReselected(Tab tab) {

                            }
                        });


                        return;
                    }
                    Toast.makeText(DoctorDescription.this, BuildConfig.VERSION_NAME + status, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).execute(new String[0]);
    }

    public void profile_doc(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(DoctorDescription.this, jo, url, new OnAsyncCompleteRequest() {

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


                            JSONArray doctor_experience = jo.getJSONArray("doctor_experience");
                            Log.e("exp", "" + doctor_experience);

                            profile_array_list.clear();
                            for (int i = 0; i < doctor_experience.length(); i++) {

                                JSONObject jsonObject = doctor_experience.getJSONObject(i);
                                Log.e("deta", "" + jsonObject.getString("position"));
                                profile_array_list.add(jsonObject.getString("position") + ": " + jsonObject.getString("start_year") + " - " + jsonObject.getString("end_year"));

                                //profile_list.getText("position");
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));

                            }
                            profile_list.setAdapter(new ArrayAdapter<String>(DoctorDescription.this, android.R.layout.simple_spinner_dropdown_item, profile_array_list));


                            JSONArray doctor_doc = jo.getJSONArray("doctordatails");
                            Log.e("exp", "" + doctor_doc);


                            for (int i = 0; i < doctor_doc.length(); i++) {
                                JSONObject doctordatails = doctor_doc.getJSONObject(i);
                                Log.e("deta", "" + doctordatails.getString("experience"));

                                text_exp.setText(doctordatails.getString("experience"));
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));

                            }
                            JSONArray doctor_qualification = jo.getJSONArray("doctor_qualification");
                            Log.e("exp", "" + doctor_qualification);


                            qualification_array.clear();
                            for (int i = 0; i < doctor_qualification.length(); i++) {

                                JSONObject jsonObject = doctor_qualification.getJSONObject(i);
                                Log.e("deta", "" + jsonObject.getString("degree_name"));
                                qualification_array.add(jsonObject.getString("degree_name"));

                                //profile_list.getText("position");
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));

                            }
                            qualification.setAdapter(new ArrayAdapter<String>(DoctorDescription.this, android.R.layout.simple_spinner_dropdown_item, qualification_array));

                            JSONArray doctor_awards = jo.getJSONArray("doctor_awords");
                            Log.e("exp", "" + doctor_awards);


                            awards_array.clear();
                            for (int i = 0; i < doctor_awards.length(); i++) {

                                JSONObject jsonObject = doctor_awards.getJSONObject(i);
                                Log.e("deta", "" + jsonObject.getString("doctor_membership_awords_details"));
                                awards_array.add(jsonObject.getString("doctor_membership_awords_details"));

                                //profile_list.getText("position");
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));

                            }
                            list_awards.setAdapter(new ArrayAdapter<String>(DoctorDescription.this, android.R.layout.simple_spinner_dropdown_item, awards_array));

                            JSONArray doc_reg_num = jo.getJSONArray("doctor_regnumbers");
                            Log.e("exp", "" + doc_reg_num);


                            reg_num_array.clear();
                            for (int i = 0; i < doc_reg_num.length(); i++) {

                                JSONObject jsonObject = doc_reg_num.getJSONObject(i);
                                Log.e("deta", "" + jsonObject.getString("registration_number"));
                                reg_num_array.add(jsonObject.getString("registration_number"));

                                //profile_list.getText("position");
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));

                            }
                            reg_num.setAdapter(new ArrayAdapter<String>(DoctorDescription.this, android.R.layout.simple_spinner_dropdown_item, reg_num_array));

                            JSONArray doctor_seminars = jo.getJSONArray("doctor_presentations");
                            Log.e("exp", "" + doctor_seminars);


                            seminars_array.clear();
                            for (int i = 0; i < doctor_seminars.length(); i++) {

                                JSONObject jsonObject = doctor_seminars.getJSONObject(i);
                                Log.e("deta", "" + jsonObject.getString("seminors_details"));
                                seminars_array.add(jsonObject.getString("seminors_details"));

                                //profile_list.getText("position");
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));

                            }
                            seminars.setAdapter(new ArrayAdapter<String>(DoctorDescription.this, android.R.layout.simple_spinner_dropdown_item, seminars_array));


                        } else {

                            Toast.makeText(DoctorDescription.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                        Log.e("exp", "" + e.toString());

                    }


                }

            }
        });
        ca.execute();
    }

    public void doctor_services(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(DoctorDescription.this, jo, url, new OnAsyncCompleteRequest() {

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

                            JSONArray doctor_service = jo.getJSONArray("doctor_selected_service");
                            Log.e("exp", "" + doctor_service);

                            service_array_text.clear();
                            service_array_image.clear();
                            for (int i = 0; i < doctor_service.length(); i++) {

                                JSONObject jsonObject = doctor_service.getJSONObject(i);
                                Log.e("deta", "" + jsonObject.getString("service_name"));
                                service_array_text.add(jsonObject.getString("service_name"));


                                //image_text.setText("service_name");
                                //test_year.setText(jsonObject.getString("start_year")+" - "+jsonObject.getString("end_year"));
                                //DoctorDescription.this.ser_image = DoctorDescription.this.getString(R.string.serverHost)+"uploads/hospitalservice_image/" + jsonObject.getString("service_image");

                                String image_url = DoctorDescription.this.getString(R.string.serverHost) + "uploads/hospitalservice_image/" + jsonObject.getString("service_image");
                                //https://www.rapmedix.com/uploads/hospitalservice_image/
                                Log.e("image", "" + image_url);
                                service_array_image.add(image_url);
                                //ser_image = jsonObject.getString("service_image");
                                /*Glide.with(DoctorDescription.this)
                                        .load(DoctorDescription.this.ser_image)
                                        .placeholder(R.drawable.mobile)
                                        .into(DoctorDescription.this.service_image);*/
                                /*DoctorDescription.this.ser_image2 = DoctorDescription.this.getString(R.string.serverHost)+"uploads/hospitalservice_image/" + jsonObject.getString("service_image");
                                //ser_image = jsonObject.getString("service_image");
                                Glide.with(DoctorDescription.this)
                                        .load(DoctorDescription.this.ser_image2)
                                        .placeholder(R.drawable.mobile)
                                        .into(DoctorDescription.this.service_image2);*/

                            }
                            service_details.setAdapter(new Service_grid(DoctorDescription.this, service_array_text, service_array_image));
                            //service_details.setAdapter(new ArrayAdapter<String>(DoctorDescription.this,android.R.layout.simple_spinner_dropdown_item,service_array));


                        } else {

                            Toast.makeText(DoctorDescription.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                        Log.e("exp", "" + e.toString());

                    }


                }

            }
        });
        ca.execute();
    }

    private void overrideFonts(Context context, View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    overrideFonts(context, vg.getChildAt(i));
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "monster.ttf"));
            }
        } catch (Exception e) {
        }
    }

    public void callServer(String date, int position, ArrayList<JSONObject> Object, ExpandableHeightGridView1 slots_grid, int positionn) {
        if (this.isNet) {
            try {
                Log.e("dadadada", "123    " + Object.toString());
                JSONObject jo = new JSONObject();
                jo.put("date", date);
                jo.put("doctor_id", this.doctorId);
                jo.put("doctorworkingdetails_id", (Object.get(positionn)).getString("id"));
                Log.e("ServertoSend", "123    " + jo.toString());
                showSlots(jo, getString(R.string.webData) + Constants.slotBooking, date, slots_grid);
                return;
            } catch (Exception e) {
                Log.e("ServertoSend", "123    " + e.toString());
                e.printStackTrace();
                return;
            }
        }


    }

    public void showSlots(JSONObject jo, String url, final String date, final ExpandableHeightGridView1 SlotGrid) {
        new CustomAsync1(this, jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {
                if (result == null || result.equals(BuildConfig.VERSION_NAME)) {
                    Snackbar snackBar = Snackbar.make(DoctorDescription.this.view, "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new OnClickListener() {
                        public void onClick(View view) {
                            finish();
                            startActivity(DoctorDescription.this.getIntent());
                        }
                    });
                    /*snackBar.setActionTextColor(-65536);
                    ((TextView) snackBar.getView().findViewById(R.id.snackbar_text)).setTextColor(-256);
                    snackBar.show();
                   */
                    return;
                }
                Log.e("callservice", "aa36363");

                try {
                    Log.e("callservice", "aa");
                    DatesOfSlots.clear();
                    BookedSlots.clear();
                    JSONObject jSONObject = new JSONObject(result);
                    String status = jSONObject.getString("status");
                    Toast.makeText(DoctorDescription.this, result, Toast.LENGTH_LONG).show();
                    if (status.equals("success")) {
                        JSONArray ja = jSONObject.getJSONArray("schedule");
                        JSONArray jaa = jSONObject.getJSONArray("appointmentsdata");
                        for (int i = 0; i < ja.length(); i++) {
                            String NoOfSlots = date + " " + ja.getString(i);
                            Log.e("ServerData", "12     " + NoOfSlots);
                            String finalSlots = ja.getString(i);
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                            if (simpleDateFormat.parse(simpleDateFormat.format(c.getTime())).getTime() + 7200000 < simpleDateFormat.parse(NoOfSlots).getTime()) {
                                DatesOfSlots.add(finalSlots);
                            }
                        }
                        for (int j = 0; j < jaa.length(); j++) {
                            BookedSlots.add(jaa.getString(j));
                        }
                        if (DatesOfSlots.size() == 0) {
                            Log.e("slotTime", "123     " + slotTime);
                            SlotGrid.setVisibility(View.GONE);
                        } else {
                            SlotGrid.setVisibility(View.VISIBLE);
                        }
                        Log.e("ServerLong", "12345     " + BookedSlots.size() + "     " + DatesOfSlots.size());
                        selectSlotsAdapter = new SelectSlotsAdapter(DoctorDescription.this, DatesOfSlots, BookedSlots);
                        SlotGrid.setAdapter(selectSlotsAdapter);
                    } else if (status.equals("no data found")) {
                        Toast.makeText(DoctorDescription.this, BuildConfig.VERSION_NAME + status, Toast.LENGTH_LONG).show();
                        BookedSlots.clear();
                        DatesOfSlots.clear();

                        selectSlotsAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.e("callservice", "aa36363" + e.toString());
                    e.printStackTrace();
                }
            }
        }).execute(new String[0]);
    }

    public void get_dates() {
        for (int i = 0; i < 7; i++) {
            //this.datess.add(getCalculatedDate("dd-MM-yyyy", i));
            this.datess.add(getCalculatedDate("yyyy-MM-dd", i));
            Collections.sort(this.datess);
        }
    }

    public class ListAdapter extends BaseAdapter {

        ArrayList<String> list_profile;
        LayoutInflater inflater;

        public ListAdapter(Context context, ArrayList<String> list) {
            // TODO Auto-generated constructor stub
            this.list_profile = list;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            Log.e("DAtaofArray", "123    " + list_profile);
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list_profile.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View viewww, ViewGroup parent) {
            // TODO Auto-generated method stub
            viewww = inflater.inflate(R.layout.doc_profile_list, parent, false);
            TextView tvNameView = (TextView) viewww.findViewById(R.id.textView);
            tvNameView.setText(list_profile.get(position));
            return viewww;
        }

    }

    public class DoctorDescriptionAdapter extends BaseAdapter {
        ArrayList<JSONObject> Object;
        Context con;
        LayoutInflater inflater;

        public DoctorDescriptionAdapter(Context con, ArrayList<JSONObject> Object) {
            this.con = con;
            this.Object = Object;
            this.inflater = (LayoutInflater) con.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return this.Object.size();
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder_doctor vh;
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.doctor_desc_row, parent, false);
                vh = new ViewHolder_doctor();
                vh.doctorName = (TextView) convertView.findViewById(R.id.doctor_row_name);
                vh.doctorAddress = (TextView) convertView.findViewById(R.id.doctor_row_desc);
                vh.doctorMobile = (TextView) convertView.findViewById(R.id.mobile);
                vh.doctorEmail = (TextView) convertView.findViewById(R.id.email);
                vh.doctorTime = (TextView) convertView.findViewById(R.id.time);
                vh.doctorPrice = (TextView) convertView.findViewById(R.id.doctor_row_price);
                vh.doctorBook = (Button) convertView.findViewById(R.id.doctor_row_book);
                vh.doctorDirection = (Button) convertView.findViewById(R.id.doctor_row_direction);
                vh.doctorName.setTypeface(DoctorDescription.this.monster);
                vh.doctorAddress.setTypeface(DoctorDescription.this.monster);
                vh.doctorPrice.setTypeface(DoctorDescription.this.monster);
                vh.doctorBook.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        datess.clear();

//                        final int position=(Integer)v.getTag();
                        Intent slot = new Intent(DoctorDescription.this, slot_selection.class);
                        slot.putExtra("ObjectData",DoctorDescriptionAdapter.this.Object.get(position).toString());
                        slot.putExtra("position",position);
                        slot.putExtra("doctorId",doctorId);
                        startActivity(slot);
//                        Log.e("pos", "" + position);
//                        Dialog dialog = new Dialog(DoctorDescription.this);
//                        dialog.requestWindowFeature(1);
//                        dialog.setContentView(R.layout.slots_popup);
//                        final TabLayout date_tab = (TabLayout) dialog.findViewById(R.id.dates_tab);
//                        TextView doctor_row_price = (TextView) dialog.findViewById(R.id.doctor_row_price);
//                        TextView doctor_name = (TextView) dialog.findViewById(R.id.doctor_name);
//                        TextView hosp_name = (TextView) dialog.findViewById(R.id.hspital_name);
//                        final TextView datte = (TextView) dialog.findViewById(R.id.date);
//                        timeW = (TextView) dialog.findViewById(R.id.time);
//                        timeW.setText("00:00");
//                        Button doctor_row_book = (Button) dialog.findViewById(R.id.doctor_row_book);
//                        final ExpandableHeightGridView1 expandableGridView1 = (ExpandableHeightGridView1) dialog.findViewById(R.id.slots_list);
//                        Calendar datee = Calendar.getInstance();
//                        DoctorDescription.this.get_dates();
//                        Drawable img1 = DoctorDescription.this.getResources().getDrawable(R.drawable.slot_date_bg_selector);
//                        Drawable img2 = DoctorDescription.this.getResources().getDrawable(R.drawable.slot_date_bg_selector2);
//                        String[] converted = null;
//                        final ArrayList<String> modifiedDates = new ArrayList<String>();
//                        String converteDates = "";
//                        modifiedDates.clear();
//                        for (int i = 0; i < datess.size(); i++) {
//                            converted = datess.get(i).split("-");
//                            converteDates = converted[2] + "-" + converted[1] + "-" + converted[0];
//                            modifiedDates.add(converteDates);
//                            Log.e("TheDataofString", "938   " + converteDates);
//                            View relativeLayout = (LinearLayout) LayoutInflater.from(DoctorDescription.this).inflate(R.layout.slot_date_item, date_tab, false);
//                            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.month);
//                            TextView date = (TextView) relativeLayout.findViewById(R.id.date);
//                            tabTextView.setBackgroundDrawable(img1);
//                            date.setBackgroundDrawable(img2);
//
//
//                            try {
//                                doctor_row_price.setText("\u20b9 " + (Object.get(position)).getString("fee"));
//                                //Date dateee = new SimpleDateFormat("dd-MM-yyyy").parse(datess.get(i));
//                                Date dateee = new SimpleDateFormat("dd-MM-yyyy").parse(converteDates);
//                                Date d = new Date();
//
//
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
//                                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM");
//                                SimpleDateFormat sdf = new SimpleDateFormat("EEE");
//                                String dayOfTheWeek = sdf.format(d);
//                                String goal = sdf.format(dateee);
//                                String day = simpleDateFormat.format(dateee);
//                                tabTextView.setText(goal);
//                                date.setText(day);
//                                String doc_name = doctorName.toString();
//                                doctor_name.setText(doc_name);
//                                // hosp_name.setText();
//                                //Hospital_name= getIntent().getStringExtra("hospital_name");
//
//                                String hospName = hospitalName.toString();
//
//                                hosp_name.setText(Object.get(position).getString("hospital_name"));
//
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            date_tab.addTab(date_tab.newTab().setCustomView(relativeLayout));
//                        }
//                        callServer((modifiedDates.get(0)).toString(), 0, Object, expandableGridView1, position);
//
//                        date_tab.addOnTabSelectedListener(new OnTabSelectedListener() {
//                            public void onTabSelected(Tab tab) {
//                                Log.e("ServertoSend0", tab.getPosition() + "123" + (modifiedDates.get(tab.getPosition())));
//                                try {
//                                    Log.e("ServertoSend1", "123");
//                                    datte.setText(modifiedDates.get(tab.getPosition()));
//                                    callServer((modifiedDates.get(tab.getPosition())).toString(), tab.getPosition(), Object, expandableGridView1, position);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    Log.e("ServertoSend2", "123" + e.toString());
//                                }
//
///*
//                                if (date_tab.getSelectedTabPosition()==3)
//                                {
//                                    try {
//
//                                        datte.setText(datess.get(tab.getPosition()));
//                                        callServer((datess.get(date_tab.getSelectedTabPosition())).toString(), date_tab.getSelectedTabPosition(), DoctorDescriptionAdapter.this.Object, expandableGridView1,position);
//                                        Log.e("ServertoSend33", "123    ");
//                                    } catch (Exception e)
//                                    {
//                                        e.printStackTrace();
//                                        Log.e("ServertoSend3", "123    " + e.toString());
//                                    }
//
//                                }
//*/
//                            }
//
//                            public void onTabUnselected(Tab tab) {
//                            }
//
//                            public void onTabReselected(Tab tab) {
//                            }
//                        });
//                        datte.setText(modifiedDates.get(date_tab.getSelectedTabPosition()));
//
//
//                        doctor_row_book.setOnClickListener(new OnClickListener() {
//                            public void onClick(View view) {
//                                try {
//                                    if (slotTime.equals(BuildConfig.VERSION_NAME)) {
//                                        Toast.makeText(DoctorDescription.this, "Select slot", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        DoctorDescription.this.startActivity(new Intent(DoctorDescription.this, Selected_candidate_page.class)
//                                                .putExtra("doctor_id", DoctorDescription.this.doctorId)
//                                                .putExtra("doctorworkingdetails_id", ((JSONObject)
//                                                        DoctorDescriptionAdapter.this.Object.get(position)).getString("id"))
//                                                .putExtra("hospital_id", ((JSONObject)
//                                                        DoctorDescriptionAdapter.this.Object.get(position)).getString("hospital_id"))
//                                                .putExtra("date", modifiedDates.get(date_tab.getSelectedTabPosition()))
//                                                .putExtra("hos_pos", position)
//                                                .putExtra("time", slotTime).putExtra(FirebaseAnalytics.Param.PRICE,
//                                                        ((JSONObject) DoctorDescriptionAdapter.this.Object.get(position)).getString("fee")));
//                                    }
//                                } catch (Exception e) {
//                                }
//                            }
//                        });
//
//                        Window window = dialog.getWindow();
//                        window.setLayout(-1, -2);
//                        window.setGravity(17);
//                        dialog.show();

                    }
                });
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder_doctor) convertView.getTag();
            }
            try {
                vh.doctorName.setText(((JSONObject) this.Object.get(position)).getString("hospital_name"));
                vh.doctorAddress.setText(((JSONObject) this.Object.get(position)).getString("address"));
                vh.doctorPrice.setText("\u20b9 " + ((JSONObject) this.Object.get(position)).getString("fee"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //vh.doctorBook.setTag(position);

            return convertView;
        }
    }

    public class SelectSlotsAdapter extends BaseAdapter {
        ArrayList<String> BookedSlots;
        ArrayList<String> DatesToDisplay;
        Context con;
        LayoutInflater inflater;
        int selected_slot = 0;

        public SelectSlotsAdapter(Context con, ArrayList<String> DatesToDisplay, ArrayList<String> BookedSlots) {
            this.con = con;
            this.DatesToDisplay = DatesToDisplay;
            this.BookedSlots = BookedSlots;
            this.inflater = (LayoutInflater) con.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return this.DatesToDisplay.size();
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.selectslots_row, parent, false);
                vh = new ViewHolder();
                vh.listDates = (RadioButton) convertView.findViewById(R.id.diffTimes);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            vh.listDates.setText((CharSequence) this.DatesToDisplay.get(position));
            vh.listDates.setChecked(position == selected_slot);
            vh.listDates.setTag(position);
            vh.listDates.setClickable(false);
            if (vh.listDates.isChecked()) {
                vh.listDates.setTextColor(Color.WHITE);
            } else {
                vh.listDates.setTextColor(Color.BLACK);

            }

            if (this.BookedSlots.contains(this.DatesToDisplay.get(position))) {
                vh.listDates.setPaintFlags(vh.listDates.getPaintFlags() | 16);
            } else {
                vh.listDates.setPaintFlags(vh.listDates.getPaintFlags() | 32);
                convertView.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        selected_slot = position;
                        notifyDataSetChanged();
                        DoctorDescription.this.slotTime = vh.listDates.getText().toString().trim();
                        Log.e("SlotTimeee", "123     " + DoctorDescription.this.slotTime);
                        DoctorDescription.this.timeW.setText(DoctorDescription.this.slotTime);
                    }
                });
            }
            return convertView;
        }

        public class ViewHolder {
            RadioButton listDates;
        }
    }

    class ViewHolder_doctor {
        TextView doctorAddress;
        Button doctorBook;
        Button doctorDirection;
        TextView doctorEmail;
        TextView doctorMobile;
        TextView doctorName;
        TextView doctorPrice;
        TextView doctorTime;

        ViewHolder_doctor() {
        }
    }
}
