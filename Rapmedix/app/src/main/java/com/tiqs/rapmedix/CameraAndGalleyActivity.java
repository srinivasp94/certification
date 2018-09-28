/*
package com.tiqs.rapmedix;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sys.example.R;

import java.io.File;

public class CameraAndGalleyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button  mGallery;
    private TextView mCam;
    private ImageView mPic;
    private Uri file;
    private Bitmap bitmap_Camera;
    private ThumbnailUtils thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_and_galley);
        initComponents();

    }

    private void initComponents() {
        setReferences();
        setClickListners();
    }

    private void setReferences() {
        mCam = (TextView) findViewById(R.id.tv_camera);
        mGallery = (Button) findViewById(R.id.tv_gallery);
        mPic = (ImageView) findViewById(R.id.iv_pic);
    }

    public void setClickListners() {
        mCam.setOnClickListener(this);
        mGallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_camera:
                callCamera();
//                break;
            case R.id.tv_gallery:
                callGallery();
//                break;
        }
    }

    private void callCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(CameraAndGalleyActivity.this,
                    Manifest.permission.CAMERA)

                    + ActivityCompat.checkSelfPermission(CameraAndGalleyActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    + ActivityCompat.checkSelfPermission(CameraAndGalleyActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                */
/*if (ActivityCompat.shouldShowRequestPermissionRationale(CameraAndGalleyActivity.this,
                        Manifest.permission.CAMERA)

                        && ActivityCompat.shouldShowRequestPermissionRationale(CameraAndGalleyActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)

                        && ActivityCompat.shouldShowRequestPermissionRationale(CameraAndGalleyActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA}, 1);

//sshould request premissions
                    Toast.makeText(this, "Need All Permissions ", Toast.LENGTH_SHORT).show();
                *//*

                ActivityCompat.requestPermissions(CameraAndGalleyActivity.this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


            } else {
                ActivityCompat.requestPermissions(CameraAndGalleyActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }


        } else {
            invitecameraintent();
        }

    }


    private void callGallery() {
    }

    private void invitecameraintent() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "Srinu_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intentCamera, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                try {
                    bitmap_Camera = MediaStore.Images.Media.getBitmap(getContentResolver(), file);
                    mPic.setImageBitmap(thumbnail.extractThumbnail(bitmap_Camera, bitmap_Camera.getWidth(), bitmap_Camera.getHeight()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("exception", e.toString());
                }
            }

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    invitecameraintent();
                } else {

                    Toast.makeText(CameraAndGalleyActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            case 2:

        }
    }
}
*/
