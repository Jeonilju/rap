package com.rap.example;

import android.os.Bundle;
import android.widget.TextView;

import com.rap.R;
import com.rap.activity.RAPBaseActivity;

public class SampleActivity4 extends RAPBaseActivity{

	private TextView pageTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample1);
		
		initResourse();
	}

	private void initResourse(){
		pageTitle = (TextView) findViewById(R.id.page_name);
		pageTitle.setText("������ 004");
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
