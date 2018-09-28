package com.tiqs.rapmedix;


import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Doctor_list_adapter extends BaseAdapter
{
//    private List<ListData> mylist = new ArrayList<ListData>();

    //ArrayList <ListData>myList = new ArrayList();
    LayoutInflater inflater;
    Context context;
    String s,iddd;
    // String[] Title, Desc, Title1,mobile;
    ArrayList<String>  doctor_name,image,specialisation_names,experiences,degree_names,hospital_names,distances;
    Typeface face;
    TextView tvTitle;
    ImageView imgview;
    AnimatorSet set;
    int res,id ;
    String mobile_numeber="9574562510";
    private ArrayList<Doctor_list_helper> doctor_list_helper;
    public Doctor_list_adapter(Context context,  ArrayList<Doctor_list_helper> doctor_list_helper/* ArrayList<String> Image, ArrayList<String> doctor_name, ArrayList<String> specialisation_names, ArrayList<String> experiences, ArrayList<String> degree_names, ArrayList<String> hospital_names, ArrayList<String> distances*/) {
        this.context = context;
        this.doctor_list_helper = doctor_list_helper;
        this.doctor_name = doctor_name;
        this.specialisation_names = specialisation_names;
        this.experiences = experiences;
        this.degree_names = degree_names;
        this.hospital_names = hospital_names;
        this.distances = distances;

        inflater = LayoutInflater.from(this.context);

    }



    @Override
    public int getCount() {

        return doctor_list_helper.size();
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
        final ViewHolder_list vh;

        if (convertView==null) {
            convertView = inflater.inflate(R.layout.doctor_list_item_2, parent, false);
            vh=new ViewHolder_list();
            vh.textView = (TextView) convertView.findViewById(R.id.doctor_name);
            vh.imageView = (ImageView) convertView.findViewById(R.id.doctor_image);
            vh.call_button = (LinearLayout) convertView.findViewById(R.id.call_button);

            vh.specialization = (TextView) convertView.findViewById(R.id.specialization);
            vh.exp = (TextView) convertView.findViewById(R.id.exp);
            vh.degree = (TextView) convertView.findViewById(R.id.degree);
            vh.hospital = (TextView) convertView.findViewById(R.id.hospital);
            vh.distance = (TextView) convertView.findViewById(R.id.distance);

            vh.textView.setTypeface(face);

            convertView.setTag(vh);

        }else
        {
            vh=(ViewHolder_list)convertView.getTag();

        }

      //  vh.imageView.setImageResource(image.get(position));
        Glide
                .with(context)
                .load(doctor_list_helper.get(position).getImage_url())
                .centerCrop()
                .placeholder(R.drawable.doctor_icon)
                .crossFade()
                .into(vh.imageView);


        vh.textView.setText("Dr."+doctor_list_helper.get(position).getDoctor_name());
        vh.textView.setTypeface( Typeface.DEFAULT, Typeface.BOLD);

        vh.specialization.setText(doctor_list_helper.get(position).getDoctor_speciality());
        vh.exp.setText("( "+doctor_list_helper.get(position).getExperience()+"years Experience)");
        vh.degree.setText(doctor_list_helper.get(position).getDoctor_degree());
        vh.hospital.setText(doctor_list_helper.get(position).getDoctor_hospital());
        vh.doctorId = doctor_list_helper.get(position).getDoctorId();

        vh.distance.setText(String.valueOf(Math.round(Double.parseDouble(doctor_list_helper.get(position).getDoctor_distance())))+" km");




        vh.call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkPermission()) {

                        // Snackbar.make(linearLayout, "Permission already granted.", Snackbar.LENGTH_LONG).show();

                    } else {

                        //Snackbar.make(linearLayout, "Please request permission.", Snackbar.LENGTH_LONG).show();
                        requestPermission();
                        //  Snackbar.make(linearLayout, "Permission already granted.", Snackbar.LENGTH_LONG).show();
                    }

            }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+context.getString(R.string.mobile)));
                context.startActivity(intent);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DoctorDescription.class);
                intent.putExtra("doctorId", vh.doctorId);
                context.startActivity(intent);
            }
        });


        return convertView;
    }


    private boolean checkPermission()
    {
        int result = ContextCompat.checkSelfPermission((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }


    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION)) {

            // Toast.makeText(this, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }



}
class ViewHolder_list
{
    TextView textView,specialization,exp,degree,hospital,distance;
    ImageView imageView;
    LinearLayout call_button;
    String doctorId;
}
