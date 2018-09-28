package com.tiqs.rapmedix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tiqs.rapmedix.fragments.Health_checkup_list;
import com.tiqs.rapmedix.fragments.Pending_list;
import com.tiqs.rapmedix.fragments.Upload_new;


public class Health_checkup_adapter extends FragmentStatePagerAdapter {
    private final
    Health_checkup_list lf1 = Health_checkup_list .newInstance("Health_checkup_list, intance");
    Upload_new lf2 = Upload_new .newInstance("Upload_new, intance");


    public Health_checkup_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:  return lf1;
            default:return lf2;
        }
    }

    @Override
    public int getCount()
    {
        return 2;
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
/*

class MyPagerAdapter_Events_Activities extends FragmentPagerAdapter {

   public MyPagerAdapter_Events_Activities(FragmentManager fm) {
       super(fm);
       List<Fragment> fragments = fm.getFragments();
     */
/*  if (fragments != null) {
           FragmentTransaction ft = fm.beginTransaction();
           fragments.clear();
       }*//*


   }
   @Override
   public Fragment getItem(int pos) {

       switch(pos) {

           case 0: return Events_GridView.newInstance("Events_GridView, intance");
           case 1: return Activities_GridView.newInstance("Activities_GridView, Default");
           default: return null;
       }
   }

   @Override
   public int getCount() {
       return 2;
   }
}
*/
