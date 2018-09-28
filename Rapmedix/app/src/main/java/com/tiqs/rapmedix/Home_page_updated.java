package com.tiqs.rapmedix;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiqs.rapmedix.adapters.Home_adapter;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

/**
 * Created by Sohail on 4/30/2017.
 */
public class Home_page_updated  extends AppCompatActivity
{
    ExpandableHeightGridView list1;
    TwoWayView list2;
    TabLayout tabLayout;
    TextView tab11;
        ImageView    icon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_updated);


        ArrayList<String> tabs = new ArrayList<>();
        tabs.clear();
        tabs.add("Doctors");
        tabs.add("Diagnostics");
        tabs.add("Best Quotes");
        tabs.add("Home Visits");
        tabLayout = (TabLayout)findViewById(R.id.cat_tabs);

        int  [] drawables={R.drawable.tab_icon_chang,R.drawable.tab_icon_chang_diagnostics,R.drawable.tab_icon_chang_pharmacy,R.drawable.tab_icon_chang_home_visit};
        for (int i = 0; i < tabs.size(); i++)
        {
            Drawable img1 = getResources().getDrawable(drawables[i]);

            LinearLayout relativeLayout = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.custom_tab, tabLayout, false);

            tab11 = (TextView) relativeLayout.findViewById(R.id.tab_title);
            icon=(ImageView)relativeLayout.findViewById(R.id.tab_icon);


            icon.setImageDrawable(img1);
            tab11.setText(tabs.get(i));

            tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

        }

        list1=(ExpandableHeightGridView)findViewById(R.id.categories);
        list2=(TwoWayView)findViewById(R.id.deals_list);
       // list1.setAdapter(new Home_adapter(this,R.layout.home_item));
        //list2.setAdapter(new Home_adapter(this,R.layout.deals_item));
        list2.setItemMargin(10);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                startActivity(new Intent(Home_page_updated.this,Add_family_member.class));
            }
        });
    }
}
