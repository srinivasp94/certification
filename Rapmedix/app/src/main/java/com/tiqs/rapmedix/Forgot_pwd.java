package com.tiqs.rapmedix;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class Forgot_pwd extends Activity
{
	EditText mobile_number;
	LinearLayout mobile;
	Button submit;
	User user_data;
	View parentLayout, view;
	String valid_email,emailPattern ;
	String login_page_url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_pwd);
		parentLayout =findViewById(R.id.root_view);
		mobile_number = (EditText) findViewById(R.id.mobile_number_input);
		submit = (Button)findViewById(R.id.forgot_btn);

		final SharedPreferences sp = getSharedPreferences(MyFirebaseInstanceIDService.pref, this.MODE_PRIVATE);
		final String Notification = sp.getString("Notification", "Error");
		//Log.e("aa", "ccc" + Notification);
		//login_page_url = Constants.forgot_service;
		submit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {


					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("usermobile", mobile_number.getText().toString().trim());
						Log.e("mob",""+jsonObject);

						password_service(jsonObject,getResources().getString(R.string.server)+Constants.user_forgotpassword_service);
						//call_custom_asynch(jsonObject,login_page_url);
						Log.e("ser","");

					} catch (JSONException e) {
						e.printStackTrace();
						Log.e("err",""+e);
					}
				dissmis();

				}

		});

	}


	public	void password_service (JSONObject jo, final String url) {

		CustomAsync ca = new CustomAsync(Forgot_pwd.this, jo, url, new OnAsyncCompleteRequest() {

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
						Log.e("sta",""+status);

						if (status.equals("1"))
						{
							Toast.makeText(Forgot_pwd.this, "Your password send your register mobile number.", Toast.LENGTH_SHORT).show();
						}

						else {

							Toast.makeText(Forgot_pwd.this, "Enter valid mobile number.", Toast.LENGTH_SHORT).show();
						}


					}catch (Exception e) {

						e.printStackTrace();
					}


				}

			}
		});ca.execute();
	}

	public void dissmis(){
		Intent login_intent = new Intent(Forgot_pwd.this,member_login.class);
		startActivity(login_intent);
	}
	public void onBackPressed() {
		//super.onBackPressed();
		//finish();
		Intent back_intent = new Intent(Forgot_pwd.this, First_page.class);
		startActivity(back_intent);
	}


}