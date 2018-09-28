package com.tiqs.rapmedix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.firebase.receivers.MyFirebaseInstanceIDService;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;
import com.tiqs.rapmedix.utils.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ADMIN on 6/14/2017.
 */

public class Registation_page extends AppCompatActivity
{
	EditText name_reg, mobile_num, email_reg, pwd_reg, confirm_pwd, referral_code;
	Button reg_button;
	TextView login_text;
	View v;
	String login_page_url;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registation_page);

		name_reg = (EditText) findViewById(R.id.name_reg);
		mobile_num = (EditText) findViewById(R.id.mob_number);
		email_reg = (EditText) findViewById(R.id.email_reg);
		pwd_reg = (EditText) findViewById(R.id.pwd_reg);
		confirm_pwd = (EditText) findViewById(R.id.confirm_pwd);
		referral_code = (EditText) findViewById(R.id.referral_code);
		reg_button = (Button) findViewById(R.id.submit_reg);
		login_text = (TextView) findViewById(R.id.login_text);

		login_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent login_intent = new Intent(Registation_page.this, member_login.class);
				startActivity(login_intent);
			}
		});
		/*final SharedPreferences sp = Registation_page.this.getSharedPreferences(MyFirebaseInstanceIDService.pref, Registation_page.this.MODE_PRIVATE);
		final String Notification = sp.getString("Notification", "Error");*/

		login_page_url =Constants.login;

		email_reg.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{

			}

			@Override
			public void afterTextChanged(Editable editable)
			{

			}
		});


		referral_code.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable)
			{
				if (referral_code.getText().toString().trim().length()==6)
				{
					if (new ConnectionDetector(Registation_page.this).isConnectingToInternet())
					{
						try
						{
							JSONObject jsonObject=new JSONObject();

							jsonObject.put("referee",referral_code.getText().toString().trim());
							checkReferral_code(jsonObject,getResources().getString(R.string.server)+Constants.check_Referral_Code);
						}catch ( Exception e)
						{
							Log.e("exception",""+e.toString());

						}
					}
					else
					{
						Toast.makeText(Registation_page.this, "Please check your internet connectivity", Toast.LENGTH_SHORT).show();
					}

				}

			}
		});

		reg_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


               /* DataBase_Helper dataBaseHelper =new DataBase_Helper(getActivity());
                    user_data.setId("khusaki");
                    user_data.setName("khusaki tech");
                   user_data.setEmail("gjgjg@gmail.com");
                   user_data.setMobile("9908816530");
                   user_data.setCity("hyderab");
                   user_data.setUid("001123");
                   dataBaseHelper.insertData(user_data);*/

				if (new ConnectionDetector(Registation_page.this).isConnectingToInternet()) {

					/*final SharedPreferences sp = Registation_page.this.getSharedPreferences(MyFirebaseInstanceIDService.pref, Registation_page.this.MODE_PRIVATE);
		final String Notification = sp.getString("Notification", "Error");*/

					if (name_reg.getText().toString().trim().equals("") || mobile_num.getText().toString().trim().equals("") || pwd_reg.getText().toString().trim().equals("")) {

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
						Log.e("log for email", "email invalid");

						final SharedPreferences sp = Registation_page.this.getSharedPreferences(MyFirebaseInstanceIDService.pref, Registation_page.this.MODE_PRIVATE);
						final String Notification = sp.getString("Notification", "");


						JSONObject jsonObject = new JSONObject();
						try {
							jsonObject.put("name", name_reg.getText().toString().trim());
							jsonObject.put("mobile", mobile_num.getText().toString().trim());
							jsonObject.put("email_id", email_reg.getText().toString().trim());
							jsonObject.put("password", pwd_reg.getText().toString().trim());
							jsonObject.put("cpassword", confirm_pwd.getText().toString().trim());
							jsonObject.put("referee", referral_code.getText().toString().trim());
							jsonObject.put("device_token", Notification);
							jsonObject.put("device_type", "1");

							call_custom_asynch(jsonObject, login_page_url);
							Log.e("ASDFG", "ASDDF" + jsonObject);

						} catch (JSONException e) {
							e.printStackTrace();
						}


					}
				}

				else {

					Toast.makeText(Registation_page.this, "Please check Internet connection", Toast.LENGTH_SHORT).show();
				}

			}


		});




	}

	private  void call_custom_asynch(JSONObject jo, String url)
	{
		CustomAsync ca=new CustomAsync(Registation_page.this, jo, url, new OnAsyncCompleteRequest() {

			@Override
			public void asyncResponse(String result) {
				// TODO Auto-generated method stub
				if(result==null||result.equals(""))
				{
					Toast.makeText(Registation_page.this, "Please Retry", Toast.LENGTH_SHORT).show();
				}
				else{
					try{
						JSONObject j=new JSONObject(result);
						String status=j.getString("status");
						if(status.equals("success"))
						{
							String uid=j.getString("user_id");
							String mobile=j.getString("mobile");
							String namee=j.getString("name");
							String otp=j.getString("otp");

//Log.e("otp","otp  "+otp+"  mob  "+mobile+"  uid  "+uid);
							Toast.makeText(Registation_page.this, ""+uid, Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(Registation_page.this, Otp_page.class);
							intent.putExtra("uid",uid);
							intent.putExtra("mobile",mobile);
							intent.putExtra("otp",otp);
							intent.putExtra("name",namee);
							startActivity(intent);
							Registation_page.this.finish();

						}
						else{

							Toast.makeText(Registation_page.this, ""+status, Toast.LENGTH_SHORT).show();
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
	private  void checkReferral_code(JSONObject jo, String url)
	{
		CustomAsync ca=new CustomAsync(Registation_page.this, jo, url, new OnAsyncCompleteRequest() {

			@Override
			public void asyncResponse(String result) {
				// TODO Auto-generated method stub
				if(result==null||result.equals(""))
				{
					Toast.makeText(Registation_page.this, "Please Retry", Toast.LENGTH_SHORT).show();
				}
				else{
					try{
						JSONObject j=new JSONObject(result);
						String status=j.getString("status");
						Log.e("suc",""+status);
						if(status.equals("success"))
						{

							Toast.makeText(Registation_page.this, "Referral code is success", Toast.LENGTH_SHORT).show();

						}
						else
						{

							Toast.makeText(Registation_page.this, "Invalid referral code", Toast.LENGTH_SHORT).show();
						}



					}
					catch(Exception e)
					{
						e.printStackTrace();
						Log.e("Excep",""+e.toString());
					}
				}
			}

		});
		ca.execute();
	}

	public void onBackPressed() {
		//super.onBackPressed();
		//finish();
		Intent back_intent = new Intent(Registation_page.this, First_page.class);
		startActivity(back_intent);
	}

}
