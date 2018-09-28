package com.tiqs.rapmedix.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiqs.rapmedix.R;

/**
 * Created by ADMIN on 1/31/2017.
 */

public class ArrayAdapter_EditPage extends ArrayAdapter{

	Context context;
	int layoutResourceId;
	String [] data;

	public ArrayAdapter_EditPage(Context context, int layoutResourceId, String [] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		WeatherHolder holder = null;

		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new WeatherHolder();
			//holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
			holder.txtTitle = (TextView)row.findViewById(R.id.slist_item);

			row.setTag(holder);
		}
		else
		{
			holder = (WeatherHolder)row.getTag();
		}

		if (position == 0) holder.txtTitle.setTextColor(Color.parseColor("#262626"));

		else holder.txtTitle.setTextColor(Color.parseColor("#262626"));

		holder.txtTitle.setText(data[position]);


		return row;
	}

	static class WeatherHolder
	{
		ImageView imgIcon;
		TextView txtTitle;
	}
}