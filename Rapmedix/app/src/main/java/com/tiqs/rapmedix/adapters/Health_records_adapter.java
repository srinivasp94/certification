package com.tiqs.rapmedix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tiqs.rapmedix.fragments.Health_checkup_list;
import com.tiqs.rapmedix.fragments.Health_records_upload;
import com.tiqs.rapmedix.fragments.Upload_new;
import com.tiqs.rapmedix.fragments.HealthRecord_upload_new;


public class Health_records_adapter extends FragmentStatePagerAdapter {
	private final
	Health_records_upload lf1 = Health_records_upload .newInstance("Health_records_upload, intance");
//	HealthRecord_upload_new lf2 = HealthRecord_upload_new .newInstance("HealthRecord_upload_new, intance");


	public Health_records_adapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		//	case 0:  return lf1;
			default:return lf1;
		}
	}

	@Override
	public int getCount()
	{
		return 1;
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
    /*  if (position == 0)
          lf1.updateUI(); //Refresh what you need on this fragment
      else if (position == 1)
          lf2.updateUI();*/
	}
}
