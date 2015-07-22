package com.px;

import android.content.Intent;
import android.os.Bundle;

import com.px.tool.Preference;
import com.rap.activity.RAPBaseActivity;

public class IntroActivity extends RAPBaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		if(!Preference.getBoolean(IntroActivity.this, Preference.isFirst)){
			// ó�� �α���
			Preference.putBoolean(IntroActivity.this, Preference.isFirst, true);
			
			
			
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	
	
}