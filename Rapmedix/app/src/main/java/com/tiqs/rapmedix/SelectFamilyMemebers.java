package com.tiqs.rapmedix;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by ADMIN on 2/13/2017.
 */

public class SelectFamilyMemebers extends Activity {

	TextView FName, day, time, price;
	EditText AlternateMobile;
	Button Submit;
	ListView familyData;
	FloatingActionButton Floating;
	boolean isNet;
	View vie;

	String UserId="",Url="",BookNowUrl="",DoctorId="", WorkingId="", AppDate="", AppTime="",AppDay="",Fee="", Name="", MobileNo="", DoctorName="";

	ArrayList<String> Namee = new ArrayList<String>();
	ArrayList<String> Relation = new ArrayList<String>();
	ArrayList<String> Mobile = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_family_members);

		FName = (TextView) findViewById(R.id.family_Name);
		day = (TextView) findViewById(R.id.family_Day);
		time = (TextView) findViewById(R.id.family_Time);
		price = (TextView) findViewById(R.id.family_Price);

		AlternateMobile = (EditText) findViewById(R.id.family_alternateMobile);

		Submit = (Button) findViewById(R.id.familyBook);

		familyData = (ListView) findViewById(R.id.familyList);

		View view = findViewById(R.id.add_family);
		vie = findViewById(R.id.parentfamilyList);

		ImageView back = (ImageView) view.findViewById(R.id.mainmenu);

		Floating = (FloatingActionButton) findViewById(R.id.fButton);

		Intent intent = getIntent();
		Bundle b = intent.getExtras();

		if (b!=null)
		{

			DoctorId = b.getString("DoctorId");
			WorkingId = b.getString("WorkingId");
			AppDate = b.getString("slotDate");
			AppDay = b.getString("slotDay");
			AppTime = b.getString("slotTime");
			Fee = b.getString("PriceFee");
			DoctorName = b.getString("DoctorName");

		}

		day.setText(AppDay);
		time.setText(AppTime);
		price.setText(Fee);

		ConnectionDetector cd = new ConnectionDetector(this);
		isNet = cd.isConnectingToInternet();

		Url = getString(R.string.server)+ Constants.selectFamily;
		BookNowUrl = getString(R.string.webData)+Constants.bookAppointment;

		DataBase_Helper db = new DataBase_Helper(this);
		UserId = db.getUserId("1");
		Name = db.getUserName("1");
		MobileNo = db.getUserMobileNumber("1");

		Namee.clear();
		Relation.clear();
		Mobile.clear();

		if (isNet) {

			try {

				JSONObject jo = new JSONObject();

				jo.put("user_id",UserId);
				getFamilyDetails(jo, Url);

			}catch (Exception e) {

				e.printStackTrace();
			}
		}
		else {

			Snackbar snackBar = Snackbar.make(vie, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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

		Floating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(SelectFamilyMemebers.this, AddMember.class);
				intent.putExtra("returnValues", "1");
				intent.putExtra("DoctorId", DoctorId);
				intent.putExtra("WorkingId", WorkingId);
				intent.putExtra("AppDate", AppDate);
				intent.putExtra("AppDay", AppDay);
				intent.putExtra("AppTime", AppTime);
				intent.putExtra("Fee", Fee);
				intent.putExtra("DoctorName", DoctorName);
				startActivity(intent);
			}
		});

		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		Submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (isNet) {

					if (AlternateMobile.isShown()) {

						if (AlternateMobile.getText().toString().trim().length() != 10) {

							AlternateMobile.setError("Invalid Mobile Number");
						}

						else {

							try {

								JSONObject jo = new JSONObject();

								jo.put("user_id",UserId);
								jo.put("doctor_id",DoctorId);
								jo.put("doctorworkingdetails_id",WorkingId);
								jo.put("appointment_date",AppDate);
								jo.put("appointment_time",AppTime);
								jo.put("fee",Fee);
								jo.put("patient_name",FName.getText().toString().trim());
								jo.put("patient_mobile",MobileNo);
								jo.put("alternate_mobile", AlternateMobile.getText().toString().trim());

								bookNow(jo, BookNowUrl);

								//selectFamily("Something went Wrong", "Facing Technical Issue Please try again");


							}catch (Exception e) {

								e.printStackTrace();
							}

						}

					}

					else {

						try {

							JSONObject jo = new JSONObject();

							jo.put("user_id", UserId);
							jo.put("doctor_id", DoctorId);
							jo.put("doctorworkingdetails_id", WorkingId);
							jo.put("appointment_date", AppDate);
							jo.put("appointment_time", AppTime);
							jo.put("fee", Fee);
							jo.put("patient_name", FName.getText().toString().trim());
							jo.put("patient_mobile", MobileNo);
							jo.put("alternate_mobile", "");

							bookNow(jo, BookNowUrl);

							//selectFamily("Something went Wrong", "Facing Technical Issue Please try again");


						} catch (Exception e) {

							e.printStackTrace();
						}

					}
				}
				else {

					Snackbar snackBar = Snackbar.make(vie, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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
		});
	}

	public class SelectFamilyMembersAdapter extends BaseAdapter {

		ArrayList<String> Name, Relation, Mobile;
		Context con;
		LayoutInflater inflater;
		int selectedPosition = 0;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		public SelectFamilyMembersAdapter(Context con, ArrayList<String> Name, ArrayList<String> Relation, ArrayList<String> Mobile) {

			this.Name = Name;
			this.Relation = Relation;
			this.Mobile = Mobile;
			this.con = con;
			inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}


		@Override
		public int getCount() {
			return Name.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder vh;


			if (convertView == null) {
				convertView = inflater.inflate(R.layout.select_family_member_row, parent, false);
				vh = new ViewHolder();

				vh.name = (TextView) convertView.findViewById(R.id.family_row_name);
				vh.relation = (TextView) convertView.findViewById(R.id.family_row_relation);
				vh.mobile = (TextView) convertView.findViewById(R.id.family_row_mobile);
				vh.Select = (RadioButton) convertView.findViewById(R.id.family_row_radio);
				vh.relative = (RelativeLayout) convertView.findViewById(R.id.relativelayout);

				//int padding = Namee.size();

//				vh.name.setTypeface(monster);
//				vh.relation.setTypeface(monster);
//				vh.mobile.setTypeface(monster);

				convertView.setTag(vh);

			} else {
				vh = (ViewHolder) convertView.getTag();

			}

			vh.Select.setChecked(position == selectedPosition);
			vh.Select.setTag(position);
			vh.Select.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					selectedPosition = (Integer)v.getTag();

					FName.setText(Namee.get(position));

					if (selectedPosition == 0) {


						AlternateMobile.setVisibility(View.GONE);
					}
					else {

						AlternateMobile.setVisibility(View.VISIBLE);
					}

					notifyDataSetChanged();
				}
			});

			if (position == Namee.size()-1) {

				params.setMargins(0,0,0,70);
				vh.relative.setLayoutParams(params);
			}

			else {

				params.setMargins(0,0,0,0);
				vh.relative.setLayoutParams(params);

			}


			vh.name.setText(Namee.get(position));
			vh.relation.setText("("+Relation.get(position)+")");
			vh.mobile.setText(Mobile.get(position));

			return convertView;
		}

	}

	public class ViewHolder {

		TextView name, relation, mobile;
		RadioButton Select;
		RelativeLayout relative;
	}

	public void getFamilyDetails (JSONObject jo, String url) {

		CustomAsync ca = new CustomAsync(SelectFamilyMemebers.this, jo, url, new OnAsyncCompleteRequest() {
			@Override
			public void asyncResponse(String result) {

				if (result.equals("") || result == null) {

					Snackbar snackBar = Snackbar.make(vie, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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

							Namee.clear();
							Relation.clear();
							Mobile.clear();

							String UserName = jo.getString("username");

							Namee.add(UserName);
							Relation.add("me");
							Mobile.add(MobileNo);

							FName.setText(UserName);

							JSONArray ja = jo.getJSONArray("familydata");

							for (int i = 0; i<ja.length(); i++) {

								JSONObject j = ja.getJSONObject(i);

								Namee.add(j.getString("name"));
								Relation.add(j.getString("relation_ship"));
								Mobile.add(j.getString("mobile"));

							}

							SelectFamilyMembersAdapter sfm = new SelectFamilyMembersAdapter(SelectFamilyMemebers.this, Namee, Relation, Mobile);
							familyData.setAdapter(sfm);
						}


					}catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		}); ca.execute();
	}

	public void bookNow (JSONObject jo, String url) {

		CustomAsync ca = new CustomAsync(SelectFamilyMemebers.this, jo, url, new OnAsyncCompleteRequest() {
			@Override
			public void asyncResponse(String result) {

				if (result == null || result.equals("")) {

					Snackbar snackBar = Snackbar.make(vie, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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

							selectFamily("Thanks for Booking", "Your slot booked with "+DoctorName+ "on "+AppDate+" at "+ AppTime);
						}

						else {

							Snackbar snackBar = Snackbar.make(vie, "Please try again!", Snackbar.LENGTH_INDEFINITE)
									.setAction("", new View.OnClickListener() {
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


					}catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		});ca.execute();
	}

	public void  selectFamily ( String Header ,String maintext)
	{final Dialog dialog = new Dialog(SelectFamilyMemebers.this);
		// Include dialog.xml file
		dialog.setCancelable(false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.booknow_pop);
		Window window = dialog.getWindow();
		//   window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
		//window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);

		window.setGravity(Gravity.CENTER);
		window.setBackgroundDrawable(new ColorDrawable(
				Color.TRANSPARENT));

		TextView header  = (TextView) dialog.findViewById(R.id.header);
		TextView maintextt  = (TextView) dialog.findViewById(R.id.main_text);
		Button call  = (Button)dialog.findViewById(R.id.call);

		call.setText("OK");

		header.setText(Header);
		maintextt.setText(maintext);

		call.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

				Intent intent = new Intent(SelectFamilyMemebers.this, Home_Page.class);
				startActivity(intent);

				dialog.dismiss();


			}
		});

		dialog.show();

	}

}
