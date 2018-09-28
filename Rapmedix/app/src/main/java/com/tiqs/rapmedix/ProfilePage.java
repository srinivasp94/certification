package com.tiqs.rapmedix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONObject;

/**
 * Created by ADMIN on 2/4/2017.
 */

public class ProfilePage extends Activity {

    ImageView ProfileImage;
    TextView Name, Mobile, Emial, Membership, Address, free_count;//, State;
    View view;
    LinearLayout Edit;
    boolean isNet;
    String Url = "", UserId = "", Image;
    String Addr, city, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        ProfileImage = (ImageView) findViewById(R.id.prflImage);
        Name = (TextView) findViewById(R.id.prflName);
        Mobile = (TextView) findViewById(R.id.prflNo);
        Emial = (TextView) findViewById(R.id.prflEmail);
        free_count = (TextView) findViewById(R.id.free_count);
        Membership = (TextView) findViewById(R.id.membership_type);
        Address = (TextView) findViewById(R.id.membership_address);
        //City = (TextView) findViewById(R.id.membership_city);
        //State = (TextView) findViewById(R.id.membership_state);
        Edit = (LinearLayout) findViewById(R.id.prflEdit);
        view = findViewById(R.id.view);
        View profilePageView = findViewById(R.id.profile_page_view);
        View myLayout = findViewById(R.id.prfl_head);

        ImageView back = (ImageView) myLayout.findViewById(R.id.mainmenu);
        Url = getString(R.string.server) + Constants.category_id;

        ConnectionDetector cd = new ConnectionDetector(this);
        isNet = cd.isConnectingToInternet();

        DataBase_Helper db = new DataBase_Helper(this);
        UserId = db.getUserId("1");

        overrideFonts(ProfilePage.this, profilePageView);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (isNet) {

            try {

                JSONObject jo = new JSONObject();
                jo.put("user_id", UserId);
                Log.e("id", "" + jo.toString());
                free_Consultancy(jo, getResources().getString(R.string.server) + Constants.category_id);


            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {

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

        if (isNet) {

            try {

                JSONObject jo = new JSONObject();
                jo.put("user_id", UserId);
                profileService(jo, Url);

            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {

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

		/*if (isNet) {

			try {

				JSONObject jo = new JSONObject();
				jo.put("user_id", UserId);
				Log.e("id",""+jo.toString());
				new free_Consultancy(jo, getResources().getString(R.string.server) + Constants.category_id);


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

		}*/

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilePage.this, ProfileEdit.class);
                intent.putExtra("Name", Name.getText().toString());
                intent.putExtra("Email", Emial.getText().toString());
                intent.putExtra("Address", Addr);
                intent.putExtra("City", city);
                intent.putExtra("State", state);
                intent.putExtra("Image", Image);
                startActivity(intent);
            }
        });

    }

    public void profileService(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(ProfilePage.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");

                        if (status.equals("success")) {

                            int Type = Integer.parseInt(jo.getString("membership_type"));
                            Image = jo.getString("profile_pic");
                            String Member;
                            if (Type == 1) Member = "Free";
                            else Member = "Premium";

                            Addr = (jo.getString("address"));
                            city = (jo.getString("city"));
                            state = (jo.getString("state"));

                            Name.setText(jo.getString("name"));
                            Mobile.setText(jo.getString("mobile"));
                            Emial.setText(jo.getString("email"));
                            Membership.setText(Member);
                            Address.setText(Addr + "\n" + city);
                            //City.setText(jo.getString("city"));
                            //State.setText(jo.getString("state"));

                            Glide.with(ProfilePage.this).
                                    load(Image).
                                    error(R.drawable.single).
                                    //placeholder(R.drawable.single).
                                            into(ProfileImage);

                        } else {

                            Toast.makeText(ProfilePage.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                }

            }
        });
        ca.execute();
    }

    @Override
    public void onBackPressed() {

        Intent startMain = new Intent(ProfilePage.this, Home_Page.class);
        startActivity(startMain);
    }


    private void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {

                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "robo.ttf"));
            }
        } catch (Exception e) {
        }
    }

    public void free_Consultancy(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(ProfilePage.this, jo, url, new OnAsyncCompleteRequest() {

            Intent intent2 = getIntent();
            Bundle b = intent2.getExtras();

            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
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
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");

                        if (status.equals("success")) {

                            free_count.setText(jo.getString("coupons"));

                        } else {

                            Toast.makeText(ProfilePage.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                }

            }
        });
        ca.execute();
    }

}
