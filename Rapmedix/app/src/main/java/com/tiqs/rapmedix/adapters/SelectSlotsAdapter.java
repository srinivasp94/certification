package com.tiqs.rapmedix.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiqs.rapmedix.R;

import java.util.ArrayList;

public class SelectSlotsAdapter extends BaseAdapter {


		Context con;
	ArrayList<String> DatesToDisplay;
		ArrayList<String> BookedSlots;
		LayoutInflater inflater;

		public SelectSlotsAdapter (Context con) {

			this.con = con;
			this.DatesToDisplay = DatesToDisplay;
			this.BookedSlots = BookedSlots;
			inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}


		@Override
		public int getCount() {
			//return DatesToDisplay.size();
			return 12;
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
			final ViewHolder vh;


			if (convertView == null) {
				convertView = inflater.inflate(R.layout.selectslots_row, parent, false);
				vh = new ViewHolder();

				vh.listDates = (TextView) convertView.findViewById(R.id.diffTimes);

				convertView.setTag(vh);

			} else {
				vh = (ViewHolder) convertView.getTag();

			}


			//vh.listDates.setText(DatesToDisplay.get(position));

			/*if (BookedSlots.contains (DatesToDisplay.get(position))) {

				vh.listDates.setPaintFlags(vh.listDates.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
			}

			else {

				vh.listDates.setPaintFlags(vh.listDates.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);

				*//*convertView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						slotTime = vh.listDates.getText().toString().trim();

						Log.e("SlotTimeee", "123     "+ slotTime);

						Intent intent = new Intent(SelectSlots.this, SelectFamilyMemebers.class);
						intent.putExtra("slotDay", Today.getText().toString().substring(0,3));
						intent.putExtra("slotDate", TodayDate.getText().toString());
						intent.putExtra("slotTime", slotTime);
						intent.putExtra("PriceFee", Price.getText().toString().trim());
						intent.putExtra("DoctorId", DoctorId);
						intent.putExtra("WorkingId", WorkingId);
						intent.putExtra("DoctorName", DoctorName);
						startActivity(intent);
					}
				});*//*
			}
*/
			return convertView;
		}
	public class ViewHolder {

		TextView listDates;

	}

	}

