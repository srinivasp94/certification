package com.android.Uga;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	MainLayout mLayout;
	private ListView lvMenu;
	private String[] lvMenuItems;
	ImageButton btMenu;
	static ImageView imagetilte;
	TextView text_title;
	static TextView text_title2;
	Typeface typeface1,typeface2;
	TextView text1;
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLayout = (MainLayout) this.getLayoutInflater().inflate(
				R.layout.activity_main, null);
		setContentView(mLayout);

		typeface1=Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
		typeface2=Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat_Bold.ttf");
		/*if (android.os.Build.VERSION.SDK_INT >= 21) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.status_color));

		}else{

		}*/
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 view = inflater.inflate(R.layout.textlayout, null);

		lvMenuItems = getResources().getStringArray(R.array.menu_items);

		lvMenu = (ListView) findViewById(R.id.menu_listview);
		/*lvMenu.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lvMenuItems));*/
		lvMenu.setAdapter(new ArrayAdapter<String>(this,
				R.layout.textlayout, lvMenuItems));
		lvMenu.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Log.d("position", position + "");
				Log.d("view", view + "");
				Log.d("id", id + "");
				onMenuItemClick(parent, view, position, id);
			}

		});

		btMenu = (ImageButton) findViewById(R.id.button_menu);
		btMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Show/hide the menu
				toggleMenu(v);
			}
		});

		imagetilte = (ImageView) findViewById(R.id.activity_main_content_title);

		text_title=(TextView)findViewById(R.id.text_title);
		text_title2=(TextView)findViewById(R.id.text_title2);
		text1=(TextView)view.findViewById(R.id.text1);

		text_title.setTypeface(typeface1);
		text_title2.setTypeface(typeface1);
		text1.setTypeface(typeface2);


		FragmentManager fm = MainActivity.this.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Layout1 fragment = new Layout1();
		ft.add(R.id.activity_main_content_fragment, fragment);
		ft.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void toggleMenu(View v) {
		mLayout.toggleMenu();
	}

	private void onMenuItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		String selectedItem = lvMenuItems[position];
		String currentItem = text_title.getText().toString();
		if (selectedItem.compareTo(currentItem) == 0) {
			mLayout.toggleMenu();
			return;
		}

		FragmentManager fm = MainActivity.this.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment fragment = null;
		Log.d("selecteditem",selectedItem+"");
		if (selectedItem.compareTo("Dashboard") == 0) {
			text_title.setVisibility(View.GONE);
			imagetilte.setVisibility(View.VISIBLE);
			text_title.setVisibility(View.GONE);
			/*if(AppConstants.Four==true){
				imagetilte.setVisibility(View.GONE);
				text_title2.setVisibility(View.VISIBLE);
				text_title2.setText("Ingredients Library");
			} if(AppConstants.TWO==true){
				imagetilte.setVisibility(View.GONE);
				text_title2.setVisibility(View.VISIBLE);
				text_title2.setText("Create My Diet");
			}if(AppConstants.THREE==true){
				imagetilte.setVisibility(View.GONE);
				text_title2.setVisibility(View.VISIBLE);
				text_title2.setText("Nutrient Class");
			}*/


			fragment = new Layout1();
		} /*else if (selectedItem.compareTo("Settings") == 0) {
			tvTitle.setVisibility(View.GONE);
			text_title.setVisibility(View.VISIBLE);
			fragment = new Layout2();
		}*/else if (selectedItem.compareTo("About") == 0) {
			imagetilte.setVisibility(View.GONE);
			text_title2.setVisibility(View.GONE);
			text_title.setVisibility(View.VISIBLE);

			fragment = new Layout3();
		}
		if (fragment != null) {
			ft.replace(R.id.activity_main_content_fragment, fragment);
			ft.commit();
			text_title.setText(selectedItem);

			/*if(AppConstants.ONE){
				text_title.setVisibility(View.VISIBLE);
				tvTitle.setVisibility(View.GONE);
				text_title.setText("Ingredients Library");
			}else if(AppConstants.TWO){
				text_title.setVisibility(View.VISIBLE);
				tvTitle.setVisibility(View.GONE);
				text_title.setText("Create My Diet");
			}else if(AppConstants.THREE){
				text_title.setVisibility(View.VISIBLE);
				tvTitle.setVisibility(View.GONE);
				text_title.setText("Nutrient Class");
			}*/
		}
		mLayout.toggleMenu();
	}

	@Override
	public void onBackPressed() {
		if (mLayout.isMenuShown()) {
			mLayout.toggleMenu();
		} else {
			super.onBackPressed();
		}
	}
}
