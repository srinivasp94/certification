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


public class Appointments_list_adapter extends BaseAdapter
{
	LayoutInflater inflater;
	Context context;

	// String[] Title, Desc, Title1,mobile;

	AnimatorSet set;
	int res,id ;
	View view;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
			vh.cancel = (ImageView) convertView.findViewById(R.id.cancel_ant);

			convertView.setTag(vh);



		}else
		{
			vh=(ViewHolder_grid)convertView.getTag();
		}

		vh.can = appointment_data_modell.get(position).cancel;

        if (vh.can == 3) vh.cancel.setVisibility(View.GONE);

		vh.doctor_name.setText(appointment_data_modell.get(position).doctor_name);
		vh.specialization.setText(appointment_data_modell.get(position).specialization);
		vh.hos_name.setText(appointment_data_modell.get(position).hos_name);
		vh.hos_place.setText(appointment_data_modell.get(position).hos_place);
		vh.date.setText(appointment_data_modell.get(position).date);
		vh.time.setText(appointment_data_modell.get(position).time);

        vh.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Do you want to cancel this appointment?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                try {
                                    JSONObject jsonObject = new JSONObject();
                                    cancel(jsonObject,context.getResources().getString(R.string.server)+ Constants.cancellappointment_service);
                                    jsonObject.put("appointment_id", appointment_data_modell.get(position).appointment_id);
                                    //jsonObject.put("appointment_id", "124");
                                    Log.e("mob",appointment_data_modell.get(position).appointment_id+""+jsonObject);


                                    cancel(jsonObject,context.getResources().getString(R.string.server)+ Constants.cancellappointment_service);
                                    //call_custom_asynch(jsonObject,login_page_url);
                                    Log.e("ser","");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("err",""+e);
                                }
                                //Toast.makeText(context, "Appointment cancled successfully.", Toast.LENGTH_SHORT).show();

                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

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

						if (status.equals("0"))
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
		TextView doctor_name,specialization,hos_name,hos_place,date,time;
		ImageView cancel;
		int can = 0;
	}
}
