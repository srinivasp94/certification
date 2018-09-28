package com.tiqs.rapmedix;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.ArrayAdapter_EditPage;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ADMIN on 2/6/2017.
 */

public class AddMember extends Activity {

	Button Above18, Below18, AddMember;
	EditText Name, Mobile, Email;
	TextView Birthday, Relation;
	RelativeLayout r1, r2, r3, r4, r5;
	View view;
	int count = 2;
	boolean isNet;
	String UserId="", Url="", dattte="", ReturnValue="", DoctorId="",WorkingId="",AppDate="",AppDay="",AppTime="",Fee="",DoctorName="";

	ArrayList <String> Relations = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_member);

		Name = (EditText) findViewById(R.id.member_name);
		Mobile = (EditText) findViewById(R.id.member_mobile);
		Email = (EditText) findViewById(R.id.member_email);
		Relation = (TextView) findViewById(R.id.member_relation);
		Birthday = (TextView) findViewById(R.id.member_birthday);

		Above18 = (Button) findViewById(R.id.above);
		Below18 = (Button) findViewById(R.id.below);
		AddMember = (Button) findViewById(R.id.member_add);

		view = findViewById(R.id.add_memberview);
		View myLayout = findViewById(R.id.prfl_head);

		ImageView back = (ImageView) myLayout.findViewById(R.id.mainmenu);


		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		r1 = (RelativeLayout) findViewById(R.id.r1);
		r2 = (RelativeLayout) findViewById(R.id.r2);
		r3 = (RelativeLayout) findViewById(R.id.r3);
		r4 = (RelativeLayout) findViewById(R.id.r4);
		r5 = (RelativeLayout) findViewById(R.id.r5);

		Above18.setBackgroundColor(Color.parseColor("#ed3237"));
		Above18.setTextColor(Color.parseColor("#ffffff"));

		Intent intent = getIntent();
		Bundle b = intent.getExtras();

		if (b!=null) {

			ReturnValue = b.getString("returnValues");
			DoctorId = b.getString("DoctorId");
			WorkingId = b.getString("WorkingId");
			AppDate = b.getString("AppDate");
			AppDay = b.getString("AppDay");
			AppTime = b.getString("AppTime");
			Fee = b.getString("Fee");
			DoctorName = b.getString("DoctorName");
		}

		ConnectionDetector cd = new ConnectionDetector(this);
		isNet = cd.isConnectingToInternet();

		DataBase_Helper db = new DataBase_Helper(this);
		UserId = db.getUserId("1");
		Url = getString(R.string.server)+ Constants.addFamily;

		Relations.clear();

		Relations.add("Wife");
		Relations.add("Husband");
		Relations.add("Son");
		Relations.add("Daughter");
		Relations.add("Father");
		Relations.add("Mother");
		Relations.add("Grand Father");
		Relations.add("Grand Mother");
		Relations.add("Sister");
		Relations.add("Brother");
		Relations.add("Father In Law");
		Relations.add("Mother In Law");
		Relations.add("Sister In Law");
		Relations.add("Brother In Law");
		Relations.add("Cousin");
		Relations.add("Other");

		Birthday.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				java.util.Calendar c= java.util.Calendar.getInstance();
				int cyear=c.get(java.util.Calendar.YEAR);
				int cmonth= c.get(java.util.Calendar.MONTH);
				int cdate=c.get(java.util.Calendar.DAY_OF_MONTH);



				final DatePickerDialog dpd=new DatePickerDialog(AddMember.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

						Birthday.setText(new StringBuilder()
								// Month is 0 based so add 1
								.append(dayOfMonth).append("-")
								.append(monthOfYear + 1).append("-")
								.append(year).append(" "));
						dattte=year+"/"+(monthOfYear+1)+"/"+dayOfMonth;

					}
				}, cyear, cmonth, cdate);

				dpd.show();

			}
		});


		Above18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				count = 2;

				Relations.clear();

				Relations.add("Wife");
				Relations.add("Husband");
				Relations.add("Son");
				Relations.add("Daughter");
				Relations.add("Father");
				Relations.add("Mother");
				Relations.add("Grand Father");
				Relations.add("Grand Mother");
				Relations.add("Sister");
				Relations.add("Brother");
				Relations.add("Father In Law");
				Relations.add("Mother In Law");
				Relations.add("Sister In Law");
				Relations.add("Brother In Law");
				Relations.add("Cousin");
				Relations.add("Other");

				Name.setText("");
				Relation.setText("");
				Birthday.setText("");
				Mobile.setText("");
				Email.setText("");
				r2.setVisibility(View.VISIBLE);
				r3.setVisibility(View.VISIBLE);
				Below18.setTextColor(Color.parseColor("#707070"));
				Below18.setBackgroundColor(Color.TRANSPARENT);
				Above18.setBackgroundColor(Color.parseColor("#ed3237"));
				Above18.setTextColor(Color.parseColor("#ffffff"));

			}
		});

		Below18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				count = 1;

				Relations.clear();

				Relations.add("Son");
				Relations.add("Daughter");
				Relations.add("Sister");
				Relations.add("Brother");
				Relations.add("Cousin");
				Relations.add("Others");

				Name.setText("");
				Relation.setText("");
				Birthday.setText("");
				r2.setVisibility(View.GONE);
				r3.setVisibility(View.GONE);
				Below18.setBackgroundColor(Color.parseColor("#ed3237"));
				Below18.setTextColor(Color.parseColor("#ffffff"));
				Above18.setTextColor(Color.parseColor("#707070"));
				Above18.setBackgroundColor(Color.TRANSPARENT);
			}
		});

		Relation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				final String [] searchitems = Relations.toArray(new String[Relations.size()]);
				final Dialog alertDialog = new Dialog(AddMember.this);
				alertDialog.getWindow();
				alertDialog.setCancelable(true);
				alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

				alertDialog.getWindow().setGravity(Gravity.CENTER);
				alertDialog.setContentView(R.layout.slist);

				ListView lv = (ListView) alertDialog.findViewById(R.id.sp_list);

				ArrayAdapter_EditPage ad = new ArrayAdapter_EditPage(AddMember.this, R.layout.spin_item, searchitems);
				lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
											int position, long id) {
						Relation.setText(searchitems[position]);
						////Log.e("SpinnerData", "123    "+ searchitems.length);
						//itype=String.valueOf(position+1);
						if (alertDialog.isShowing()) {
							alertDialog.dismiss();
						}
					}
				});

				lv.setAdapter(ad);

				alertDialog.show();
			}
		});



		AddMember.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (count == 2) {

					if (Name.getText().toString().trim().equals("") || Mobile.getText().toString().trim().equals("") ||
							Relation.getText().toString().trim().equals("") || Birthday.getText().toString().trim().equals("")) {

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

						if (isNet) {

							try {

								JSONObject jo = new JSONObject();
								jo.put("user_id",UserId);
								jo.put("membername",Name.getText().toString().trim());
								jo.put("relationship",Relation.getText().toString().trim());
								jo.put("birthdate",Birthday.getText().toString().trim());
								jo.put("age_type",count);
								jo.put("emailid",Email.getText().toString().trim());
								jo.put("mobilenumber",Mobile.getText().toString().trim());

								Log.e("FamilyMembers", jo.toString());

								addMember(jo, Url);


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

				else if (count == 1){

					if (Name.getText().toString().trim().equals("")  ||
							Relation.getText().toString().trim().equals("") || Birthday.getText().toString().trim().equals("")) {

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

						if (isNet) {

							try {

								JSONObject jo = new JSONObject();
								jo.put("user_id",UserId);
								jo.put("membername",Name.getText().toString().trim());
								jo.put("relationship",Relation.getText().toString().trim());
								jo.put("birthdate",Birthday.getText().toString().trim());
								jo.put("age_type",count);

								addMember(jo, Url);


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

			}
		});

	}

	public void addMember (JSONObject jo, String url) {

		CustomAsync ca = new CustomAsync(AddMember.this, jo, url, new OnAsyncCompleteRequest() {
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

						if (status.equals("success")) {

							Toast.makeText(AddMember.this, "You Added Family Number", Toast.LENGTH_SHORT).show();

							if (ReturnValue.equals("1")) {

								Intent intent = new Intent(com.tiqs.rapmedix.AddMember.this, SelectFamilyMemebers.class);

								intent.putExtra("DoctorId", DoctorId);
								intent.putExtra("WorkingId", WorkingId);
								intent.putExtra("slotDate", AppDate);
								intent.putExtra("slotDay", AppDay);
								intent.putExtra("slotTime", AppTime);
								intent.putExtra("PriceFee", Fee);
								intent.putExtra("DoctorName", DoctorName);
								startActivity(intent);

							}

							else {

								Intent intent = new Intent(com.tiqs.rapmedix.AddMember.this, Home_Page.class);
								startActivity(intent);

							}
						}

						else {

							Toast.makeText(AddMember.this, ""+status, Toast.LENGTH_SHORT).show();
						}


					}catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		}); ca.execute();
	}
}
