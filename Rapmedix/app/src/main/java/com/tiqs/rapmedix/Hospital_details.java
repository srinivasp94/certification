package com.tiqs.rapmedix;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import com.tiqs.rapmedix.fragments.Hospital_Tab_Fragment;

import com.tiqs.rapmedix.adapters.TabsPage_adapter;
import com.tiqs.rapmedix.fragments.Hospital_Tab_Fragment_gallery;
import com.tiqs.rapmedix.fragments.Hospital_Tab_Fragment_service;
import com.tiqs.rapmedix.fragments.Hospital_Tab_Fragment_tarrifs;
import com.tiqs.rapmedix.fragments.Hospital_Tab_Fragment_tieups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 6/3/2017.
 */

public class Hospital_details extends FragmentActivity
{

	private ViewPager viewPager;
	private ActionBar actionBar;
	private TabsPage_adapter tabPagerAdapter;
	TabLayout tabLayout;
	private String[] tabs = { "Home", "Tarrifs", "Services", "Tieups", "Gallery" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_detail_page);
		viewPager = (ViewPager) findViewById(R.id.pager);
		tabLayout = (TabLayout) findViewById(R.id.tab_layout);


		for (int i=0;i<tabs.length;i++)
		{
			tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
		}

		List<Fragment> fragments = getFragments();
		tabPagerAdapter = new TabsPage_adapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(tabPagerAdapter);
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		//pager.setAdapter(tabPagerAdapter);
	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();
		fList.add(Hospital_Tab_Fragment.newInstance(""));
		fList.add(Hospital_Tab_Fragment_tarrifs.newInstance(""));
		fList.add(Hospital_Tab_Fragment_service.newInstance(""));
		fList.add(Hospital_Tab_Fragment_tieups.newInstance(""));
		fList.add(Hospital_Tab_Fragment_gallery.newInstance(""));
		return fList;

	}


}

