package com.tiqs.rapmedix.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.ConnectionDetector;
import com.tiqs.rapmedix.ExpandableHeightGridView;
import com.tiqs.rapmedix.ExpandableHeightGridView1;
import com.tiqs.rapmedix.HealthRecords_data_model;
import com.tiqs.rapmedix.Prescription_List;
import com.tiqs.rapmedix.Prescription_Zoom;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.adapters.Health_records_list_adapter;
import com.tiqs.rapmedix.adapters.Prescription_list_adapter;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.droidsonroids.gif.BuildConfig;

/**
 * Created by ADMIN on 5/19/2017.
 */

public class Health_records_upload extends Fragment {

	String old_user_url,check_kit_id_url,vendor_login;
	ArrayList<String> image_title=new ArrayList<>();
	ArrayList<String> id=new ArrayList<>();
	ExpandableHeightGridView family_members_list;
	boolean isNet;
	ArrayList<HealthRecords_data_model> healthrecords_data_modell;
	ConnectionDetector connectionDetector;
	DataBase_Helper dataBase_helper;
	ExpandableHeightGridView1 list1;
    String Image;
    ImageView image;
    ArrayList<String []> images_prescription=new ArrayList<>();

    Prescription_list_adapter prescription_list_adapter;

    String finalImage="", image_url="";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.familymembers_list_main, container, false);

		list1=(ExpandableHeightGridView1)v.findViewById(R.id.family_members_list);
		//family_members_list.setAdapter(new Member_list_adpater(getActivity(),R.layout.appointments_list_item));

		connectionDetector = new ConnectionDetector(getActivity());
		isNet = connectionDetector.isConnectingToInternet();
		dataBase_helper=new DataBase_Helper(getActivity());
		healthrecords_data_modell=new ArrayList<>();
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
				jo.put("user_id", new DataBase_Helper(getActivity()).getUserId("1"));
				Log.e("U_di",""+jo.toString());

				get_healthrecords(jo, getResources().getString(R.string.server)+ Constants.userPrescriptions_service);



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


	public static Health_records_upload newInstance(String text) {

		Health_records_upload f = new Health_records_upload();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);


		return f;
	}
	public void get_healthrecords (JSONObject jo, String url) {

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

                         image_url = jo.getString("img_url");

						if (status.equals("success")) {
							JSONArray jsonArray = jo.getJSONArray("0");
							healthrecords_data_modell.clear();

							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String [] images= (image_url+jsonObject.getString("doctor_prescription")).split(",");
                                //finalImage = (jsonObject.getString("doctor_prescription"));
                                images_prescription.add(images);
								HealthRecords_data_model healthRecords_data_model = new HealthRecords_data_model();
								healthRecords_data_model.title = jsonObject.getString("title");
								healthRecords_data_model.user_name = "For "+jsonObject.getString("patient_name");
								healthRecords_data_model.doctor_name = jsonObject.getString("doctor_name");
								//healthRecords_data_model.specialization = jsonObject.getString("");
								healthRecords_data_model.date = jsonObject.getString("created_date").split(" ")[0];
								healthRecords_data_model.time = jsonObject.getString("created_date").split(" ")[1];
								//healthRecords_data_model.time = jsonObject.getString("appointment_time");

								healthrecords_data_modell.add(healthRecords_data_model);
							}
							Log.e("ar_size",""+healthrecords_data_modell.size());


							list1.setAdapter(new Health_records_list_adapter(getActivity(), R.layout.healthrecord_list_item, healthrecords_data_modell));

                            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    /*Intent intent = new Intent(getActivity(), Prescription_Zoom.class);

                                    intent.putExtra("image",finalImage);
                                    intent.putExtra("imageurl",image_url);

                                    getActivity().startActivity(intent);*/

                                    final Dialog dialog = new Dialog(getActivity());
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.prescription_imgae);
                                    Window window = dialog.getWindow();

                                    ViewPager viewPager = (ViewPager) dialog.findViewById(R.id.view_pager);


                                    prescription_list_adapter  = new Prescription_list_adapter(getActivity(), images_prescription.get(position));
                                    viewPager.setAdapter(prescription_list_adapter);



                                    Log.e("imagepresv",""+images_prescription.get(position)[0]);

                                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                                    dialog.show();

                                }
                            });

						} else {

							Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
						}


					} catch (Exception e) {

						e.printStackTrace();
						Log.e("excep",""+e.toString());

					}
				}

			}
		}); ca.execute();
	}

    public void Prescription_Image(JSONObject jo, String url) {
        new CustomAsync(getActivity(), jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {
                if (result.equals(BuildConfig.VERSION_NAME) || result == null) {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(R.id.root), "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                        public void onClick(View view) {
                            getActivity().finish();
                            getActivity().startActivity(getActivity().getIntent());
                        }
                    });
                    snackBar.show();
                    return;
                }
                try {
                    JSONObject jo = new JSONObject(result);
                    String image_url = jo.getString("img_url");

                    if (jo.getString("status").equals("success")) {

                        Image = image_url + jo.getString("doctor_prescription");

                        Log.e("image", "" + Image);

                    }
                } catch (Exception e) {
                    Log.e("Exception", "" + e.toString());

                    e.printStackTrace();
                }
            }
        }).execute(new String[0]);

    }

}