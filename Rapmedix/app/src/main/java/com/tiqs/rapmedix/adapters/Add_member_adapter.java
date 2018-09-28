package com.tiqs.rapmedix.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tiqs.rapmedix.already_member;
import com.tiqs.rapmedix.fragments.add_member_page;
import com.tiqs.rapmedix.fragments.member_list;
import com.tiqs.rapmedix.new_user;


 public class Add_member_adapter extends FragmentStatePagerAdapter {
   private final
     member_list lf1 = member_list .newInstance("member_list, intance");
     add_member_page lf2 = add_member_page .newInstance("add_member_page, intance");



   public Add_member_adapter(FragmentManager fm) {
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
