package com.example.pegasys.pegasyshmis;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView txt_docName, txt_specialization, txt_exp, distance, txt_phone;
    private ImageView img_profile;
    private EditText ed_date;
    private Spinner slots;
    private Button book;
    private LinearLayout call_button;
    private String name, exp, dist, special;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    Dialog dialog1;


    private String[] semidays = {"Select your free time", "Morning", "AfterNoon", "Evening"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCompos();
    }

    private void initCompos() {
        setrefers();
        setOnclicks();
        loadDataIntoView();
    }

    private void setrefers() {
        txt_docName = (TextView) findViewById(R.id.txt_docname);
        txt_specialization = (TextView) findViewById(R.id.txt_doc_Specialname);
        txt_exp = (TextView) findViewById(R.id.txt_docexperiance);
        distance = (TextView) findViewById(R.id.txt_diastance);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        call_button = (LinearLayout) findViewById(R.id.call);
        img_profile = (ImageView) findViewById(R.id.img_prifile);
        call_button.setVisibility(View.GONE);

        ed_date = (EditText) findViewById(R.id.edt_date);
        slots = (Spinner) findViewById(R.id.spinner_timeslot);
        book = (Button) findViewById(R.id.btn_book);
    }

    private void setOnclicks() {
        ed_date.setOnClickListener(this);
        book.setOnClickListener(this);
//        slots.setOnClickListener(this);
//        slots.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_date:
                initiateaDatepicker();
                break;
            case R.id.spinner_timeslot:
                setSpinnerAdapter();
                break;
            case R.id.btn_book:
                showAler();
                break;
        }
    }

    private void loadDataIntoView() {
        Intent i = getIntent();
        if (i != null) {
            name = i.getStringExtra("NAME");
            exp = i.getStringExtra("EXPERIANCE");
            dist = i.getStringExtra("DISTANCE");
            special = i.getStringExtra("SPECIAL");
            txt_docName.setText("" + name);
            distance.setText("" + dist + "K.M");
            txt_exp.setText("" + exp);
            txt_specialization.setText("" + special);
        }
        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, month);
                update_Date();

            }
        };
        setSpinnerAdapter();
    }

    private void update_Date() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ed_date.setText(sdf.format(calendar.getTime()));
    }


    private void initiateaDatepicker() {
        new DatePickerDialog(DescriptionActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void setSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, semidays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slots.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, "Clicked at" + semidays[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void showAler() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm.!");
        builder.setMessage("your Appointment is booked");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog1.dismiss();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 1000);
            }
        });
        dialog1 = new Dialog(this);
        dialog1 = builder.create();
        dialog1.show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
