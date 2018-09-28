package com.tiqs.rapmedix.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tiqs.rapmedix.Add_family_member;
import com.tiqs.rapmedix.ConnectionDetector;
import com.tiqs.rapmedix.Home_Page;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.SelectFamilyMemebers;
import com.tiqs.rapmedix.Selected_candidate_page;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.ArrayAdapter_EditPage;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;
import org.json.JSONObject;
import pl.droidsonroids.gif.BuildConfig;

public class add_member_page extends Fragment {
    Button Above18;
    Button AddMember;
    String AppDate = BuildConfig.VERSION_NAME;
    String AppDay = BuildConfig.VERSION_NAME;
    String AppTime = BuildConfig.VERSION_NAME;
    Button Below18;
    TextView Birthday;
    String DoctorId = BuildConfig.VERSION_NAME;
    String DoctorName = BuildConfig.VERSION_NAME;
    EditText Email;
    String Fee = BuildConfig.VERSION_NAME;
    EditText Mobile;
    EditText Name;
    TextView Relation;
    ArrayList<String> Relations = new ArrayList();
    String ReturnValue = BuildConfig.VERSION_NAME;
    String Url = BuildConfig.VERSION_NAME;
    String UserId = BuildConfig.VERSION_NAME;
    String WorkingId = BuildConfig.VERSION_NAME;
    String check_kit_id_url;
    int count = 2;
    String dattte = BuildConfig.VERSION_NAME;
    ArrayList<String> id = new ArrayList();
    ArrayList<String> image_title = new ArrayList();
    boolean isNet;
    String old_user_url;
    RelativeLayout r1;
    RelativeLayout r2;
    RelativeLayout r3;
    RelativeLayout r4;
    RelativeLayout r5;
    String vendor_login;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_family_member_page, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.Name = (EditText) view.findViewById(R.id.member_name);
        this.Mobile = (EditText) view.findViewById(R.id.member_mobile);
        this.Email = (EditText) view.findViewById(R.id.member_email);
        this.Relation = (TextView) view.findViewById(R.id.member_relation);
        this.Birthday = (TextView) view.findViewById(R.id.member_birthday);
        this.Above18 = (Button) view.findViewById(R.id.above);
        this.Below18 = (Button) view.findViewById(R.id.below);
        this.AddMember = (Button) view.findViewById(R.id.member_add);
        this.r1 = (RelativeLayout) view.findViewById(R.id.r1);
        this.r2 = (RelativeLayout) view.findViewById(R.id.r2);
        this.r3 = (RelativeLayout) view.findViewById(R.id.r3);
        this.r4 = (RelativeLayout) view.findViewById(R.id.r4);
        this.r5 = (RelativeLayout) view.findViewById(R.id.r5);
        this.Above18.setBackgroundColor(Color.parseColor("#ed3237"));
        this.Above18.setTextColor(Color.parseColor("#ffffff"));
        Bundle b = getActivity().getIntent().getExtras();
        if (b != null) {
            this.ReturnValue = b.getString("returnValues");
            this.DoctorId = b.getString("DoctorId");
            this.WorkingId = b.getString("WorkingId");
            this.AppDate = b.getString("AppDate");
            this.AppDay = b.getString("AppDay");
            this.AppTime = b.getString("AppTime");
            this.Fee = b.getString("Fee");
            this.DoctorName = b.getString("DoctorName");
        }


