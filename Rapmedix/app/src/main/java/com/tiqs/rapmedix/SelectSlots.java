package com.tiqs.rapmedix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ADMIN on 2/13/2017.
 */

public class SelectSlots extends Activity {


	TextView Name, Address, Desig, HospitalName, Previous, Next, Today, TodayDate, day, time, Price, NoData;
	ImageView DoctorImage;
	LinearLayout Animator, Layout1, Layout2;
	View view;
	int count = 0;
	//Button Proceed;
	GridView SlotGrid;
	String Url = "", UserId= "", DoctorId="", WorkingId="", Designation="", Qualification="", DoctorName="",
			ProfileImage="", HospiatlName="", HospitalPrice="", slotTime="";
	boolean isNet;
	ArrayList <String> WeekDays = new ArrayList<String>();
	ArrayList <String> NameOfWeekDays = new ArrayList<String>();
	ArrayList <String> BookedSlots = new ArrayList<String>();
	ArrayList <String> SlotsStatus = new ArrayList<String>();

	ArrayList<String> DatesOfSlots = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_slot);

		Name = (TextView) findViewById(R.id.slot_name);
		Desig = (TextView) findViewById(R.id.slot_desig);
		Address = (TextView) findViewById(R.id.slot_quli);
		HospitalName = (TextView) findViewById(R.id.slot_hospitalName);
		Previous = (TextView) findViewById(R.id.slot_previous);
		Next = (TextView) findViewById(R.id.slot_next);
		Today = (TextView) findViewById(R.id.todayDay);
		TodayDate = (TextView) findViewById(R.id.todayDate);
		day = (TextView) findViewById(R.id.family_Day);
		time = (TextView) findViewById(R.id.family_Time);
		Price = (TextView) findViewById(R.id.family_Price);
		NoData = (TextView) findViewById(R.id.noData);
		view = findViewById(R.id.selectSlot);

		Animator = (LinearLayout) findViewById(R.id.slotAnimation);
		Layout1 = (LinearLayout) findViewById(R.id.slotLayout1);
		Layout2 = (LinearLayout) findViewById(R.id.slotLayout2);

		View SlotView = findViewById(R.id.add_slots);

		ImageView back = (ImageView) SlotView.findViewById(R.id.mainmenu);

		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				onBackPressed();
			}
		});

		DoctorImage = (ImageView) findViewById(R.id.slotImg);

		//Proceed = (Button) findViewById(R.id.familyBook);

		SlotGrid = (GridView) findViewById(R.id.slots_gridView);

		Intent intent = getIntent();
		Bundle b = intent.getExtras();

		if (b!= null)
		{

			DoctorId = b.getString("DoctorId");
			WorkingId = b.getString("workingId");
			Designation = b.getString("Desig");
			Qualification = b.getString("Qualification");
			DoctorName = b.getString("DoctorName");
			ProfileImage = b.getString("ProfileImage");
			HospiatlName = b.getString("HospitalName");
			HospitalPrice = b.getString("DoctorPrice");

		}

		Desig.setText(Designation);
		Address.setText(Qualification);
		Name.setText(DoctorName);
		HospitalName.setText(HospiatlName);
		Log.e("hosname",""+HospiatlName);
		Price.setText(HospitalPrice);

		Glide
				.with(SelectSlots.this)
				.load(ProfileImage)
				.centerCrop()
				.placeholder(R.drawable.single)
				.crossFade()
				.into(DoctorImage);

		Url = getString(R.string.webData)+ Constants.slotBooking;

		ConnectionDetector cd = new ConnectionDetector(this);
		isNet = cd.isConnectingToInternet();

		DataBase_Helper db = new DataBase_Helper(this);
		UserId = db.getUserId("1");

		Calendar c = Calendar.getInstance();

		SimpleDateFormat input = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate2 = input.format(c.getTime());

		//SimpleDateFormat output = new SimpleDateFormat("EEEE dd-MM-yyyy hh:mm:ss");

		try {
			Date date = input.parse(formattedDate2);
			long milli = date.getTime();

			for (int i = 0; i<7; i++) {

				if (i == 0) {

					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(milli);

					int mYear = calendar.get(Calendar.YEAR);
					int mMonth = calendar.get(Calendar.MONTH)+1;
					int mDay = calendar.get(Calendar.DAY_OF_MONTH);

					String FinalDate = String.valueOf(mDay)+"-"+String.valueOf(mMonth)+"-"+String.valueOf(mYear);

					//Log.e("DatesofWeek", "123     "+ FinalDate);

					SimpleDateFormat input1 = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat output1 = new SimpleDateFormat("EEEE");

					Date dat = input1.parse(FinalDate);
					String UpdatedDate = output1.format(dat);

					NameOfWeekDays.add(UpdatedDate);

					WeekDays.add(FinalDate);
				}

				else {

					milli = milli + 86400000;

					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(milli);

					int mYear = calendar.get(Calendar.YEAR);
					int mMonth = calendar.get(Calendar.MONTH) + 1;
					int mDay = calendar.get(Calendar.DAY_OF_MONTH);

					String FinalDate = String.valueOf(mDay) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mYear);

					SimpleDateFormat input1 = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat output1 = new SimpleDateFormat("EEEE");

					Date dat = input1.parse(FinalDate);
					String UpdatedDate = output1.format(dat);

					NameOfWeekDays.add(UpdatedDate);

					WeekDays.add(FinalDate);

				}
			}


		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (count == 0) {

			Layout1.setVisibility(View.INVISIBLE);
			Today.setText(NameOfWeekDays.get(0));
			TodayDate.setText(WeekDays.get(0));
			Next.setText(NameOfWeekDays.get(1).substring(0, 3));

		}

		callServer();

		Next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				count++;

				YoYo.with(Techniques.ZoomInRight)
						.duration(350)
						.playOn(Animator);

				if (count < 6) {

					Layout1.setVisibility(View.VISIBLE);
					Previous.setText(NameOfWeekDays.get(count - 1).substring(0, 3));
					Next.setText(NameOfWeekDays.get(count + 1).substring(0, 3));
					Today.setText(NameOfWeekDays.get(count));
					TodayDate.setText(WeekDays.get(count));

					callServer();

					//Log.e("CountNumber", "123    "+ count);

				}

				else {

					Layout2.setVisibility(View.INVISIBLE);
					Today.setText(NameOfWeekDays.get(count));
					TodayDate.setText(WeekDays.get(count));
					Previous.setText(NameOfWeekDays.get(count-1).substring(0, 3));

					callServer();
				}

			}
		});

		Previous.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				count--;


				YoYo.with(Techniques.ZoomInLeft)
						.duration(350)
						.playOn(Animator);

				if (count >= 1) {

					Layout1.setVisibility(View.VISIBLE);
					Layout2.setVisibility(View.VISIBLE);
					Previous.setText(NameOfWeekDays.get(count - 1).substring(0, 3));
					Next.setText(NameOfWeekDays.get(count + 1).substring(0, 3));
					Today.setText(NameOfWeekDays.get(count));
					TodayDate.setText(WeekDays.get(count));

					callServer();
					//Log.e("CountNumber", "123    " + count);
				}

				else {

					Layout1.setVisibility(View.INVISIBLE);
					Next.setText(NameOfWeekDays.get(count + 1).substring(0, 3));
					Today.setText(NameOfWeekDays.get(count));
					TodayDate.setText(WeekDays.get(count));

					callServer();

					//Log.e("CountNumber", "12345    " + count);
				}
			}
		});

		/*Proceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (slotTime.equals("")) {

					Snackbar snackBar = Snackbar.make(view, "Please Select atleast One Slot!", Snackbar.LENGTH_LONG)
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
				}

				else {

					Intent intent = new Intent(SelectSlots.this, SelectFamilyMemebers.class);
					startActivity(intent);
				}
			}
		});*/

	}

	public class SelectSlotsAdapter extends BaseAdapter {

		Context con;
		ArrayList<String> DatesToDisplay;
		ArrayList<String> BookedSlots;
		LayoutInflater inflater;

		public SelectSlotsAdapter (Context con, ArrayList<String> DatesToDisplay, ArrayList<String> BookedSlots) {

			this.con = con;
			this.DatesToDisplay = DatesToDisplay;
			this.BookedSlots = BookedSlots;
			inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}


		@Override
		public int getCount() {
			return DatesToDisplay.size();
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
			final ViewHolder vh;


			if (convertView == null) {
				convertView = inflater.inflate(R.layout.selectslots_row, parent, false);
				vh = new ViewHolder();

				vh.listDates = (TextView) convertView.findViewById(R.id.diffTimes);

				convertView.setTag(vh);

			} else {
				vh = (ViewHolder) convertView.getTag();

			}


			vh.listDates.setText(DatesToDisplay.get(position));

			if (BookedSlots.contains (DatesToDisplay.get(position))) {

				vh.listDates.setPaintFlags(vh.listDates.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
			}

			else {

				vh.listDates.setPaintFlags(vh.listDates.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);

				convertView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						slotTime = vh.listDates.getText().toString().trim();

						Log.e("SlotTimeee", "123     "+ slotTime);

						Intent intent = new Intent(SelectSlots.this, SelectFamilyMemebers.class);
						intent.putExtra("slotDay", Today.getText().toString().substring(0,3));
						intent.putExtra("slotDate", TodayDate.getText().toString());
						intent.putExtra("slotTime", slotTime);
						intent.putExtra("PriceFee", Price.getText().toString().trim());
						intent.putExtra("DoctorId", DoctorId);
						intent.putExtra("WorkingId", WorkingId);
						intent.putExtra("DoctorName", DoctorName);
						//intent.putExtra("HospitalName", HospiatlName);
						startActivity(intent);
					}
				});
			}

			return convertView;
		}

	}

	public class ViewHolder {

		TextView listDates;

	}


	public void showSlots (JSONObject jo, String url) {

		CustomAsync1 ca = new CustomAsync1(SelectSlots.this, jo, url, new OnAsyncCompleteRequest() {
			@Override
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
				}

				else {

					try {

						JSONObject jo = new JSONObject(result);

						String status = jo.getString("status");

						if (status.equals("success")) {

							DatesOfSlots.clear();
							BookedSlots.clear();

							JSONArray ja = jo.getJSONArray("schedule");
							JSONArray jaa = jo.getJSONArray("appointmentsdata");



							for (int i = 0; i < ja.length(); i++) {


								String NoOfSlots = TodayDate.getText().toString() + " " + ja.getString(i);

								Log.e("ServerData", "12     "+ NoOfSlots);

								String finalSlots = ja.getString(i);

								Calendar c = Calendar.getInstance();

								//String currentDateTimeString = String.valueOf(c.getTime());

								SimpleDateFormat slotinput = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
								String slotDate2 = slotinput.format(c.getTime());

								//Log.e("ServerSlot", "123     "+ slotDate2);

								Date date1 = slotinput.parse(slotDate2);
								Date date2 = slotinput.parse(NoOfSlots);

								//Log.e("ServerDate", "1234     "+ date1+ "     "+ date2);

								long finalSlot1 = date1.getTime() + 7200000;
								long finalSlot2 = date2.getTime();

								//Log.e("ServerLong", "12345     "+ finalSlot1+ "     "+ finalSlot2);

								if (finalSlot1 < finalSlot2)
								{

									DatesOfSlots.add(finalSlots);
								}

							}

								for (int i = 0; i<jaa.length(); i++)
								{

									String bookedSlots = jaa.getString(i);

									BookedSlots.add(bookedSlots);
								}

							if (DatesOfSlots.size() == 0) {

								NoData.setVisibility(View.VISIBLE);
								SlotGrid.setVisibility(View.GONE);
							} else {

								NoData.setVisibility(View.GONE);
								SlotGrid.setVisibility(View.VISIBLE);
							}

							SelectSlotsAdapter ssa = new SelectSlotsAdapter(SelectSlots.this, DatesOfSlots,BookedSlots);
							SlotGrid.setAdapter(ssa);

						}

					}catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		}); ca.execute();
	}

	public  void callServer () {

		if (isNet) {


			try {

				JSONObject jo = new JSONObject();
				jo.put("date", TodayDate.getText().toString().trim());
				jo.put("doctor_id", DoctorId);
				jo.put("doctorworkingdetails_id", WorkingId);

				Log.e("ServertoSend", "123    "+ jo.toString());

				showSlots(jo, Url);


			}catch (Exception e) {

				e.printStackTrace();
			}

		}else {

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
