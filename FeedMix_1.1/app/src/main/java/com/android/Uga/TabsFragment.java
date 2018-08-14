package com.android.Uga;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.support.v4.app.FragmentActivity;
/**
 * Created by New android on 14-07-2016.
 */
public class TabsFragment extends FragmentActivity {

    private View view;
    private int mCurrentTab;

    private static final String TAB_1_TAG = "tab_1";
    private static final String TAB_2_TAG = "tab_2";
    private static final String TAB_3_TAG = "tab_3";


    private FragmentTabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);


        setupTabs();

    }


    private void setupTabs() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_1_TAG).setIndicator("Ingredient Library"), tab1Fragement.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_2_TAG).setIndicator("Create My Diet"), tab2Fragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_3_TAG).setIndicator("Nutrient Class"), tab3Fragment.class, null);

    }

    @Override
    public void onBackPressed() {
        boolean isPopFragment = false;
        String currentTabTag = mTabHost.getCurrentTabTag();
        if (currentTabTag.equals(TAB_1_TAG)) {
            isPopFragment = ((BaseContainerFragment) getSupportFragmentManager().findFragmentByTag(TAB_1_TAG)).popFragment();
        } else if (currentTabTag.equals(TAB_2_TAG)) {
            isPopFragment = ((BaseContainerFragment) getSupportFragmentManager().findFragmentByTag(TAB_2_TAG)).popFragment();
        } else if (currentTabTag.equals(TAB_3_TAG)) {
            isPopFragment = ((BaseContainerFragment) getSupportFragmentManager().findFragmentByTag(TAB_3_TAG)).popFragment();
        }
        if (!isPopFragment) {
            finish();
        }

    }
}