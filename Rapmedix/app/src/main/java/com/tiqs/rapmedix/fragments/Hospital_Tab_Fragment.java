package com.tiqs.rapmedix.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiqs.rapmedix.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 6/5/2017.
 */

public class Hospital_Tab_Fragment extends Fragment
{
		public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
		public static final Hospital_Tab_Fragment newInstance(String message)
		{
			Hospital_Tab_Fragment f = new Hospital_Tab_Fragment();
			Bundle bdl = new Bundle(1);
			bdl.putString(EXTRA_MESSAGE, message);
			f.setArguments(bdl);
			return f;
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {
			String message = getArguments().getString(EXTRA_MESSAGE);
			View v = inflater.inflate(R.layout.myfragment, container, false);
			/*TextView messageTextView = (TextView)v.findViewById(R.id.textView);
			messageTextView.setText(message);*/
			return v;
		}
	/*private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();
		fList.add(Hospital_Tab_Fragment.newInstance("Fragment 1"));
		fList.add(Hospital_Tab_Fragment.newInstance("Fragment 2"));
		fList.add(Hospital_Tab_Fragment.newInstance("Fragment 3"));
		return fList;

	}*/
}
