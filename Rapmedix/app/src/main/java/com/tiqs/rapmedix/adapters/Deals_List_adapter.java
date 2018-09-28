package com.tiqs.rapmedix.adapters;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.Appointment_data_model;
import com.tiqs.rapmedix.Appointments;
import com.tiqs.rapmedix.Diagnostic_data_model;
import com.tiqs.rapmedix.Forgot_pwd;
import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Deals_List_adapter extends BaseAdapter
{
	LayoutInflater inflater;
	Context context;

	// String[] Title, Desc, Title1,mobile;

	AnimatorSet set;
	int res,id ;
	View view;
	ArrayList<Appointment_data_model> appointment_data_modell;

	public Deals_List_adapter(Context context, int res,ArrayList<Appointment_data_model> appointment_data_models
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder_grid vh;

		if (convertView==null) {
			convertView = inflater.inflate(res, parent, false);
			vh=new ViewHolder_grid();
			vh.topic_grid = (ImageView) convertView.findViewById(R.id.topic_grid);
			vh.reviews = (TextView) convertView.findViewById(R.id.specialization);
			vh.description = (TextView) convertView.findViewById(R.id.hos_name);

			convertView.setTag(vh);



		}else
		{
			vh=(ViewHolder_grid)convertView.getTag();

		}

		//vh.topic_grid.setText(appointment_data_modell.get(position).doctor_name);
		//vh.specialization.setText(appointment_data_modell.get(position).specialization);
		vh.reviews.setText(appointment_data_modell.get(position).hos_name);
		vh.description.setText(appointment_data_modell.get(position).hos_place);

		/*Glide.with(context).
				load(deals_img.get(position)).
				error(R.drawable.doctor_white).
				placeholder(R.drawable.doctor_white).
				into(vh.imageView);*/

		return convertView;
	}

	public	void cancel (JSONObject jo, final String url) {

		CustomAsync ca = new CustomAsync(context, jo, url, new OnAsyncCompleteRequest() {

			public void asyncResponse(String result) {

				if (result == null || result.equals("")) {

					Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
							.setAction("RETRY", new View.OnClickListener() {
								@Override
								public void onClick(View view) {

									/*finish();
									startActivity(getIntent());*/
								}
							});
					snackBar.setActionTextColor(Color.RED);
					View sbView = snackBar.getView();
					TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
					textView.setTextColor(Color.YELLOW);
					snackBar.show();
				}

				else {

					try {

						JSONObject jo = new JSONObject(result);
						String status = jo.getString("status");
						Log.e("sta",""+status);

						if (status.equals("1"))
						{
							Toast.makeText(context, "Your appointment is Cancelled.", Toast.LENGTH_SHORT).show();
							((Activity)context).finish();
							context.startActivity(new Intent(context, Appointments.class)
									.putExtra("tab",1));
						}

						else {


						}


					}catch (Exception e) {

						e.printStackTrace();
					}


				}

			}
		});ca.execute();
	}


	class ViewHolder_grid
	{
		TextView reviews, description;
		ImageView topic_grid;

	}
}
