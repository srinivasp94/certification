package com.tiqs.rapmedix;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static com.tiqs.rapmedix.R.id.imageView;

/**
 * Created by TechIq on 7/27/2017.
 */

public class Prescription_Zoom extends AppCompatActivity {

    String ImageUrl="", Imagee="", str="";
    SubsamplingScaleImageView imageView;
    String [] ImageString;
    ArrayList <String> FinalImages = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.prescription_zoom);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        imageView = (SubsamplingScaleImageView) findViewById(R.id.image_view);

        Intent intent = getIntent();
        Bundle d = intent.getExtras();

        if (d != null) {

            Imagee = d.getString("image");
            ImageUrl = d.getString("imageurl");

            //initialiseImage();
        }

        ImageString = Imagee.split(",");

        for (int i = 0; i<ImageString.length; i++) {

            str = ImageUrl+ImageString[i];
            FinalImages.add(str);
            initialiseImage();
        }

    }

    private void initialiseImage() {

        try {

            Log.e("ImageUrl", "123    " + str);

            Bitmap mIcon11 = null;
            InputStream in = new URL(str).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);

            imageView.setImage(ImageSource.bitmap(mIcon11));
            imageView.setDoubleTapZoomDpi(15);
            //imageView.setDebug(true);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