        this.isNet = new ConnectionDetector(getActivity()).isConnectingToInternet();
        this.UserId = new DataBase_Helper(getActivity()).getUserId("1");
        this.Url = getString(R.string.server) + Constants.addFamily;
        this.Relations.clear();
        this.Relations.add("Wife");
        this.Relations.add("Husband");
        this.Relations.add("Son");
        this.Relations.add("Daughter");
        this.Relations.add("Father");
        this.Relations.add("Mother");
        this.Relations.add("Grand Father");
        this.Relations.add("Grand Mother");
        this.Relations.add("Sister");
        this.Relations.add("Brother");
        this.Relations.add("Father In Law");
        this.Relations.add("Mother In Law");
        this.Relations.add("Sister In Law");
        this.Relations.add("Brother In Law");
        this.Relations.add("Cousin");
        this.Relations.add("Other");
        this.Birthday.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(add_member_page.this.getActivity(), new OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        add_member_page.this.Birthday.setText(new StringBuilder().append(dayOfMonth).append("-").append(monthOfYear + 1).append("-").append(year).append(" "));
                        add_member_page.this.dattte = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                    }
                },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        this.Above18.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                add_member_page.this.count = 2;
                add_member_page.this.Relations.clear();
                add_member_page.this.Relations.add("Wife");
                add_member_page.this.Relations.add("Husband");
                add_member_page.this.Relations.add("Son");
                add_member_page.this.Relations.add("Daughter");
                add_member_page.this.Relations.add("Father");
                add_member_page.this.Relations.add("Mother");
                add_member_page.this.Relations.add("Grand Father");
                add_member_page.this.Relations.add("Grand Mother");
                add_member_page.this.Relations.add("Sister");
                add_member_page.this.Relations.add("Brother");
                add_member_page.this.Relations.add("Father In Law");
                add_member_page.this.Relations.add("Mother In Law");
                add_member_page.this.Relations.add("Sister In Law");
                add_member_page.this.Relations.add("Brother In Law");
                add_member_page.this.Relations.add("Cousin");
                add_member_page.this.Relations.add("Other");
                add_member_page.this.Name.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.Relation.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.Birthday.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.Mobile.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.Email.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.r2.setVisibility(View.VISIBLE);
                add_member_page.this.r3.setVisibility(View.VISIBLE);
                add_member_page.this.Below18.setTextColor(Color.parseColor("#707070"));
                add_member_page.this.Below18.setBackgroundColor(0);
                add_member_page.this.Above18.setBackgroundColor(Color.parseColor("#ed3237"));
                add_member_page.this.Above18.setTextColor(Color.parseColor("#ffffff"));
            }
        });
        this.Below18.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                add_member_page.this.count = 1;
                add_member_page.this.Relations.clear();
                add_member_page.this.Relations.add("Son");
                add_member_page.this.Relations.add("Daughter");
                add_member_page.this.Relations.add("Sister");
                add_member_page.this.Relations.add("Brother");
                add_member_page.this.Relations.add("Cousin");
                add_member_page.this.Relations.add("Others");
                add_member_page.this.Name.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.Relation.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.Birthday.setText(BuildConfig.VERSION_NAME);
                add_member_page.this.r2.setVisibility(View.GONE);
                add_member_page.this.r3.setVisibility(View.GONE);
                add_member_page.this.Below18.setBackgroundColor(Color.parseColor("#ed3237"));
                add_member_page.this.Below18.setTextColor(Color.parseColor("#ffffff"));
                add_member_page.this.Above18.setTextColor(Color.parseColor("#707070"));
                add_member_page.this.Above18.setBackgroundColor(0);
            }
        });
        this.Relation.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final String[] searchitems = (String[]) add_member_page.this.Relations.toArray(new String[add_member_page.this.Relations.size()]);
                final Dialog alertDialog = new Dialog(add_member_page.this.getActivity());
                alertDialog.getWindow();
                alertDialog.setCancelable(true);
              //  alertDialog.getWindow().setBackgroundDrawableResource(17170445);
                alertDialog.requestWindowFeature(1);
                alertDialog.getWindow().setGravity(17);
                alertDialog.setContentView(R.layout.slist);
                ListView lv = (ListView) alertDialog.findViewById(R.id.sp_list);
                ArrayAdapter_EditPage ad = new ArrayAdapter_EditPage(add_member_page.this.getActivity(), R.layout.spin_item, searchitems);
                lv.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        add_member_page.this.Relation.setText(searchitems[position]);
                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }
                });
                lv.setAdapter(ad);
                alertDialog.show();
            }
        });
        this.AddMember.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                JSONObject jo;

                if (add_member_page.this.count == 2) {
                    if (add_member_page.this.Name.getText().toString().trim().equals(BuildConfig.VERSION_NAME) || add_member_page.this.Mobile.getText().toString().trim().equals(BuildConfig.VERSION_NAME) || add_member_page.this.Relation.getText().toString().trim().equals(BuildConfig.VERSION_NAME) || add_member_page.this.Birthday.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                        Toast.makeText(add_member_page.this.getActivity(), "Fields should not be empty", Toast.LENGTH_LONG).show();
                    } else if (add_member_page.this.isNet) {
                        try {
                            jo = new JSONObject();
                            jo.put("user_id", add_member_page.this.UserId);
                            jo.put("membername", add_member_page.this.Name.getText().toString().trim());
                            jo.put("relationship", add_member_page.this.Relation.getText().toString().trim());
                            jo.put("birthdate", add_member_page.this.Birthday.getText().toString().trim());
                            jo.put("age_type", BuildConfig.VERSION_NAME + add_member_page.this.count);
                            jo.put("emailid", add_member_page.this.Email.getText().toString().trim());
                            jo.put("mobilenumber", add_member_page.this.Mobile.getText().toString().trim());
                            Log.e("FamilyMembers", jo.toString());
                            add_member_page.this.addMember(jo, add_member_page.this.Url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(add_member_page.this.getActivity(), "No Internent Connection", Toast.LENGTH_LONG).show();
                    }
                } else if (add_member_page.this.count != 1) {
                } else {
                    if (add_member_page.this.Name.getText().toString().trim().equals(BuildConfig.VERSION_NAME) || add_member_page.this.Relation.getText().toString().trim().equals(BuildConfig.VERSION_NAME) || add_member_page.this.Birthday.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                        Toast.makeText(add_member_page.this.getActivity(), "Fields should not be empty", Toast.LENGTH_LONG).show();
                    } else if (add_member_page.this.isNet) {
                        try {
                            jo = new JSONObject();
                            jo.put("user_id", add_member_page.this.UserId);
                            jo.put("membername", add_member_page.this.Name.getText().toString().trim());
                            jo.put("relationship", add_member_page.this.Relation.getText().toString().trim());
                            jo.put("birthdate", add_member_page.this.Birthday.getText().toString().trim());
                            jo.put("age_type", add_member_page.this.count);
                            add_member_page.this.addMember(jo, add_member_page.this.Url);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        Toast.makeText(add_member_page.this.getActivity(), "No Internent Connection", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    public static boolean isEmailValid(String email) {
        if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE).matcher(email).matches()) {
            return true;
        }
        return false;
    }
    public void dismiss() {
        getActivity().finish();
        Intent i = new Intent(getActivity(), Selected_candidate_page.class);  //your class
        startActivity(i);

    }

    public static add_member_page newInstance(String text) {
        add_member_page f = new add_member_page();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public void addMember(JSONObject jo, String url) {
        new CustomAsync(getActivity(), jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {
                if (result.equals(BuildConfig.VERSION_NAME) || result == null) {
                    Toast.makeText(add_member_page.this.getActivity(), "Please retry", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String status = new JSONObject(result).getString("status");
                    if (status.equals("success")) {
                        Toast.makeText(add_member_page.this.getActivity(), "You Added Family Number", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(), add_member_page.class));

                        if (add_member_page.this.ReturnValue.equals("1")) {
                            Intent intent = new Intent(add_member_page.this.getActivity(), SelectFamilyMemebers.class);
                            intent.putExtra("DoctorId", add_member_page.this.DoctorId);
                            intent.putExtra("WorkingId", add_member_page.this.WorkingId);
                            intent.putExtra("slotDate", add_member_page.this.AppDate);
                            intent.putExtra("slotDay", add_member_page.this.AppDay);
                            intent.putExtra("slotTime", add_member_page.this.AppTime);
                            intent.putExtra("PriceFee", add_member_page.this.Fee);
                            intent.putExtra("DoctorName", add_member_page.this.DoctorName);
                            add_member_page.this.startActivity(intent);
                            return;
                        }
                        add_member_page.this.getActivity().finish();
                        add_member_page.this.startActivity(new Intent(add_member_page.this.getActivity(), Add_family_member.class));
                        return;
                    }
                    Toast.makeText(add_member_page.this.getActivity(), BuildConfig.VERSION_NAME + status, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).execute(new String[0]);
    }
}
