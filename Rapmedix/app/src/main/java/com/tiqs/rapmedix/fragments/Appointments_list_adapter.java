package com.tiqs.rapmedix.fragments;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.Appointment_data_model;
import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.R;

import java.util.ArrayList;


public class Appointments_list_adapter extends BaseAdapter
{
	LayoutInflater inflater;
	Context context;

	// String[] Title, Desc, Title1,mobile;

	AnimatorSet set;
	int res,id ;
	ArrayList<Appointment_data_model> appointment_data_modell;

	public Appointments_list_adapter(Context context, int res,ArrayList<Appointment_data_model> appointment_data_models
	) {
		this.context = context;
		this.appointment_data_modell = appointment_data_models;

		this.res=res;

		inflater = LayoutInflater.from(this.context);

	}

	@Override
	public int getCount() {

		return appointment_data_modell.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder_grid vh;

		if (convertView==null) {
			convertView = inflater.inflate(res, parent, false);
			vh=new ViewHolder_grid();
			vh.doctor_name = (TextView) convertView.findViewById(R.id.doctor_name);
			vh.specialization = (TextView) convertView.findViewById(R.id.specialization);
			vh.hos_name = (TextView) convertView.findViewById(R.id.hos_name);
			vh.hos_place = (TextView) convertView.findViewById(R.id.hos_place);
			vh.date = (TextView) convertView.findViewById(R.id.date);
			vh.time = (TextView) convertView.findViewById(R.id.time);

			convertView.setTag(vh);

		}else
		{
			vh=(ViewHolder_grid)convertView.getTag();

		}

		vh.doctor_name.setText(appointment_data_modell.get(position).doctor_name);
		//vh.specialization.setText(appointment_data_modell.get(position).specialization);
		vh.hos_name.setText(appointment_data_modell.get(position).hos_name);
		vh.hos_place.setText(appointment_data_modell.get(position).hos_place);
		vh.date.setText(appointment_data_modell.get(position).date);
		vh.time.setText(appointment_data_modell.get(position).time);

		return convertView;
	}



	class ViewHolder_grid
	{
		TextView doctor_name,specialization,hos_name,hos_place,date,time;

	}
}
