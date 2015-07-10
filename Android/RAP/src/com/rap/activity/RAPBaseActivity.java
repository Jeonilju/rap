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
	
	/** 앱 카운트 */
	private static int AppCount = 0;
	
	/** 앱 시작시간 */
	private static Timestamp startTime;
	
	/** 앱 종료시간 */
	private static Timestamp endTime;
	
	private static String previousActivity = "";
	private static String currentActiviy = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, "AppCount: " + AppCount);
		
		if(AppCount == 0){
			// 처음 실행
			previousActivity = this.getClass().getName();
			currentActiviy = this.getClass().getName();
			
			startTime = new Timestamp(System.currentTimeMillis());
		}
		
		currentActiviy = previousActivity;
		previousActivity = this.getClass().getName();
		
		if(!previousActivity.equals(currentActiviy)){
			// Activity간의 이동이 발생한 경우
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
			// 앱 종료
			Log.i(TAG, "Application 종료");
			
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
