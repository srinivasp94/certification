package com.tiqs.rapmedix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ADMIN on 6/9/2017.
 */

public class HealthCheckup_select_item extends AppCompatActivity
{
	Button booknow;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_checkup_item);
		booknow = (Button) findViewById(R.id.booknow);

		booknow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(HealthCheckup_select_item.this, "Your diagnostic will booked", Toast.LENGTH_SHORT).show();
				dismiss();
			}
		});
	}
	public void dismiss() {
		HealthCheckup_select_item.this.finish();
		Intent i = new Intent(HealthCheckup_select_item.this, Home_Page.class);  //your class
		startActivity(i);

	}
}
