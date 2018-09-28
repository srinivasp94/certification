package com.tiqs.rapmedix.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.Manifest;
import com.tiqs.rapmedix.R;

import java.util.ArrayList;

/**
 * Created by ADMIN on 6/2/2017.
 */

public class BestQuote_list_adapter extends BaseAdapter
{
	LayoutInflater inflater;
	Context context;
	ArrayList<String> image;
	int res,id ;
	int checkAccumulator;
	private int doctor_list_helper;
	ViewHolder_list vh;
	public BestQuote_list_adapter(Context context, int doctor_list_helper) {
		this.context = context;
		this.doctor_list_helper = doctor_list_helper;


		inflater = LayoutInflater.from(this.context);

	}



	@Override
	public int getCount() {

		return 6;
	}

	@Override
	public ListData getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {


		if (convertView==null) {
			convertView = inflater.inflate(R.layout.best_quote_selected_item, parent, false);

			//vh.textView.setTypeface(face);

			convertView.setTag(vh);

		}else
		{
			vh=(ViewHolder_list)convertView.getTag();

		}






		return convertView;
	}
	private void countCheck(boolean isChecked) {

		checkAccumulator += isChecked ? 1 : -1 ;
	}

	private boolean checkPermission()
	{
		int result = ContextCompat.checkSelfPermission((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION);
		if (result == PackageManager.PERMISSION_GRANTED) {

			return true;

		} else {

			return false;

		}
	}


	private void requestPermission() {

		if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION)) {



		} else {

			ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
		}
	}


	class ViewHolder_list
	{


	}

}
