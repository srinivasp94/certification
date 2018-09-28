package com.tiqs.rapmedix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ADMIN on 6/2/2017.
 */

public class Categories_submit_item extends AppCompatActivity
{
	View view;
	LinearLayout layout_main;
	CardView card_1, card_2, card_3;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories_select_list2);

		layout_main = (LinearLayout) findViewById(R.id.linear_layout);
		card_1 = (CardView) findViewById(R.id.cad_1);
		card_2 = (CardView) findViewById(R.id.cad_2);
		card_3 = (CardView) findViewById(R.id.cad_3);

		view = findViewById(R.id.linear_layout);
		Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.right_left);
		view.setAnimation(slide_down);
		View view=findViewById(R.id.hdrawer_layout);
		ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
		back_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		card_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cat_book = new Intent(Categories_submit_item.this, Book_Categories.class);
				startActivity(cat_book);
			}
		});

		card_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cat_book = new Intent(Categories_submit_item.this, Book_Categories.class);
				startActivity(cat_book);
			}
		});

		card_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cat_book = new Intent(Categories_submit_item.this, Book_Categories.class);
				startActivity(cat_book);
			}
		});
	}

}
