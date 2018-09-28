package com.tiqs.rapmedix.adapters;

import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiqs.rapmedix.HealthRecords_data_model;
import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.R;

import java.util.ArrayList;

/**
 * Created by ADMIN on 5/19/2017.
 */

public class Health_records_list_adapter extends BaseAdapter {
	LayoutInflater inflater;
	Context context;

	// String[] Title, Desc, Title1,mobile;

	AnimatorSet set;
	int res,id ;
	ArrayList<HealthRecords_data_model> healthrecords_data_modell;

	public Health_records_list_adapter(Context context, int res,ArrayList<HealthRecords_data_model> healthrecords_data_models
	) {
		this.context = context;
		this.healthrecords_data_modell = healthrecords_data_models;

		this.res=res;
		inflater = LayoutInflater.from(this.context);

	}

	@Override
	public int getCount() {

		return healthrecords_data_modell.size();
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
			vh.record_title = (TextView) convertView.findViewById(R.id.record_title);
			vh.doc_name = (TextView) convertView.findViewById(R.id.doctor_name);
			//vh.specialization = (TextView) convertView.findViewById(R.id.specialization);
			vh.date = (TextView) convertView.findViewById(R.id.date_hr);
			vh.time = (TextView) convertView.findViewById(R.id.time_hr);

			convertView.setTag(vh);

		}else
		{
			vh=(ViewHolder_grid)convertView.getTag();

		}

		vh.record_title.setText(healthrecords_data_modell.get(position).title);
		//vh.user_name.setText(healthrecords_data_modell.get(position).user_name);
		vh.doc_name.setText(healthrecords_data_modell.get(position).doctor_name);
		//vh.specialization.setText(healthrecords_data_modell.get(position).specialization);
		vh.date.setText(healthrecords_data_modell.get(position).date);
		vh.time.setText(healthrecords_data_modell.get(position).time);

		return convertView;
	}



	class ViewHolder_grid
	{
		TextView user_name,record_title,doc_name,specialization,date,time;

	}
}
