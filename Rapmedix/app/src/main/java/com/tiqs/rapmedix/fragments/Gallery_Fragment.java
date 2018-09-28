package com.tiqs.rapmedix.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiqs.rapmedix.R;

/**
 * Created by ADMIN on 6/3/2017.
 */

public class Gallery_Fragment extends Fragment
{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

		return rootView;
	}
}
