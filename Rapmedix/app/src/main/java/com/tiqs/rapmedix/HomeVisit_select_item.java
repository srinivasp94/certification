package com.tiqs.rapmedix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ADMIN on 6/9/2017.
 */

public class HomeVisit_select_item extends AppCompatActivity
{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_visits_list);
		View view=findViewById(R.id.hdrawer_layout);
		ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
		back_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
}
