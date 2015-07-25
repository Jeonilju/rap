package com.rap.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rap.R;
import com.rap.RAPSetting;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.gcm.RAPGcmManager;

public class RAP_MainActivity extends RAPBaseActivity {

	private static final String TAG = "RAP_MainActivity";
	
	private Button btnSex, btnAge, btnLocation, btnOs, btnDevice;
	
	private Button btnActivity001,btnActivity002,btnActivity003,btnActivity004,btnActivity005, iap_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rap__main);
		
		initResourse();
		initEvent();
		
		RAPSetting.setRAPKey("1");
		RAPSetting.setGCMProjectId("1065883592772");
		RAPGcmManager.registerGcm(this);
		
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		Log.i(TAG, "tmDevice: " + tmDevice);
		Log.i(TAG, "tmSerial: " + tmSerial);
		Log.i(TAG, "androidId: " + androidId);
	}
	
	private void initResourse(){
		btnSex = (Button) findViewById(R.id.user_sex);
		btnAge = (Button) findViewById(R.id.user_age);
		btnLocation = (Button) findViewById(R.id.user_location);
		btnOs = (Button) findViewById(R.id.user_os);
		btnDevice = (Button) findViewById(R.id.user_device);
		
		btnActivity001 = (Button) findViewById(R.id.page_001);
		btnActivity002 = (Button) findViewById(R.id.page_002);
		btnActivity003 = (Button) findViewById(R.id.page_003);
		btnActivity004 = (Button) findViewById(R.id.page_004);
		btnActivity005 = (Button) findViewById(R.id.page_005);
		
		iap_btn = (Button) findViewById(R.id.iap_btn);
	}
	
	private void initEvent(){
		btnSex.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SexDialog dialog = new SexDialog(RAP_MainActivity.this);
				dialog.setTitle("성별 정보 입력");

				dialog.setOnDismissListener(new OnDismissListener() {

					@Override
					public void onDismiss(DialogInterface dialog) {
					}
				});

				dialog.show();
			}
		});

		btnAge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AgeDialog dialog = new AgeDialog(RAP_MainActivity.this);
				dialog.setTitle("나이 정보 입력");

				dialog.setOnDismissListener(new OnDismissListener() {

					@Override
					public void onDismiss(DialogInterface dialog) {
					}
				});

				dialog.show();
			}
		});

		btnLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					HttpRequestBase req = RAPAPIs.UserInfo_Location(RAP_MainActivity.this);
					RAPHttpClient.getInstance().background(req, null);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btnOs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					HttpRequestBase req = RAPAPIs.UserInfo_OS();
					RAPHttpClient.getInstance().background(req, null);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btnDevice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					HttpRequestBase req = RAPAPIs.UserInfo_Device();
					RAPHttpClient.getInstance().background(req, null);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btnActivity001.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RAP_MainActivity.this,
						SampleActivity1.class);
				startActivity(intent);
			}
		});

		btnActivity002.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RAP_MainActivity.this,
						SampleActivity2.class);
				startActivity(intent);
			}
		});

		btnActivity003.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RAP_MainActivity.this,
						SampleActivity3.class);
				startActivity(intent);
			}
		});

		btnActivity004.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RAP_MainActivity.this,
						SampleActivity4.class);
				startActivity(intent);
			}
		});

		btnActivity005.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RAP_MainActivity.this,
						SampleActivity5.class);
				startActivity(intent);
			}
		});
		
		iap_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent(RAP_MainActivity.this, IAPSampleActivity.class);
				//startActivity(intent);
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}
}
