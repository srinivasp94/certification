package com.tiqs.rapmedix.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.tiqs.rapmedix.Prescription_Zoom;
import com.tiqs.rapmedix.R;
import com.tiqs.rapmedix.TouchImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Prescription_list_adapter extends PagerAdapter {
    ArrayList<String> mResources ;
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> images = new ArrayList<>();
    String[] imgs;
    int[] images_int;
    public Prescription_list_adapter(Context context , String[] imgs) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources=mResources;
        this.imgs = imgs;
    }

    @Override
    public int getCount()
    {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.prescription_imageview, container, false);

        TouchImageView imageView = (TouchImageView) itemView.findViewById(R.id.image_view);
        Glide.with(mContext).
                load(imgs[position])
                .placeholder(R.drawable.single).
                into(imageView);

        byte[] decodedString = Base64.decode(imgs[position], Base64.DEFAULT);


        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        imageView.setImageBitmap(decodedByte);

        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext.getApplicationContext(), Prescription_Zoom.class);

                intent.putExtra("image",imgs[position]);

                mContext.startActivity(intent);
            }
        });*/


        container.addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
