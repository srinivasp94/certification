package com.tiqs.rapmedix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tiqs.rapmedix.fragments.Gallery_Fragment;
import com.tiqs.rapmedix.fragments.Home_Fragment;
import com.tiqs.rapmedix.fragments.Hospital_Tab_Fragment;
import com.tiqs.rapmedix.fragments.Services_Fragment;
import com.tiqs.rapmedix.fragments.Tarrifs_Fragment;
import com.tiqs.rapmedix.fragments.Tieups_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 6/3/2017.
 */

public class TabsPage_adapter extends FragmentPagerAdapter {
	private List<Fragment> fragments;

	public TabsPage_adapter(FragmentManager supportFragmentManager, List<Fragment> fragments) {
		super(supportFragmentManager);
		this.fragments=fragments;
	}

	public Fragment getItem(int index)
	{

		return fragments.get(index);
		/*switch (index) {
			case 0:
				// Top Rated fragment activity
				return fragments.get(index);
			case 1:
				// Games fragment activity
				return new Tarrifs_Fragment();
			case 2:
				// Movies fragment activity
				return new Services_Fragment();
			case 3:
				// Movies fragment activity
				return new Tieups_Fragment();
			case 4:
				// Movies fragment activity
				return new Gallery_Fragment();*/


	}


	public int getCount() {
		return fragments.size();
	}
}

