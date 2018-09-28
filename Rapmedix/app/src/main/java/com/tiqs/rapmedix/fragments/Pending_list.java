package com.tiqs.rapmedix.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.Appointment_data_model;
import com.tiqs.rapmedix.Appointments;
import com.tiqs.rapmedix.ConnectionDetector;
import com.tiqs.rapmedix.ExpandableHeightGridView;
import com.tiqs.rapmedix.ExpandableHeightGridView1;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.adapters.Appointments_list_adapter;
import com.tiqs.rapmedix.adapters.Member_list_adpater;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Pending_list extends Fragment {

    String old_user_url,check_kit_id_url,vendor_login;
    ArrayList<String> image_title=new ArrayList<>();
    ArrayList<String> id=new ArrayList<>();
    ExpandableHeightGridView family_members_list;
    boolean isNet;
    ArrayList<Appointment_data_model> appointment_data_modell;
    ConnectionDetector connectionDetector;
    DataBase_Helper dataBase_helper;
    ExpandableHeightGridView1 list1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.familymembers_list_main, container, false);

        list1=(ExpandableHeightGridView1)v.findViewById(R.id.family_members_list);
        //family_members_list.setAdapter(new Member_list_adpater(getActivity(),R.layout.appointments_list_item));

        connectionDetector = new ConnectionDetector(getActivity());
        isNet = connectionDetector.isConnectingToInternet();
        dataBase_helper=new DataBase_Helper(getActivity());
        appointment_data_modell=new ArrayList<>();
        //list1=(ExpandableHeightGridView1)v.findViewById(R.id.family_members_list);
/*
        family_members_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), Selected_candidate_page.class));
            }
        });
*/




        if (isNet) {
            try {
                JSONObject jo = new JSONObject();
                jo.put("user_id",new DataBase_Helper(getActivity()).getUserId("1"));
                Log.e("U_di",""+jo.toString());

                get_appointments(jo, getResources().getString(R.string.server) + Constants.userAppointments_service);
            } catch (Exception e) {

                e.printStackTrace();

            }

        } else {

            Snackbar snackBar = Snackbar.make(getActivity().findViewById(R.id.main_content), "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                        }
                    });
            snackBar.setActionTextColor(Color.RED);
            View sbView = snackBar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackBar.show();


        }


        return v;
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    public static Pending_list newInstance(String text) {

        Pending_list f = new Pending_list();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);


        return f;
    }
    public void get_appointments (JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(getActivity(), jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {


                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(R.id.main_content), "Please try Again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                }

                else {
                    try
                    {

                        JSONObject jo = new JSONObject(result);

                        String status = jo.getString("status");
                        if (status.equals("success")) {
                            JSONArray jsonArray = jo.getJSONArray("0");
                            appointment_data_modell.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Appointment_data_model appointment_data_model = new Appointment_data_model();
                                appointment_data_model.doctor_name = jsonObject.getString("name");
                                appointment_data_model.appointment_id = jsonObject.getString("id");
                                appointment_data_model.specialization = jsonObject.getString("category_name");
                                appointment_data_model.hos_name = jsonObject.getString("hospital_name");
                                appointment_data_model.hos_place = jsonObject.getString("location");
                                appointment_data_model.date = jsonObject.getString("appointment_date");
                                appointment_data_model.time = jsonObject.getString("appointment_time");

                                if (jsonObject.getString("status").equals("1"))
                                {
                                    appointment_data_modell.add(appointment_data_model);
                                }
                            }
                            Log.e("ar_size",""+appointment_data_modell.size());
                            list1.setAdapter(new Appointments_list_adapter(getActivity(), R.layout.appointments_list_item, appointment_data_modell));


                        } else {

                            Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        Log.e("catch",""+e);

                        e.printStackTrace();
                    }
                }

            }
        }); ca.execute();
    }

}

