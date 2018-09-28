package com.tiqs.rapmedix;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission_group.CAMERA;
import static com.tiqs.rapmedix.Manifest.permission.ACCESS_COARSE_LOCATION;
import static com.tiqs.rapmedix.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.tiqs.rapmedix.Manifest.permission.READ_EXTERNAL_STORAGE;
import static com.tiqs.rapmedix.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.tiqs.rapmedix.ProfileEdit.MEDIA_TYPE_IMAGE;

/**
 * Created by ADMIN on 6/2/2017.
 */

public class BestQuote_select_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
	private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 786;
	TextView spn_age, spn_gender;
	EditText patient_name,contact_person,contact_number,email,treatment,preferred_hos,budget_from,budget_to;
	Button upload_reports, submit_reports;
	ImageView remove_image,plus_image,dummy;
	View view_anim;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private static final String IMAGE_DIRECTORY_NAME = "RapMedix_Pictures";
	public static final int MY_PERMISSIONS_REQUEST_WRITE_CAMERA = 123;
	int PICK_IMAGE_MULTIPLE =01;
	private static final int REQUEST_WRITE_PERMISSION = 786;
	Uri fileUri;
	ArrayList<String> Id=new ArrayList<>();
	boolean isNet;
	String[] age = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
			,"21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40"
			,"41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"
			,"61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80"
			,"81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	String[] gender = {"Male","Female"};
	ArrayList<String> Recordes_b64=new ArrayList<>();
	StringBuilder recordsbase64 = new StringBuilder();
	ViewGroup insertPoint;
	Context context;
	View view;
	String Url = "";
	int PERMISSION_REQUEST_CODE=10;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.best_quote_selected_item);

		spn_age = (TextView) findViewById(R.id.spinner_age);
		spn_gender = (TextView) findViewById(R.id.spinner_gender);
		upload_reports = (Button) findViewById(R.id.upload_reports);
		dummy = (ImageView) findViewById(R.id.dummy);
		remove_image = (ImageView) findViewById(R.id.remove_button);
		insertPoint = (ViewGroup) findViewById(R.id.main_layout_image);
		submit_reports = (Button) findViewById(R.id.submit_reports);
		patient_name = (EditText) findViewById(R.id.patient_name);
		contact_person = (EditText) findViewById(R.id.con_person);
		contact_number = (EditText) findViewById(R.id.contact_number);
		email = (EditText) findViewById(R.id.email);
		treatment = (EditText) findViewById(R.id.treatment);
		preferred_hos = (EditText) findViewById(R.id.preffered);
		budget_from = (EditText) findViewById(R.id.budget_from);
		budget_to = (EditText) findViewById(R.id.budget_to);

		view_anim = findViewById(R.id.layout_main);
		/*Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.right_left);
		view_anim.setAnimation(slide_down);*/

		ConnectionDetector cd = new ConnectionDetector(this);
		isNet = cd.isConnectingToInternet();

		/*if (getIntent()!=null) {
			Id = getIntent().getStringExtra("id");
		}*/
		Url = getString(R.string.webData)+ Constants.saveBestQuoteQuotation_service;

		spn_age.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				final Dialog dialog = new Dialog(BestQuote_select_item.this);
				dialog.setContentView(R.layout.age_gender_popup);
				dialog.setTitle("Select Age");
				dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
				ListView listView=(ListView)dialog.findViewById(R.id.age_gender_list);
				ArrayAdapter dataAdapter_age = new ArrayAdapter(BestQuote_select_item.this, R.layout.spinner_item, age);
				listView.setAdapter(dataAdapter_age);
				//dialog.getWindow().setTitleColor();

				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						dialog.dismiss();
						spn_age.setText(age[position]);
					}
				});

				Window window =dialog.getWindow();
				window.setLayout(300,500);


				//dialog.getWindow().setBackgroundDrawableResource(R.color.colorAccent);
				dialog.show();
			}
		});

		spn_gender.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				final Dialog dialog = new Dialog(BestQuote_select_item.this);
				dialog.setContentView(R.layout.gender_popup);
				dialog.setTitle("Select gender");
				dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
				ListView listView=(ListView)dialog.findViewById(R.id.gender_list);
				ArrayAdapter dataAdapter_gender = new ArrayAdapter(BestQuote_select_item.this, R.layout.spinner_item, gender);
				listView.setAdapter(dataAdapter_gender);

				//dialog.getWindow().setTitleColor();
				//android.R.style.Theme_Translucent_NoTitleBar
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						dialog.dismiss();
						spn_gender.setText(gender[position]);
					}
				});

				Window window =dialog.getWindow();
				window.setLayout(350,280);
				dialog.show();

			}
		});


		view = findViewById(R.id.hdrawer_layout);
		ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
		back_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		upload_reports.setOnClickListener(new View.OnClickListener() {

			@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
			public void onClick(View v) {

				selectImage();

			}
		});

		submit_reports.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (patient_name.getText().toString().trim().equals("") || contact_person.getText().toString().trim().equals("") ||
						contact_number.getText().toString().trim().equals("") || spn_age.getText().toString().trim().equals("")||
						spn_gender.getText().toString().trim().equals("")|| treatment.getText().toString().trim().equals("")
						|| preferred_hos.getText().toString().trim().equals("")|| budget_from.getText().toString().trim().equals("")
						|| budget_to.getText().toString().trim().equals("")) {

					Snackbar snackBar = Snackbar.make(view, "Fields Should not be Empty!", Snackbar.LENGTH_SHORT)
							.setAction("", new View.OnClickListener() {
								@Override
								public void onClick(View view) {

								}



							});
					snackBar.setActionTextColor(Color.RED);
					View sbView = snackBar.getView();
					TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
					textView.setTextColor(Color.YELLOW);
					snackBar.show();
				} else {

					if (isNet) {

						try {

							JSONObject jo = new JSONObject();
							jo.put("service_id", Id);
							jo.put("patient_name",patient_name.getText().toString().trim());
							jo.put("contact_person",contact_person.getText().toString().trim());
							jo.put("contact_number",contact_number.getText().toString().trim());
							jo.put("age",spn_age);
							jo.put("gender",spn_gender.getText().toString().trim());
							jo.put("email_id",email.getText().toString().trim());
							jo.put("treatment_for",treatment.getText().toString().trim());
							jo.put("preffered_hospital",preferred_hos.getText().toString().trim());
							jo.put("budget_from",budget_from.getText().toString().trim());
							jo.put("budget_to",budget_to.getText().toString().trim());
							//jo.put("report",repo.getText().toString().trim());
							//jo.put("image",Mobile.getText().toString().trim());

							Log.e("FamilyMembers", jo.toString());

							bestQuote(jo, Url);


						}catch (Exception e) {

							e.printStackTrace();
						}

					}

					else {

						Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
								.setAction("RETRY", new View.OnClickListener() {
									@Override
									public void onClick(View view) {

										finish();
										startActivity(getIntent());
									}
								});
						snackBar.setActionTextColor(Color.RED);
						View sbView = snackBar.getView();
						TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
						textView.setTextColor(Color.YELLOW);
						snackBar.show();
					}


				}
				Toast.makeText(BestQuote_select_item.this, "Thankyou for your request we will get back with best quote", Toast.LENGTH_SHORT).show();
				dismiss();
			}
		});

	}

	public void bestQuote (JSONObject jo, String url) {

		CustomAsync ca = new CustomAsync(BestQuote_select_item.this, jo, url, new OnAsyncCompleteRequest() {
			@Override
			public void asyncResponse(String result) {

				if (result.equals("") || result == null) {


					Snackbar snackBar = Snackbar.make(view, "Please try Again!", Snackbar.LENGTH_INDEFINITE)
							.setAction("RETRY", new View.OnClickListener() {
								@Override
								public void onClick(View view) {

									finish();
									startActivity(getIntent());
								}
							});
					snackBar.setActionTextColor(Color.RED);
					View sbView = snackBar.getView();
					TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
					textView.setTextColor(Color.YELLOW);
					snackBar.show();

				}

				else {

					try {

						JSONObject jo = new JSONObject(result);

						String status = jo.getString("status");

						if (status.equals("success")) {

							Toast.makeText(BestQuote_select_item.this, "Your best quote reports successfully send", Toast.LENGTH_SHORT).show();

						}



						else {

							Toast.makeText(BestQuote_select_item.this, "", Toast.LENGTH_SHORT).show();
						}


					}catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		}); ca.execute();
	}

	public void dismiss() {
		BestQuote_select_item.this.finish();
		Intent i = new Intent(BestQuote_select_item.this, Home_Page.class);  //your class
		startActivity(i);

	}
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
			selectImage();
		}
	}

	private void requestPermission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(new String[]{
					android.Manifest.permission.WRITE_EXTERNAL_STORAGE
			}, REQUEST_WRITE_PERMISSION);
		} else {
			selectImage();
		}
	}
	private void select_photo()
	{
		if (Build.VERSION.SDK_INT >= 23){
			// Here, thisActivity is the current activity
			if (ContextCompat.checkSelfPermission(BestQuote_select_item.this,
					Manifest.permission.READ_EXTERNAL_STORAGE)
					!= PackageManager.PERMISSION_GRANTED) {

				// Should we show an explanation?
				if (ActivityCompat.shouldShowRequestPermissionRationale(BestQuote_select_item.this,
						Manifest.permission.READ_EXTERNAL_STORAGE)) {

					// Show an expanation to the user *asynchronously* -- don't block
					// this thread waiting for the user's response! After the user
					// sees the explanation, try again to request the permission.

				} else {

					// No explanation needed, we can request the permission.

					ActivityCompat.requestPermissions(BestQuote_select_item.this,
							new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
							MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

					// MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
					// app-defined int constant. The callback method gets the
					// result of the request.
				}
			}else{
				ActivityCompat.requestPermissions(BestQuote_select_item.this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
						MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
			}
		}else {

			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			//startActivityForResult(photoPickerIntent, SELECT_PHOTO);
		}

	}


	private void selectImage() {
		//Constants.iscamera = true;
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		TextView title = new TextView(BestQuote_select_item.this);
		title.setText("Add Photo!");
		title.setBackgroundColor(Color.BLACK);
		title.setPadding(10, 15, 15, 10);
		title.setGravity(Gravity.CENTER);
		title.setTextColor(Color.WHITE);
		title.setTextSize(22);


		AlertDialog.Builder builder = new AlertDialog.Builder(
				BestQuote_select_item.this);



		builder.setCustomTitle(title);

		// builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int item) {
				//checkPermission();
				if (items[item].equals("Take Photo"))

				{

					if (ContextCompat.checkSelfPermission(BestQuote_select_item.this,
							android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
							!= PackageManager.PERMISSION_GRANTED)
					{
						if (ActivityCompat.shouldShowRequestPermissionRationale(BestQuote_select_item.this,
								android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
						{

							// Show an expanation to the user *asynchronously* -- don't block
							// this thread waiting for the user's response! After the user
							// sees the explanation, try again to request the permission.

						} else
						{
							// No explanation needed, we can request the permission.

							ActivityCompat.requestPermissions(BestQuote_select_item.this,
									new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
									11);

							// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
							// app-defined int constant. The callback method gets the
							// result of the request.
						}


					}

					else
					{

						Intent intent = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);


						File photo = new
								File(Environment.getExternalStorageDirectory(), "Pic.jpg");
						intent.putExtra(MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(photo));



						//imageUri = Uri.fromFile(photo);

						//startActivityForResult(intent,TAKE_PICTURE);

						Intent intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

						fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

						intents.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);


						// start the image capture Intent
						//dummy.setImageBitmap();
						startActivityForResult(intents, 0);

					}
				}
				else if (items[item].equals("Choose from Library")) {
					if (ContextCompat.checkSelfPermission(BestQuote_select_item.this,
							android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
							!= PackageManager.PERMISSION_GRANTED)
					{
						if (ActivityCompat.shouldShowRequestPermissionRationale(BestQuote_select_item.this,
								android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
						{


						} else
						{
							// No explanation needed, we can request the permission.

							ActivityCompat.requestPermissions(BestQuote_select_item.this,
									new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
									22);

							// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
							// app-defined int constant. The callback method gets the
							// result of the request.
						}
					}else {
						Intent intent = new Intent(BestQuote_select_item.this, CustomPhotoGalleryActivity.class);
						startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
					}
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}
	private static File getOutputMediaFile(int type) {

		// External sdcard location
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				IMAGE_DIRECTORY_NAME);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
						+ IMAGE_DIRECTORY_NAME + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else {
			return null;
		}

		return mediaFile;
	}


	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	public  void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case 1:
				Bitmap bitmap = null;
				if (resultCode == RESULT_OK) {
					if (data != null) {


						try {


							Uri selectedImage = data.getData();
							String[] filePath = { MediaStore.Images.Media.DATA };
							Cursor c = BestQuote_select_item.this.getContentResolver().query(
									selectedImage, filePath, null, null, null);
							c.moveToFirst();
							int columnIndex = c.getColumnIndex(filePath[0]);
							String picturePath = c.getString(columnIndex);
							c.close();
							// Bitmap thumbnail =
							// (BitmapFactory.decodeFile(picturePath));

							Bitmap thumbnail = decodeSampledBitmapFromResource(
									picturePath, 500, 500);


							LayoutInflater vi = (LayoutInflater) BestQuote_select_item.this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

							View v = vi.inflate(R.layout.upload_item, null);

// fill in an
							ImageView image = (ImageView) v.findViewById(R.id.dummy);
							ImageView remove_image = (ImageView) v.findViewById(R.id.remove_button);
							remove_image.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {

									View namebar = (View)v.getParent().getParent();
									insertPoint.removeView(namebar);
								}
							});
							final CardView remove_layout = (CardView) v.findViewById(R.id.image_uploaded);
							RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(150,150);
							RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(140,140);

							v.setLayoutParams(params1);
							image.setLayoutParams(params);
							image.setImageBitmap(thumbnail);

							insertPoint.addView(v);


							//Log.e("filae_b64", "inSamplein storage"+getEncoded64ImageStringFromBitmap(thumbnail));

							Recordes_b64.add(getEncoded64ImageStringFromBitmap(thumbnail));



							//recordsbase64.append(Recordes_b64).append(",");
							//recordsbase64.append(getEncoded64ImageStringFromBitmap(thumbnail).toString());
							//recordsbase64.append("&");

							Log.e("rcords_size",Recordes_b64.toString()+""+Recordes_b64.size());
							//	dummy.setImageBitmap(thumbnail);
							/*Bitmap thumbnail_r = imageOreintationValidator(
									thumbnail, picturePath);*/
							//dummy.setImageBitmap(thumbnail_r);
							//dummy

						} catch (Exception e)
						{
							Log.e("hjhgj",""+Recordes_b64.size());

							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				break;
			case 0:
				if (resultCode == RESULT_OK) {

					previewCapturedImage();

				}

				break;
		}
		if (resultCode == RESULT_OK) {
			if(requestCode == PICK_IMAGE_MULTIPLE){
				ArrayList<String> imagesPathList = new ArrayList<String>();
				String[] imagesPath = data.getStringExtra("data").split("\\|");
				try{
					//	insertPoint.removeAllViews();
				}catch (Throwable e)
				{
					e.printStackTrace();
				}

				for (int i=0;i<imagesPath.length;i++)
				{
					imagesPathList.add(imagesPath[i]);
					Bitmap yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);


					Bitmap thumbnail = decodeSampledBitmapFromResource(
							imagesPath[i], 500, 500);


					LayoutInflater vi = (LayoutInflater) BestQuote_select_item.this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View v = vi.inflate(R.layout.upload_item, null);

					final ImageView image = (ImageView) v.findViewById(R.id.dummy);
					ImageView remove_image = (ImageView) v.findViewById(R.id.remove_button);
					final CardView remove_layout = (CardView) v.findViewById(R.id.image_uploaded);


					RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(150,150);
					RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(140,140);
					v.setLayoutParams(params1);
					image.setLayoutParams(params);
					image.setImageBitmap(thumbnail);


					insertPoint.addView(v);


					Log.e("filae_b64", "inSamplein storage"+getEncoded64ImageStringFromBitmap(thumbnail));

					Recordes_b64.add(getEncoded64ImageStringFromBitmap(thumbnail));
					remove_image.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							//remove_layout.setVisibility(View.INVISIBLE);
							//remove.removeView(image);

							View namebar = (View)v.getParent().getParent();
							insertPoint.removeView(namebar);

						}
					});

				}
			}
		}

	}

	private void previewCapturedImage() {
		try {
			// hide video preview


			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();

			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 8;

			final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
					options);

			Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500,
					false);

			// rotated
			Bitmap thumbnail_r = imageOreintationValidator(resizedBitmap,
					fileUri.getPath());


			LayoutInflater vi2 = (LayoutInflater) BestQuote_select_item.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View v2 = vi2.inflate(R.layout.upload_item, null);
			//dummy.setBackground(null);
			String [] stockArr = Recordes_b64.toArray(new String[Recordes_b64.size()]);
			//Recordes_b64.add(base64Convertions(fileUri.getPath()));
			Recordes_b64.add(base64Convertions(encodeToBase64(thumbnail_r,Bitmap.CompressFormat.PNG, 100)));

			//recordsbase64.append(fileUri.getPath()).append(",");
			Log.e("rcords_size",""+Recordes_b64.size());


			ImageView image2 = (ImageView) v2.findViewById(R.id.dummy);
			ImageView 	remove_image = (ImageView) v2.findViewById(R.id.remove_button);
			remove_image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					View namebar = (View)v.getParent().getParent();
					insertPoint.removeView(namebar);
				}
			});

			RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(150,150);
			RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(140,140);
			v2.setLayoutParams(params1);
			image2.setLayoutParams(params);
			insertPoint.addView(v2);

			image2.setImageBitmap(thumbnail_r);
			//dummy = true;
			Toast.makeText(BestQuote_select_item.this, "done", Toast.LENGTH_LONG)
					.show();
		} catch (NullPointerException e)
		{
			Log.e("mmmmmmmm", "inSamplein storage"+e.toString());
			e.printStackTrace();
		}
	}


	public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
		byte[] byteFormat = stream.toByteArray();
		// get the base 64 string
		String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

		return imgString;
	}
	private Bitmap imageOreintationValidator(Bitmap bitmap, String path) {

		ExifInterface ei;
		try {
			ei = new ExifInterface(path);
			int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					bitmap = rotateImage(bitmap, 90);
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					bitmap = rotateImage(bitmap, 180);
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					bitmap = rotateImage(bitmap, 270);
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;
	}
	public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
	{
		ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
		image.compress(compressFormat, quality, byteArrayOS);
		return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
	}


	private Bitmap rotateImage(Bitmap source, float angle) {

		Bitmap bitmap = null;
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		try {
			bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(),
					source.getHeight(), matrix, true);
		} catch (OutOfMemoryError err) {
			source.recycle();
			Date d = new Date();
			CharSequence s = DateFormat
					.format("MM-dd-yy-hh-mm-ss", d.getTime());
			String fullPath = Environment.getExternalStorageDirectory()
					+ "/RYB_pic/" + s.toString() + ".jpg";
			if ((fullPath != null) && (new File(fullPath).exists())) {
				new File(fullPath).delete();
			}
			bitmap = null;
			err.printStackTrace();
		}
		return bitmap;
	}


	public String  base64Convertions(String fileName)
	{
		String encodedString = "";

		try {
			InputStream inputStream = new FileInputStream(fileName);//You can get an inputStream using any IO API
			byte[] bytes;
			byte[] buffer = new byte[8192];
			int bytesRead;
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			try {
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			bytes = output.toByteArray();
			encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);

		}	catch (Exception e)
		{
			Log.e("exception",e.toString());
		}
		return encodedString;
	}

	public static Bitmap decodeSampledBitmapFromResource(String pathToFile,
														 int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathToFile, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		Log.e("inSampleSize", "inSampleSize______________in storage"
				+ options.inSampleSize);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathToFile, options);
	}
	public static int calculateInSampleSize(BitmapFactory.Options options,
											int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

		}

		return inSampleSize;
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
