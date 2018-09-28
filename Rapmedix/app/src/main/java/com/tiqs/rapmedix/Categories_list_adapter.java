package com.tiqs.rapmedix;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ADMIN on 6/1/2017.
 */

public class Categories_list_adapter extends BaseAdapter
{
	LayoutInflater inflater;
	Context context;
	ArrayList<String> image;
	int res,id ;
	public static int checkAccumulator;
	private int doctor_list_helper;
	 ViewHolder_list vh;

	 public setcheckedlistener setcheckedlistener;
	 public Categories_list_adapter(Context context, int doctor_list_helper) {
		this.context = context;
		this.doctor_list_helper = doctor_list_helper;


		inflater = LayoutInflater.from(this.context);

	}

    public interface  setcheckedlistener
	{
		public  void onChecked_lsitener(int count);
	}
	public  void setOnChecked_listenr(setcheckedlistener onChecked_listenr)
	{
		this.setcheckedlistener=onChecked_listenr;
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
			convertView = inflater.inflate(R.layout.categorie_select_item1, parent, false);
			vh=new ViewHolder_list();
			vh.add_cart=(CheckBox)convertView.findViewById(R.id.add_cart);
			//vh.textView.setTypeface(face);

			convertView.setTag(vh);

		}else
		{
			vh=(ViewHolder_list)convertView.getTag();

		}
		vh.add_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					buttonView.setTextColor(Color.WHITE);

				}
				else
				{
					buttonView.setTextColor(Color.BLACK);
				}
				countCheck(isChecked);

				Log.e("MAIN", checkAccumulator + "");

				setcheckedlistener.onChecked_lsitener(checkAccumulator);
			}
		});


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

		CheckBox add_cart;
	}

}


