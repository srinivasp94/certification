package com.tiqs.rapmedix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tiqs.rapmedix.fragments.Pending_list;
import com.tiqs.rapmedix.fragments.Pending_list1;
import com.tiqs.rapmedix.fragments.Pending_list2;
import com.tiqs.rapmedix.fragments.Pending_list3;
import com.tiqs.rapmedix.fragments.add_member_page;
import com.tiqs.rapmedix.fragments.member_list;


public class Appointments_adapter extends FragmentStatePagerAdapter {
  private final
     Pending_list lf1 = Pending_list .newInstance("Pending_list, intance");
    Pending_list1 lf2 = Pending_list1 .newInstance("Pending_list1, intance");
    Pending_list2 lf3 = Pending_list2 .newInstance("Pending_list2, intance");
    Pending_list3 lf4 = Pending_list3.newInstance("Pending_list3, intance");


  public Appointments_adapter(FragmentManager fm) {
      super(fm);
  }

  @Override
  public Fragment getItem(int position) {
      switch (position) {
          case 0:  return lf1;
          case 1:  return lf2;
          case 2: return lf3;
          default:return lf4;
      }
  }

  @Override
  public int getCount()
  {
      return 4;
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
