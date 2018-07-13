package com.srinivas.com.distrct.activities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.models.videomodels;
import com.srinivas.com.distrct.network.APIUtils;
import com.srinivas.com.distrct.network.ApiInterface;
import com.srinivas.com.distrct.utils.ConnectionDetector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class videoActivity extends AppCompatActivity implements View.OnClickListener {
    private ApiInterface anInterface;
    private TextView txt1, txt2, txt3, txt4, txt5;
    private Button btn1, btn2, btn3, btn4, btn5;
    private ImageView backButton;
    VideoView video;
    ProgressDialog dialog, pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ConnectionDetector detector = new ConnectionDetector(this);
        dialog = new ProgressDialog(this);
        pd = new ProgressDialog(this);
        anInterface = APIUtils.getAPIService();

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);

        btn1 = (Button) findViewById(R.id.btn_click1);
        btn2 = (Button) findViewById(R.id.btn_click2);
        btn3 = (Button) findViewById(R.id.btn_click3);
        btn4 = (Button) findViewById(R.id.btn_click4);
        btn5 = (Button) findViewById(R.id.btn_click5);

        video = (VideoView) findViewById(R.id.video);


        backButton = (ImageView) findViewById(R.id.imageView);

        setclicklistners();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (detector.isConnectingToInternet()) {
            dialog.show();
            dialog.setMessage("Loading...");
            anInterface.getvideolist().enqueue(new Callback<videomodels>() {
                @Override
                public void onResponse(Call<videomodels> call, Response<videomodels> response) {
                    dialog.dismiss();
                    videomodels videomodels = response.body();
                    txt1.setText("https://www.youtube.com/embed/" + videomodels.videoUrl1);
                    txt2.setText("https://www.youtube.com/embed/" + videomodels.videoUrl2);
                    txt3.setText("https://www.youtube.com/embed/" + videomodels.videoUrl3);
                    txt4.setText("https://www.youtube.com/embed/" + videomodels.videoUrl4);
                    txt5.setText("https://www.youtube.com/embed/" + videomodels.videoUrl5);
                }

                @Override
                public void onFailure(Call<videomodels> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(videoActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    public void setclicklistners() {
        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);
        txt4.setOnClickListener(this);
        txt5.setOnClickListener(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

    }

    public void playvideo(String url) {
        pd.setMessage("Buffering video please wait...");
        pd.show();
        MediaController controller = new MediaController(this);
        controller.setAnchorView(video);
        Uri uri = Uri.parse(url);
        video.setVideoURI(uri);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress dialog when buffering is done
                pd.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click1:
                watchYoutubeVideo(this, txt1.getText().toString());
                break;
            case R.id.btn_click2:
                watchYoutubeVideo(this, txt2.getText().toString());
                break;
            case R.id.btn_click3:
                watchYoutubeVideo(this, txt3.getText().toString());
                break;
            case R.id.btn_click4:
                watchYoutubeVideo(this, txt4.getText().toString());
                break;
            case R.id.btn_click5:
                watchYoutubeVideo(this, txt5.getText().toString());
                break;

        }
    }

    public static void watchYoutubeVideo(Context context, String url) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "" + ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
