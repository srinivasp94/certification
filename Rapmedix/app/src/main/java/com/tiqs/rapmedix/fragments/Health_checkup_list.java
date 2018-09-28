package com.tiqs.rapmedix.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiqs.rapmedix.ExpandableHeightGridView;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.adapters.Member_list_adpater;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Health_checkup_list extends Fragment {

    String old_user_url,check_kit_id_url,vendor_login;
    ArrayList<String> image_title=new ArrayList<>();
    ArrayList<String> id=new ArrayList<>();
    ExpandableHeightGridView family_members_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.familymembers_list_main, container, false);

        family_members_list=(ExpandableHeightGridView)v.findViewById(R.id.family_members_list);
        family_members_list.setAdapter(new Member_list_adpater(getActivity(),R.layout.healthrecord_list_item));
/*
        family_members_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), Selected_candidate_page.class));
            }
        });
*/
        return v;
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    public static Health_checkup_list newInstance(String text) {

        Health_checkup_list f = new Health_checkup_list();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);


        return f;
    }




}

