package com.px;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.px.tool.Preference;
import com.rap.activity.RAPBaseActivity;

public class ChargeActivity extends RAPBaseActivity{

	private ListView mainList, subList;
	private Button mainBtn, subBtn;
	
	private ArrayAdapter<String> mainAdapter;
	private ArrayAdapter<String> subAdapter;
	
	public static String mainName = "Main È­Æó";
	public static String subName = "Sub È­Æó";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		
		initResourse();
		initEvent();
	}

	private void initResourse(){
		mainList = (ListView) findViewById(R.id.charge_list_main);
		subList = (ListView) findViewById(R.id.charge_list_sub);
		
		mainBtn = (Button) findViewById(R.id.charge_btn_main);
		subBtn = (Button) findViewById(R.id.charge_btn_sub);
		
		mainAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		subAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		
		mainName = Preference.getString(ChargeActivity.this, Preference.PREF_MAIN);
		subName = Preference.getString(ChargeActivity.this, Preference.PREF_SUB);
		
		mainAdapter.add("1000 " + mainName);
		mainAdapter.add("2000 " + mainName);
		mainAdapter.add("3000 " + mainName);
		mainAdapter.add("4000 " + mainName);
		mainAdapter.add("5000 " + mainName);
		mainAdapter.add("10000 " + mainName);
		
		subAdapter.add("1000 " + subName);
		subAdapter.add("2000 " + subName);
		subAdapter.add("3000 " + subName);
		subAdapter.add("4000 " + subName);
		subAdapter.add("5000 " + subName);
		subAdapter.add("10000 " + subName);
		
		mainList.setAdapter(mainAdapter);
		subList.setAdapter(subAdapter);
	}
	
	private void initEvent(){
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
		
		subList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});

		mainBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainList.setVisibility(View.VISIBLE);
				subList.setVisibility(View.GONE);
			}
		});

		subBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainList.setVisibility(View.GONE);
				subList.setVisibility(View.VISIBLE);
			}
		});
	}
}
