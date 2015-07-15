package com.rap.example;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.rap.R;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class IAPSampleActivity extends RAPBaseActivity{

	private Button btn_categoryL, btn_categoryM, btn_categoryS, btn_Items;
	private Spinner sp_categoryL, sp_categoryM, sp_categoryS;
	private ListView lv_items;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iap_sample);
		
		initResourse();
		initEvent();
	}
	
	private void initResourse(){
		
		btn_categoryL = (Button) findViewById(R.id.iap_btn1);
		btn_categoryM = (Button) findViewById(R.id.iap_btn2);
		btn_categoryS = (Button) findViewById(R.id.iap_btn3);
		btn_Items = (Button) findViewById(R.id.iap_btn_getItems);
		
		sp_categoryL = (Spinner) findViewById(R.id.iap_sp1);
		sp_categoryM = (Spinner) findViewById(R.id.iap_sp2);
		sp_categoryS = (Spinner) findViewById(R.id.iap_sp3);
		
		lv_items = (ListView) findViewById(R.id.iap_list);
		
	}
	
	private void initEvent(){
		btn_categoryL.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try {
					HttpRequestBase req = RAPAPIs.GetCategoryL();
					RAPHttpClient.getInstance().background(req, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btn_categoryM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		btn_categoryS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		btn_Items.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		
		lv_items.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	
	
}
