package com.tiqs.rapmedix.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.tiqs.rapmedix.ConnectionDetector;
import com.tiqs.rapmedix.ExpandableHeightGridView1;
import com.tiqs.rapmedix.Famili_member_dat_model;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.SelectFamilyMembersAdapter;
import com.tiqs.rapmedix.Selected_candidate_page;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.droidsonroids.gif.BuildConfig;

public class member_list extends Fragment {
    String AppDate = BuildConfig.VERSION_NAME;
    String AppDay = BuildConfig.VERSION_NAME;
    String AppTime = BuildConfig.VERSION_NAME;
    String DoctorId = BuildConfig.VERSION_NAME;
    String DoctorName = BuildConfig.VERSION_NAME;
    String Doctor_Working_details_id = BuildConfig.VERSION_NAME;
    String Fee = BuildConfig.VERSION_NAME;
    FloatingActionButton Floating;
    String Hospital_id = BuildConfig.VERSION_NAME;
    String MobileNo = BuildConfig.VERSION_NAME;
    String Name = BuildConfig.VERSION_NAME;
    String Url = BuildConfig.VERSION_NAME;
    String UserId = BuildConfig.VERSION_NAME;
    String check_kit_id_url;
    ArrayList<Famili_member_dat_model> famili_member_dat_models;
    ExpandableHeightGridView1 family_members_list;
    ArrayList<String> id = new ArrayList();
    ArrayList<String> image_title = new ArrayList();
    boolean isNet;
    String old_user_url;
    String vendor_login;
    View vie;
    JSONObject jo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.familymembers_list_main, container, false);
        this.family_members_list = (ExpandableHeightGridView1) v.findViewById(R.id.family_members_list);
        this.DoctorId = getActivity().getIntent().getStringExtra("doctor_id");
        this.Doctor_Working_details_id = getActivity().getIntent().getStringExtra("doctorworkingdetails_id");
        this.Hospital_id = getActivity().getIntent().getStringExtra("hospital_id");
        Log.e("data", this.DoctorId + "   " + this.Doctor_Working_details_id + "   " + this.Hospital_id);
        this.isNet = new ConnectionDetector(getActivity()).isConnectingToInternet();
        this.famili_member_dat_models = new ArrayList();

        try {
            jo = new JSONObject();
            jo.put("user_id", new DataBase_Helper(getActivity()).getUserId("1"));
            getFamilyDetails(jo, getString(R.string.server) + Constants.selectFamily);

        } catch (Exception e) {
            e.printStackTrace();

        }
       // startTimer();
        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.isNet) {
            try {
                jo = new JSONObject();
                jo.put("user_id", new DataBase_Helper(getActivity()).getUserId("1"));
                getFamilyDetails(jo, getString(R.string.server) + Constants.selectFamily);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Snackbar snackBar = Snackbar.make(getActivity().findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new OnClickListener() {
            public void onClick(View view) {
                member_list.this.getActivity().finish();
                member_list.this.startActivity(member_list.this.getActivity().getIntent());
            }
        });
            snackBar.show();
    }

    public void startTimer() {
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            jo = new JSONObject();
                            jo.put("user_id", new DataBase_Helper(getActivity()).getUserId("1"));
                            getFamilyDetails(jo, getString(R.string.server) + Constants.selectFamily);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                });
            }
        };
        t.scheduleAtFixedRate(task, 0, 100000);
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        getFamilyDetails(jo, getString(R.string.server) + Constants.selectFamily);
//    }

    public static boolean isEmailValid(String email) {
        if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE).matcher(email).matches()) {
            return true;
        }
        return false;
    }

    public static member_list newInstance(String text) {
        member_list f = new member_list();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public void getFamilyDetails(JSONObject jo, String url) {
        new CustomAsync(getActivity(), jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {
                if (result.equals(BuildConfig.VERSION_NAME) || result == null) {
                    Snackbar snackBar = Snackbar.make(member_list.this.getActivity().findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new OnClickListener() {
                        public void onClick(View view) {
                            member_list.this.getActivity().finish();
                            member_list.this.startActivity(member_list.this.getActivity().getIntent());
                        }
                    });
                        snackBar.show();
                    return;
                }
                try {
                    JSONObject jo = new JSONObject(result);
                    if (jo.getString("status").equals("success")) {
                        String UserName = jo.getString("username");
                        JSONArray ja = jo.getJSONArray("familydata");
                        member_list.this.famili_member_dat_models.clear();
                        for (int i = 0; i < ja.length(); i++)
                        {
                            JSONObject j = ja.getJSONObject(i);
                            Famili_member_dat_model famili_member_dat_model = new Famili_member_dat_model();
                            famili_member_dat_model.name = j.getString("name");

                         /*   SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            String date = formatter.format(j.getString("dateofbirth"));
                           */;


                            famili_member_dat_model.dob = j.getString("dateofbirth");
                            famili_member_dat_model.relation = j.getString("relation_ship");
                            famili_member_dat_model.Mobile = j.getString("mobile");
                            famili_member_dat_model.delete = j.getString("id");
                            member_list.this.famili_member_dat_models.add(famili_member_dat_model);
                        }
                        Log.e("SS", "SS" + member_list.this.famili_member_dat_models.size());
                        member_list.this.family_members_list.setAdapter(new SelectFamilyMembersAdapter(member_list.this.getActivity(), member_list.this.famili_member_dat_models));
                        member_list.this.family_members_list.setOnItemClickListener(new OnItemClickListener() {
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                member_list.this.startActivity(new Intent(member_list.this.getActivity(), Selected_candidate_page.class).putExtra("name", ((Famili_member_dat_model) member_list.this.famili_member_dat_models.get(i)).name).putExtra("mobile", ((Famili_member_dat_model) member_list.this.famili_member_dat_models.get(i)).Mobile).putExtra("doctor_id", member_list.this.DoctorId).putExtra("doctorworkingdetails_id", member_list.this.Doctor_Working_details_id).putExtra("hospital_id", member_list.this.Hospital_id).putExtra("date", member_list.this.getActivity().getIntent().getStringExtra("date")).putExtra("time", member_list.this.getActivity().getIntent().getStringExtra("time")).putExtra(Param.PRICE, member_list.this.getActivity().getIntent().getStringExtra(Param.PRICE)));
                            }
                        });
                    }
                } catch (Exception e)
                {
                    Log.e("Exception",""+e.toString());

                    e.printStackTrace();
                }
            }
        }).execute(new String[0]);
    }
}
