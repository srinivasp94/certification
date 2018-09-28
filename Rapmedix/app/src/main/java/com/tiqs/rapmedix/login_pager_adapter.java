package com.tiqs.rapmedix;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


class login_pager_adapter extends FragmentStatePagerAdapter {
   private final
   new_user lf1 = new_user .newInstance("Events_GridView, intance");
   already_member lf2 = already_member .newInstance("Events_GridView, intance");


   public login_pager_adapter(FragmentManager fm) {
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
