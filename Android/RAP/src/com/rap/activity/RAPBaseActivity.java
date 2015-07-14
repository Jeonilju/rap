package com.rap.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import org.apache.http.client.methods.HttpRequestBase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.rap.RAPSetting;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.example.RAP_MainActivity;

public class RAPBaseActivity extends Activity{

	private static final String TAG="RAPBaseActivity";
	
	/** �� ī��Ʈ */
	private static int AppCount = 0;
	
	/** �� ���۽ð� */
	private static Timestamp startTime;
	
	/** �� ����ð� */
	private static Timestamp endTime;
	
	private static String previousActivity = "";
	private static String currentActiviy = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, "AppCount: " + AppCount);
		
		if(AppCount == 0){
			// ó�� ����
			previousActivity = this.getClass().getName();
			currentActiviy = this.getClass().getName();
			
			startTime = new Timestamp(System.currentTimeMillis());
		}
		
		currentActiviy = previousActivity;
		previousActivity = this.getClass().getName();
		
		if(!previousActivity.equals(currentActiviy)){
			// Activity���� �̵��� �߻��� ���
			try {
				HttpRequestBase req = RAPAPIs.ActivityInfo_Move(previousActivity, currentActiviy);
				RAPHttpClient.getInstance().background(req, null);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		AppCount++;
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
		
		AppCount--;
		
		if(AppCount == 0){
			// �� ����
			Log.i(TAG, "Application ����");
			
			try {
				HttpRequestBase req = RAPAPIs.Common_AppCount();
				RAPHttpClient.getInstance().background(req, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				endTime = new Timestamp(System.currentTimeMillis());
				HttpRequestBase req2 = RAPAPIs.Common_AppTime(startTime.getTime(), endTime.getTime());
				RAPHttpClient.getInstance().background(req2, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}