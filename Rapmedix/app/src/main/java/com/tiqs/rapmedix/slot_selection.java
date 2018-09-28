package com.tiqs.rapmedix;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * Created by Manohar on 12/2/2017.
 */

public class slot_selection extends AppCompatActivity {


    TextView timeW, text_exp;
    ArrayList<String> datess = new ArrayList();
    int position;
    JSONObject Object;
    boolean isNet;

    LinearLayout layout;

    String doctorId = pl.droidsonroids.gif.BuildConfig.VERSION_NAME;
    String doctorName = pl.droidsonroids.gif.BuildConfig.VERSION_NAME;
    String hospitalName = pl.droidsonroids.gif.BuildConfig.VERSION_NAME;

    ArrayList<String> DatesOfSlots = new ArrayList();
    ArrayList<String> BookedSlots = new ArrayList();

        String slotTime = pl.droidsonroids.gif.BuildConfig.VERSION_NAME;
    SelectSlotsAdapter selectSlotsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots_popup);


        position = this.getIntent().getExtras().getInt("position");
        try {
            Object = new JSONObject(getIntent().getStringExtra("ObjectData"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Object.ToString", Object.toString());
        isNet = new ConnectionDetector(this).isConnectingToInternet();
        layout = (LinearLayout) findViewById(R.id.snaklayout);
        final TabLayout date_tab = (TabLayout) findViewById(R.id.dates_tab);
        doctorId = getIntent().getExtras().getString("doctorId");


        TextView doctor_row_price = (TextView) findViewById(R.id.doctor_row_price);
        TextView doctor_name = (TextView) findViewById(R.id.doctor_name);
        TextView hosp_name = (TextView) findViewById(R.id.hspital_name);
        final TextView datte = (TextView) findViewById(R.id.date);
        timeW = (TextView) findViewById(R.id.time);
        timeW.setText("00:00");
        Button doctor_row_book = (Button) findViewById(R.id.doctor_row_book);
        final GridView expandableGridView1 = (GridView) findViewById(R.id.slots_list);

        Calendar datee = Calendar.getInstance();
        this.get_dates();
        Drawable img1 = slot_selection.this.getResources().getDrawable(R.drawable.slot_date_bg_selector);
        Drawable img2 = slot_selection.this.getResources().getDrawable(R.drawable.slot_date_bg_selector2);
        String[] converted = null;
        final ArrayList<String> modifiedDates = new ArrayList<String>();
        String converteDates = "";
        modifiedDates.clear();
        for (int i = 0; i < datess.size(); i++) {
            converted = datess.get(i).split("-");
            converteDates = converted[2] + "-" + converted[1] + "-" + converted[0];
            modifiedDates.add(converteDates);
            Log.e("TheDataofString", "938   " + converteDates);
            View relativeLayout = (LinearLayout) LayoutInflater.from(slot_selection.this).inflate(R.layout.slot_date_item, date_tab, false);
            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.month);
            TextView date = (TextView) relativeLayout.findViewById(R.id.date);
            tabTextView.setBackgroundDrawable(img1);
            date.setBackgroundDrawable(img2);


            try {
                doctor_row_price.setText("\u20b9 " + Object.getString("fee"));
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

                hosp_name.setText(Object.getString("hospital_name"));


            } catch (Exception e) {
                e.printStackTrace();
            }
            date_tab.addTab(date_tab.newTab().setCustomView(relativeLayout));
        }
        callServer((modifiedDates.get(0)).toString(), 0, Object, expandableGridView1, position);

        date_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("ServertoSend0", tab.getPosition() + "123" + (modifiedDates.get(tab.getPosition())));
                try {
                    Log.e("ServertoSend1", "123");
                    datte.setText(modifiedDates.get(tab.getPosition()));
                    callServer((modifiedDates.get(tab.getPosition())).toString(), tab.getPosition(), Object, expandableGridView1, position);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ServertoSend2", "123" + e.toString());
                }
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        datte.setText(modifiedDates.get(date_tab.getSelectedTabPosition()));


        doctor_row_book.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (slotTime.equals(BuildConfig.VERSION_NAME)) {
                        Toast.makeText(slot_selection.this, "Select slot", Toast.LENGTH_LONG).show();
                    } else {
                        slot_selection.this.startActivity(new Intent(slot_selection.this, Selected_candidate_page.class)
                                .putExtra("doctor_id", slot_selection.this.doctorId)
                                .putExtra("doctorworkingdetails_id", Object.getString("id"))
                                .putExtra("hospital_id", Object.getString("hospital_id"))
                                .putExtra("date", modifiedDates.get(date_tab.getSelectedTabPosition()))
                                .putExtra("hos_pos", position)
                                .putExtra("time", slotTime)
                                .putExtra(FirebaseAnalytics.Param.PRICE,
                                        Object.getString("fee")));
                    }
                } catch (Exception e) {
                }
            }
        });


    }

    public void get_dates() {
        for (int i = 0; i < 7; i++) {
            //this.datess.add(getCalculatedDate("dd-MM-yyyy", i));
            this.datess.add(getCalculatedDate("yyyy-MM-dd", i));
            Collections.sort(this.datess);
        }
    }


    public static String getCalculatedDate(String dateFormat, int days) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(dateFormat);
        cal.add(java.util.Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }


    public void callServer(String date, int position, JSONObject Object, GridView slots_grid, int positionn) {
        if (this.isNet) {
            try {
                Log.e("dadadada", "123    " + Object.toString());
                JSONObject jo = new JSONObject();
                jo.put("date", date);
                jo.put("doctor_id", this.doctorId);
                jo.put("doctorworkingdetails_id", Object.getString("id"));
                Log.e("ServertoSend", "123    " + jo.toString());
                showSlots(jo, getString(R.string.webData) + Constants.slotBooking, date, slots_grid);
                return;
            } catch (Exception e) {
                Log.e("ServertoSend", "123    " + e.toString());
                e.printStackTrace();
                return;
            }
        }
    }

    public void showSlots(JSONObject jo, String url, final String date, final GridView SlotGrid) {
        new CustomAsync1(this, jo, url, new OnAsyncCompleteRequest() {
            public void asyncResponse(String result) {

                if (result == null || result.equals(pl.droidsonroids.gif.BuildConfig.VERSION_NAME)) {
                    Snackbar snackBar = Snackbar.make(layout, "Please try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                        public void onClick(View view) {
                            finish();
                            startActivity(slot_selection.this.getIntent());
                        }
                    });
                    /*snackBar.setActionTextColor(-65536);
                    ((TextView) snackBar.getView().findViewById(R.id.snackbar_text)).setTextColor(-256);
                    snackBar.show();
                   */
                    return;
                }
                Log.e("callservice", "aa36363");

                try {
                    Log.e("callservice", "aa");
                    DatesOfSlots.clear();
                    BookedSlots.clear();
                    JSONObject jSONObject = new JSONObject(result);
                    String status = jSONObject.getString("status");
                    // Toast.makeText(slot_selection.this, result, Toast.LENGTH_LONG).show();
                    if (status.equals("success")) {
                        JSONArray ja = jSONObject.getJSONArray("schedule");
                        JSONArray jaa = jSONObject.getJSONArray("appointmentsdata");
                        for (int i = 0; i < ja.length(); i++) {
                            String NoOfSlots = date + " " + ja.getString(i);
                            Log.e("ServerData", "12     " + NoOfSlots);
                            String finalSlots = ja.getString(i);
                            java.util.Calendar c = java.util.Calendar.getInstance();
                            java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm a");
                            if (simpleDateFormat.parse(simpleDateFormat.format(c.getTime())).getTime() + 7200000 < simpleDateFormat.parse(NoOfSlots).getTime()) {
                                DatesOfSlots.add(finalSlots);
                            }
                        }
                        for (int j = 0; j < jaa.length(); j++) {
                            BookedSlots.add(jaa.getString(j));
                        }
                        if (DatesOfSlots.size() == 0) {
                            Log.e("slotTime", "123     " + slotTime);
                            SlotGrid.setVisibility(View.GONE);
                        } else {
                            SlotGrid.setVisibility(View.VISIBLE);
                        }
                        Log.e("ServerLong", "12345     " + BookedSlots.size() + "     " + DatesOfSlots.size());
                        selectSlotsAdapter = new SelectSlotsAdapter(slot_selection.this, DatesOfSlots, BookedSlots);
                        SlotGrid.setAdapter(selectSlotsAdapter);
                    } else if (status.equals("no data found")) {
                        Snackbar snackbar = Snackbar.make(layout, pl.droidsonroids.gif.BuildConfig.VERSION_NAME + status, Snackbar.LENGTH_LONG).setAction("ok", null);
//                        Toast.makeText(slot_selection.this, pl.droidsonroids.gif.BuildConfig.VERSION_NAME + status, Toast.LENGTH_LONG).show();
                        BookedSlots.clear();
                        DatesOfSlots.clear();

                        selectSlotsAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.e("callservice", "aa36363" + e.toString());
                    e.printStackTrace();
                }
            }
        }).execute(new String[0]);
    }

    public class SelectSlotsAdapter extends BaseAdapter {
        ArrayList<String> BookedSlots;
        ArrayList<String> DatesToDisplay;
        Context con;
        LayoutInflater inflater;
        int selected_slot = 0;

        public SelectSlotsAdapter(Context con, ArrayList<String> DatesToDisplay, ArrayList<String> BookedSlots) {
            this.con = con;
            this.DatesToDisplay = DatesToDisplay;
            this.BookedSlots = BookedSlots;
            this.inflater = (LayoutInflater) con.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return this.DatesToDisplay.size();
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.selectslots_row, parent, false);
                vh = new ViewHolder();
                vh.listDates = (RadioButton) convertView.findViewById(R.id.diffTimes);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            vh.listDates.setText((CharSequence) this.DatesToDisplay.get(position));
            vh.listDates.setChecked(position == selected_slot);
            vh.listDates.setTag(position);
            vh.listDates.setClickable(false);
            if (vh.listDates.isChecked()) {
                vh.listDates.setTextColor(Color.WHITE);
            } else {
                vh.listDates.setTextColor(Color.BLACK);

            }

            if (this.BookedSlots.contains(this.DatesToDisplay.get(position))) {
                vh.listDates.setPaintFlags(vh.listDates.getPaintFlags() | 16);
            } else {
                vh.listDates.setPaintFlags(vh.listDates.getPaintFlags() | 32);
                convertView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selected_slot = position;
                        notifyDataSetChanged();
                        slotTime = vh.listDates.getText().toString().trim();
                        Log.e("SlotTimeee", "123     " + slotTime);
                        timeW.setText(slotTime);
                    }
                });
            }
            return convertView;
        }

        public class ViewHolder {
            RadioButton listDates;
        }
    }

}
