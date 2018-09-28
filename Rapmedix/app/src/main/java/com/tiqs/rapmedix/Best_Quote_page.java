package com.tiqs.rapmedix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tiqs.rapmedix.adapters.BestQuote_list_adapter;

/**
 * Created by ADMIN on 6/2/2017.
 */

public class Best_Quote_page extends AppCompatActivity
{
	ListView diagnostic_list;
	LinearLayout submit_select;

	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.best_quote_list);
	diagnostic_list=(ListView)findViewById(R.id.diagnostic_list);
	diagnostic_list.setAdapter(new BestQuote_list_adapter(this,R.layout.best_quote_selected_item));

	}
}
