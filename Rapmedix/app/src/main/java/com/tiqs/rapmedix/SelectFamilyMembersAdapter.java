package com.tiqs.rapmedix;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SelectFamilyMembersAdapter extends BaseAdapter {

		ArrayList<Famili_member_dat_model> data;
	Context con;
	String UserId = "";
	View view;

		LayoutInflater inflater;
		int selectedPosition = 0;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		public SelectFamilyMembersAdapter(Context con,ArrayList<Famili_member_dat_model> data) {

			this.data = data;
			this.con = con;
			inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}


		@Override
		public int getCount()
		{
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			DataBase_Helper db = new DataBase_Helper(con.getApplicationContext());
			UserId = db.getUserId("1");


			if (convertView == null) {
				convertView = inflater.inflate(R.layout.family_members_list_item, parent, false);
				vh = new ViewHolder();

				vh.name = (TextView) convertView.findViewById(R.id.name);
				vh.age = (TextView) convertView.findViewById(R.id.age);
				vh.relation = (TextView) convertView.findViewById(R.id.relation);
				vh.mobile = (TextView) convertView.findViewById(R.id.mobile);
				vh.delete_member = (RelativeLayout) convertView.findViewById(R.id.layout_member);
				vh.mobilee_root = (LinearLayout) convertView.findViewById(R.id.mobilee_root);




				//int padding = Namee.size();

//				vh.name.setTypeface(monster);
//				vh.relation.setTypeface(monster);
//				vh.mobile.setTypeface(monster);

				convertView.setTag(vh);

			} else {
				vh = (ViewHolder) convertView.getTag();

			}


			vh.name.setText(data.get(position).name);
			final String datte=data.get(position).dob;
			Log.e("date",""+datte);
			SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");

			try {

				//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date date = input.parse(datte);
				Log.e("date",""+date);
				String UpdatedDate = output.format(date);
				Log.e("string",""+UpdatedDate);

				vh.age.setText(UpdatedDate);
			} catch (ParseException e) {
				e.printStackTrace();
				Log.e("Exception",""+e.toString());
			}
			//vh.age.setText(data.get(position).dob);
			vh.relation.setText(data.get(position).relation);
			if (data.get(position).Mobile.equals("0"))
			{
				vh.mobilee_root.setVisibility(View.GONE);

			}else {
				vh.mobilee_root.setVisibility(View.VISIBLE);
				vh.mobile.setText(data.get(position).Mobile);
			}
			vh.delete_member.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
					alertDialogBuilder.setTitle("Do you want to cancel this family member?");
					alertDialogBuilder.setPositiveButton("yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									try {
										JSONObject jsonObject = new JSONObject();
										//delete_item(jsonObject,con.getResources().getString(R.string.server)+ Constants.deletefamilymember_service);
										jsonObject.put("id", data.get(position).delete);
										jsonObject.put("user_id", UserId);
										//jsonObject.put("appointment_id", "124");


										delete_item(jsonObject,con.getResources().getString(R.string.server)+ Constants.deletefamilymember_service);
										//call_custom_asynch(jsonObject,login_page_url);

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
	public	void delete_item (JSONObject jo, final String url) {

		CustomAsync ca = new CustomAsync(con, jo, url, new OnAsyncCompleteRequest() {

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
							Toast.makeText(con, "Family member successfully deleted.", Toast.LENGTH_SHORT).show();
							((Activity)con).finish();

							con.startActivity(new Intent(((Activity) con).getApplication(), Add_family_member.class));
									//.putExtra("tabs",1));
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


	public class ViewHolder {

		TextView name, relation, mobile,age;
		RelativeLayout delete_member;
		RadioButton Select;
		LinearLayout mobilee_root;
	}

	}

