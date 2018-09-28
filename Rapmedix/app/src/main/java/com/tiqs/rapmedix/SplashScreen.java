package com.tiqs.rapmedix;

import com.tiqs.rapmedix.data_base.DataBase_Helper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
	
	private static int SPLASH_TIME_OUT = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);


		new Handler().postDelayed(new Runnable() {



			@Override
			public void run() {
				new DbCheck().execute();
				finish();
			}
		}, SPLASH_TIME_OUT);


	   }
	
	private class DbCheck extends AsyncTask<String, String, String>{

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			if(result!=null)
			{
			if(result.equals("0"))
			{
				finish();
				startActivity(new Intent(SplashScreen.this,First_page.class));
			}
			else{
				finish();
				startActivity(new Intent(SplashScreen.this,Home_Page.class));
			}
			}
			else{
				
			}
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			DataBase_Helper dh=new DataBase_Helper(SplashScreen.this);
			int c=dh.getUserCount();
			if(c==0)
			{
				return "0";
				/*finish();
				startActivity(new Intent(SplashScreen.this,Registration.class));*/
			}
			else
			{
				return "1";
			}
			
		}
		
	}
	

}
