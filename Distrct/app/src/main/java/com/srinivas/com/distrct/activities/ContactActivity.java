package com.srinivas.com.distrct.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.srinivas.com.distrct.R;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout phone, email, fb, twitter, utube,whatsapp;
    private ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initcomponents();
    }

    private void initcomponents() {
        setreferences();
        setonclicks();
    }

    private void setreferences() {
        phone = (LinearLayout) findViewById(R.id.linear_phone);
        email = (LinearLayout) findViewById(R.id.linear_email);
        fb = (LinearLayout) findViewById(R.id.linear_fb);
        twitter = (LinearLayout) findViewById(R.id.linear_twitter);
        utube = (LinearLayout) findViewById(R.id.linear_utube);
        whatsapp= (LinearLayout) findViewById(R.id.linear_whatsapp);

        backbutton = (ImageView) findViewById(R.id.imageView);
    }

    private void setonclicks() {
        phone.setOnClickListener(this);
        email.setOnClickListener(this);
        fb.setOnClickListener(this);
//        twitter.setOnClickListener(this);
        utube.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_phone:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "9059606383"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
            case R.id.linear_email:
                Intent intentemail = new Intent(Intent.ACTION_SEND);

                intentemail.putExtra(Intent.EXTRA_EMAIL, "info@manawanaparthy.com");
                intentemail.putExtra(Intent.EXTRA_SUBJECT, "Manawanaparthy ");
                intentemail.putExtra(Intent.EXTRA_TEXT, "Hello ");
                intentemail.setType("message/rfc822");
                startActivity(Intent.createChooser(intentemail, "Select Email Sending App : "));
                break;
            case R.id.linear_fb:
                Intent fb = new Intent(ContactActivity.this,WebActivity.class);
                fb.putExtra("WebUrl","https://www.facebook.com/manawanaparthy");
                startActivity(fb);
                break;
            case R.id.linear_twitter:
                Intent twitter = new Intent(ContactActivity.this,WebActivity.class);
                twitter.putExtra("WebUrl","https://twitter.com/manawanaparthy");
                startActivity(twitter);
                break;
            case R.id.linear_utube:
                Intent utube = new Intent(ContactActivity.this,WebActivity.class);
                utube.putExtra("WebUrl","https://www.youtube.com/channel/UCtLUCokAJdGUwPhX_Kt0rrA?view_as=subscriber");
                startActivity(utube);
                break;
            case R.id.linear_whatsapp:
               /* Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
                try {
                    this.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
//                    ToastHelper.MakeShortText("Whatsapp have not been installed.");
                    Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
*/
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                String url = "https://chat.whatsapp.com/AwwLOXnAwkl35S6cqHhpKy";
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
               break;
            case R.id.imageView:
                finish();
                break;
        }
    }
}
