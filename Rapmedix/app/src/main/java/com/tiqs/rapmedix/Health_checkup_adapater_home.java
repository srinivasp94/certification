package com.tiqs.rapmedix;

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

import java.lang.String;

import java.util.ArrayList;
   public class Health_checkup_adapater_home extends BaseAdapter{
	LayoutInflater inflater;
	Context context;
	String s,iddd;
	// String[] Title, Desc, Title1,mobile;
	ArrayList<String>  Desc, Title1,mobile,id1;
	ArrayList<String> image;
	Typeface face;
	TextView tvTitle;
	ImageView imgview;
	AnimatorSet set;
	int res,id ;
	ArrayList<Diagnostic_data_model> diagnostic_data_models;

	public Health_checkup_adapater_home(Context context, int res, ArrayList<Diagnostic_data_model> diagnostic_data_models
	) {
		this.context = context;
		this.diagnostic_data_models = diagnostic_data_models;
        /*this.image = Image;
        this.Desc = title;*/
		this.res=res;
		this.id=id;
        /*this.Title1 = Title1;
        this.mobile = mobile;
        this.id1 = id;
        this.context = context;*/
		inflater = LayoutInflater.from(this.context);

	}

	@Override
	public int getCount() {

		return diagnostic_data_models.size();
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
		Health_checkup_adapater_home.ViewHolder_grid vh;

		if (convertView==null) {
			convertView = inflater.inflate(res, parent, false);
			vh=new Health_checkup_adapater_home.ViewHolder_grid();
			vh.textView = (TextView) convertView.findViewById(R.id.title);
			vh.description = (TextView) convertView.findViewById(R.id.description);
			vh.imagee = (ImageView) convertView.findViewById(R.id.imagee);

			convertView.setTag(vh);

		}else
		{
			vh=(Health_checkup_adapater_home.ViewHolder_grid)convertView.getTag();

		}

		Glide
				.with(context)
				.load(diagnostic_data_models.get(position).checkup_image)
				.centerCrop()
				.placeholder(R.drawable.shape)
				.crossFade()
				.into(vh.imagee);

		vh.textView.setText(diagnostic_data_models.get(position).name);
		vh.description.setText(diagnostic_data_models.get(position).description);


		return convertView;
	}



	class ViewHolder_grid
	{
		TextView textView,description;
		ImageView imagee;
	}


}