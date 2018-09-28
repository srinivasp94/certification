package com.tiqs.rapmedix;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiqs.rapmedix.adapters.Health_records_adapter;

/**
 * Created by ADMIN on 5/19/2017.
 */

public class Prescription extends AppCompatActivity {
	String [] tabs={"Prescription","Upload New"};
	//String [] tabsc={"Prescription Uploaded","Upload New"};

	TabLayout tabLayout;
	ViewPager viewPager_home;
	Health_records_adapter add_member_adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_record);
		tabLayout=(TabLayout)findViewById(R.id.tab_layout);
		viewPager_home=(ViewPager)findViewById(R.id.view_pager);

		View view=findViewById(R.id.hdrawer_layout);
		ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
		back_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		if (getIntent().getIntExtra("id",0)==3)

		{
				RelativeLayout relativeLayout = (RelativeLayout)
						LayoutInflater.from(this).inflate(R.layout.tabs, tabLayout, false);
				TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
			    tabTextView.setText("Prescriptions");
				//tabTextView.setText(tabs[i]);

				//   tabLayout.addTab(tabLayout.newTab().setText(maincatt.getString("category_name")));
				tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

		}

			/*for (int i=0;i<tabsc.length;i++)
			{  RelativeLayout relativeLayout = (RelativeLayout)
					LayoutInflater.from(this).inflate(R.layout.tabs, tabLayout, false);
				TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
				tabTextView.setText(tabsc[i]);

				//   tabLayout.addTab(tabLayout.newTab().setText(maincatt.getString("category_name")));
				tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

			}*/

		add_member_adapter=new Health_records_adapter(getSupportFragmentManager());
		viewPager_home.setAdapter(add_member_adapter);


		viewPager_home.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager_home.setCurrentItem(tab.getPosition());


			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

	}
}
