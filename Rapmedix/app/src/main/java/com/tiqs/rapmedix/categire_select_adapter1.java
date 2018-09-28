package com.tiqs.rapmedix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class categire_select_adapter1 extends AppCompatActivity
{
	ListView categorie_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorie_select_item1);
		//categorie_list=(ListView)findViewById(R.id.diagnostic_list);
		//diagnostic_list.setAdapter(new Health_adapater_checkup(this,R.layout.diagnostic_item));
	}

}

