/*
package com.tiqs.rapmedix.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tiqs.rapmedix.BuildConfig;
import com.tiqs.rapmedix.DoctorDescription;
import com.tiqs.rapmedix.ExpandableHeightGridView1;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.Selected_candidate_page;
import com.tiqs.rapmedix.slot_selection;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

*/
/**
 * Created by Manohar on 12/2/2017.
 *//*


*/
/*public class DoctorDescriptionAdapter {
}*//*



public class DoctorDescriptionAdapter extends BaseAdapter {
    ArrayList<JSONObject> Object;
    Context con;
    LayoutInflater inflater;

    public DoctorDescriptionAdapter(Context con, ArrayList<JSONObject> Object) {
        this.con = con;
        this.Object = Object;
        this.inflater = (LayoutInflater) con.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return this.Object.size();
    }

    public Object getItem(int position) {
        return Integer.valueOf(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder_doctor vh;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.doctor_desc_row, parent, false);
            vh = new ViewHolder_doctor();
            vh.doctorName = (TextView) convertView.findViewById(R.id.doctor_row_name);
            vh.doctorAddress = (TextView) convertView.findViewById(R.id.doctor_row_desc);
            vh.doctorMobile = (TextView) convertView.findViewById(R.id.mobile);
            vh.doctorEmail = (TextView) convertView.findViewById(R.id.email);
            vh.doctorTime = (TextView) convertView.findViewById(R.id.time);
            vh.doctorPrice = (TextView) convertView.findViewById(R.id.doctor_row_price);
            vh.doctorBook = (Button) convertView.findViewById(R.id.doctor_row_book);
            vh.doctorDirection = (Button) convertView.findViewById(R.id.doctor_row_direction);
//            vh.doctorName.setTypeface(DoctorDescription.this.monster);
//            vh.doctorAddress.setTypeface(DoctorDescription.this.monster);
//            vh.doctorPrice.setTypeface(DoctorDescription.this.monster);
            vh.doctorBook.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    datess.clear();

                    //final int position=(Integer)v.getTag();
                    Intent slot = new Intent(DoctorDescription.this, slot_selection.class);
                    slot.putExtra("position", position);
                    startActivity(slot);
                    Log.e("pos", "" + position);
                    Dialog dialog = new Dialog(DoctorDescription.this);
                    dialog.requestWindowFeature(1);
                    dialog.setContentView(R.layout.slots_popup);
                    final TabLayout date_tab = (TabLayout) dialog.findViewById(R.id.dates_tab);
                    TextView doctor_row_price = (TextView) dialog.findViewById(R.id.doctor_row_price);
                    TextView doctor_name = (TextView) dialog.findViewById(R.id.doctor_name);
                    TextView hosp_name = (TextView) dialog.findViewById(R.id.hspital_name);
                    final TextView datte = (TextView) dialog.findViewById(R.id.date);
                    timeW = (TextView) dialog.findViewById(R.id.time);
                    timeW.setText("00:00");
                    Button doctor_row_book = (Button) dialog.findViewById(R.id.doctor_row_book);
                    final ExpandableHeightGridView1 expandableGridView1 = (ExpandableHeightGridView1) dialog.findViewById(R.id.slots_list);
                    Calendar datee = Calendar.getInstance();
                    DoctorDescription.this.get_dates();
                    Drawable img2 = DoctorDescription.this.getResources().getDrawable(R.drawable.slot_date_bg_selector2);
                    String[] converted = null;
                    final ArrayList<String> modifiedDates = new ArrayList<String>();
                    String converteDates = "";
                    modifiedDates.clear();
                    for (int i = 0; i < datess.size(); i++) {
                        converted = datess.get(i).split("-");
                        converteDates = converted[2] + "-" + converted[1] + "-" + converted[0];
                        modifiedDates.add(converteDates);
                        Log.e("TheDataofString", "938   " + converteDates);
                        View relativeLayout = (LinearLayout) LayoutInflater.from(DoctorDescription.this).inflate(R.layout.slot_date_item, date_tab, false);
                        TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.month);
                        TextView date = (TextView) relativeLayout.findViewById(R.id.date);
                        tabTextView.setBackgroundDrawable(img1);
                        date.setBackgroundDrawable(img2);


                        try {
                            doctor_row_price.setText("\u20b9 " + (Object.get(position)).getString("fee"));
                            //Date dateee = new SimpleDateFormat("dd-MM-yyyy").parse(datess.get(i));
                            Date dateee = new SimpleDateFormat("dd-MM-yyyy").parse(converteDates);
                            Date d = new Date();


                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM");
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE");
                            String dayOfTheWeek = sdf.format(d);
                            String goal = sdf.format(dateee);
                            String day = simpleDateFormat.format(dateee);
                            tabTextView.setText(goal);
                            date.setText(day);
                            String doc_name = doctorName.toString();
                            doctor_name.setText(doc_name);
                            // hosp_name.setText();
                            //Hospital_name= getIntent().getStringExtra("hospital_name");

                            String hospName = hospitalName.toString();

                            hosp_name.setText(Object.get(position).getString("hospital_name"));


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        date_tab.addTab(date_tab.newTab().setCustomView(relativeLayout));
                    }
                    callServer((modifiedDates.get(0)).toString(), 0, Object, expandableGridView1, position);

                    date_tab.addOnTabSelectedListener(new OnTabSelectedListener() {
                        public void onTabSelected(Tab tab) {
                            Log.e("ServertoSend0", tab.getPosition() + "123" + (modifiedDates.get(tab.getPosition())));
                            try {
                                Log.e("ServertoSend1", "123");
                                datte.setText(modifiedDates.get(tab.getPosition()));
                                callServer((modifiedDates.get(tab.getPosition())).toString(), tab.getPosition(), Object, expandableGridView1, position);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("ServertoSend2", "123" + e.toString());
                            }


                            if (date_tab.getSelectedTabPosition() == 3) {
                                try {

                                    datte.setText(datess.get(tab.getPosition()));
                                    callServer((datess.get(date_tab.getSelectedTabPosition())).toString(), date_tab.getSelectedTabPosition(), DoctorDescriptionAdapter.this.Object, expandableGridView1, position);
                                    Log.e("ServertoSend33", "123    ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e("ServertoSend3", "123    " + e.toString());
                                }

                            }

                        }

                        public void onTabUnselected(Tab tab) {
                        }

                        public void onTabReselected(TabLayout.Tab tab) {
                        }
                    });
                    datte.setText(modifiedDates.get(date_tab.getSelectedTabPosition()));


                    doctor_row_book.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            try {
                                if (slotTime.equals(BuildConfig.VERSION_NAME)) {
                                    Toast.makeText(DoctorDescription.this, "Select slot", Toast.LENGTH_LONG).show();
                                } else {
                                    DoctorDescription.this.startActivity(new Intent(DoctorDescription.this, Selected_candidate_page.class)
                                            .putExtra("doctor_id", DoctorDescription.this.doctorId)
                                            .putExtra("doctorworkingdetails_id", ((JSONObject)
                                                    DoctorDescriptionAdapter.this.Object.get(position)).getString("id"))
                                            .putExtra("hospital_id", ((JSONObject)
                                                    DoctorDescriptionAdapter.this.Object.get(position)).getString("hospital_id"))
                                            .putExtra("date", modifiedDates.get(date_tab.getSelectedTabPosition()))
                                            .putExtra("hos_pos", position)
                                            .putExtra("time", slotTime).putExtra(FirebaseAnalytics.Param.PRICE,
                                                    ((JSONObject) DoctorDescriptionAdapter.this.Object.get(position)).getString("fee")));
                                }
                            } catch (Exception e) {
                            }
                        }
                    });

                   */
/* Window window = dialog.getWindow();
                    window.setLayout(-1, -2);
                    window.setGravity(17);
                    dialog.show();
*//*

                }
            });
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder_doctor) convertView.getTag();
        }
        try {
            vh.doctorName.setText(((JSONObject) this.Object.get(position)).getString("hospital_name"));
            vh.doctorAddress.setText(((JSONObject) this.Object.get(position)).getString("address"));
            vh.doctorPrice.setText("\u20b9 " + ((JSONObject) this.Object.get(position)).getString("fee"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //vh.doctorBook.setTag(position);

        return convertView;
    }

    class ViewHolder_doctor {
        TextView doctorAddress;
        Button doctorBook;
        Button doctorDirection;
        TextView doctorEmail;
        TextView doctorMobile;
        TextView doctorName;
        TextView doctorPrice;
        TextView doctorTime;

        ViewHolder_doctor() {
        }
    }
}
*/
