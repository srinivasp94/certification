package com.tiqs.rapmedix;


import android.content.Context;

import android.content.pm.PackageManager;
import android.graphics.Color;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceBuffer;
import com.tiqs.rapmedix.Categories_list_adapter;
import com.tiqs.rapmedix.Doctor_List_page;
import com.tiqs.rapmedix.Doctor_list_adapter;
import com.tiqs.rapmedix.Doctor_list_helper;
import com.tiqs.rapmedix.Manifest;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Created by ADMIN on 6/1/2017.
 */

public class Categories_list_page extends FragmentActivity
{
	ListView doctor_list;
	CheckBox search_for;
	TextView header,address;
	private ArrayList<Doctor_list_helper> deptList = new ArrayList<>();
	private LinkedHashMap<Integer, Doctor_list_helper> myDepartments = new LinkedHashMap<Integer, Doctor_list_helper>();
	InputMethodManager inputMethodManager;
	private AutoCompleteTextView actv;
	ArrayAdapter<String> adapter;
	ImageView menu,add_address;

	Categories_list_adapter doctor_list_adapter;
	View rootview;

	ArrayList<String>doctor_names;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.categories_list_page);
		rootview =findViewById(R.id.root_view);

		doctor_list=(ListView)findViewById(R.id.doctors_list);
		menu=(ImageView)findViewById(R.id.menu);
		add_address=(ImageView)findViewById(R.id.add_address);
		address=(TextView) findViewById(R.id.location);

		doctor_names =new ArrayList<>();

		actv = (AutoCompleteTextView) findViewById(R.id.search_input);
		actv.setHint(Html.fromHtml("<small>" +
				"Enter Doctor Name" + "</small>"));




		adapter= new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,doctor_names);
		actv.setAdapter(adapter);

		search_for=(CheckBox)findViewById(R.id.search_for);
		header=(TextView) findViewById(R.id.header);

		inputMethodManager =
				(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

		search_for.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b)
				{
					header.setVisibility(View.GONE);
					actv.setVisibility(View.VISIBLE);
					actv.requestFocus();


					inputMethodManager.toggleSoftInputFromWindow(
							compoundButton.getApplicationWindowToken(),
							InputMethodManager.SHOW_FORCED, 0);
					//search_input.setFocusable(true);

				}
				else
				{


					header.setVisibility(View.VISIBLE);
					actv.setVisibility(View.GONE);

					inputMethodManager.toggleSoftInputFromWindow(
							compoundButton.getApplicationWindowToken(),
							InputMethodManager.RESULT_HIDDEN, 0);

				}
			}
		});
		actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				int duration = 500;  //miliseconds
				int offset = 0;      //fromListTop
				String starttext=adapterView.getItemAtPosition(i).toString();
				int i0=doctor_names.indexOf(starttext);


				Log.e("zz",starttext+"  nn  "+i+"  aa  "+adapterView.getSelectedItemPosition());
				doctor_list.smoothScrollToPositionFromTop(  i0,offset,duration);
			}
		});


		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			}
		});



		add_address.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});



	}



	public  void fadein()
	{
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setBackgroundColor(Color.RED);
		fadeIn.setDuration(3000);
		AnimationSet animation = new AnimationSet(false); //change to false
		animation.addAnimation(fadeIn);
		doctor_list.setAnimation(animation);
	}
	private  void mobile_verification(JSONObject jo, String url)
	{
		CustomAsync ca=new CustomAsync(Categories_list_page.this, jo, url, new OnAsyncCompleteRequest() {

			@Override
			public void asyncResponse(String result) {
				// TODO Auto-generated method stub
				if(result==null||result.equals(""))
				{
					Toast.makeText(Categories_list_page.this, "Please Retry", Toast.LENGTH_SHORT).show();
				}
				else{
					try {
						JSONObject j = new JSONObject(result);
						String status = j.getString("status");


						if (status.equals("success"))
						{
							//doctor_list_adapter = new Categories_list_adapter(Doctor_List_page.this, deptList);
							// doctor_list.setAdapter(new Doctor_list_adapter(Doctor_List_page.this,profile_pics,names,specialisation_names,experiences,degree_names,hospital_names,distances));
							doctor_list.setAdapter(doctor_list_adapter);


						}
						else
						{
							if (status.equals("no data found")) {
								Snackbar.make(rootview, "No doctors available in your location", Snackbar.LENGTH_LONG).show();
								deptList.clear();
								doctor_list_adapter.notifyDataSetChanged();
							}
						}



					}
					catch(Exception e)
					{

						e.printStackTrace();
					}

				}
			}

		});
		ca.execute();
	}
	public String deDup(String s) {
		return new LinkedHashSet<String>(Arrays.asList(s.split(","))).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", "-");
	}



	AdapterView.OnItemClickListener mAutocompleteClickListener
			= new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


		}
	};

	ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
			= new ResultCallback<PlaceBuffer>() {
		@Override
		public void onResult(PlaceBuffer places) {
			if (!places.getStatus().isSuccess()) {
				Log.e("log", "Place query did not complete. Error: " +
						places.getStatus().toString());
				return;
			}
			// Selecting the first object buffer.


		}
	};
	private boolean checkPermission()
	{
		int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
		if (result == PackageManager.PERMISSION_GRANTED) {

			return true;

		} else {

			return false;

		}
	}


	private void requestPermission() {

		if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

			// Toast.makeText(this, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

		} else {

			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
		}
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();


	}

}

