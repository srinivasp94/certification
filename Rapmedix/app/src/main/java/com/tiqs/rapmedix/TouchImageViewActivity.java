package com.tiqs.rapmedix;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by TechIq on 7/27/2017.
 */

public class TouchImageViewActivity extends Activity {

    String ImageUrl="", Imagee="", str="";
    String [] ImageString;
    ArrayList<String> FinalImages = new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_image_view);
        ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
        setContentView(mViewPager);
        mViewPager.setAdapter(new TouchImageAdapter());

        Intent intent = getIntent();
        Bundle d = intent.getExtras();

        if (d != null) {

            Imagee = d.getString("image");
            ImageUrl = d.getString("imageurl");

            //initialiseImage();
        }

        ImageString = Imagee.split(",");

        str = ImageUrl+ImageString;

        for (int i = 0; i<ImageString.length; i++) {

            str = ImageUrl+ImageString[i];
            FinalImages.add(str);

        }
    }

    static class TouchImageAdapter extends PagerAdapter {

        //private static int[] images = { R.drawable.img1, R.drawable.img2, R.drawable.img3 };
        String[] imgs;
        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchImageView img = new TouchImageView(container.getContext());


            byte[] decodedString = Base64.decode(imgs[position], Base64.DEFAULT);


            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            img.setImageBitmap(decodedByte);


            container.addView(img, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
