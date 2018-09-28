package com.tiqs.rapmedix;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.User;

import java.util.ArrayList;


public class Login_page extends AppCompatActivity
{
    TabLayout tabLayout;
    ArrayList<String> tabs;

    ImageView mob_icon;
    EditText mobile_number,name,email1;
    LinearLayout mobile;
    Button submit;
    User user_data;
    View parentLayout;
    String valid_email,emailPattern ;
   String login_page_url;
    login_pager_adapter manage_home_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        tabLayout = (TabLayout) findViewById(R.id.login_cat);

        login_page_url = Constants.login;

        user_data = new User();
      /* email1.requestFocusFromTouch();
     *//*  name.setFocusableInTouchMode(false);
       mobile_number.setFocusableInTouchMode(false);*/

        mobile = (LinearLayout) findViewById(R.id.mobile);
        submit = (Button) findViewById(R.id.submit);


        tabs = new ArrayList<>();
        tabs.clear();

        tabs.add("New User");
        tabs.add("Already have the kit ID? ");
        manage_home_adapter=new login_pager_adapter((getSupportFragmentManager()));
        manage_home_adapter.notifyDataSetChanged();
        final ViewPager viewPager = (ViewPager)findViewById(R.id.login_pager);
        //viewPager.setAdapter(new MyPagerAdapter_Events_Activities((getFragmentManager())));
        viewPager.setAdapter(manage_home_adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        for (int i = 0; i < tabs.size(); i++) {

            LinearLayout relativeLayout = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.custom_tab1, tabLayout, false);

            TextView tab11 = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tab11.setText(tabs.get(i));

            tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));
        }



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                manage_home_adapter.notifyDataSetChanged();

                if (tab.getPosition() == 0)
                {

                }
                else
                {

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";







    }

}
