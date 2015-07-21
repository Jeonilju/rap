package com.rap.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class RAPBaseActivity extends Activity{

	private static final String TAG="RAPBaseActivity";
	
	public static TelephonyManager userInfo;
	
	/** 앱 카운트 */
	private static int AppCount = 0;
	
	/** 앱 시작시간 */
	private static Timestamp startTime;
	
	/** 앱 종료시간 */
	private static Timestamp endTime;
	
	/** Activity 스텍 */
	private static List<Activity> activityList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(activityList == null){
			activityList = new ArrayList<Activity>();
			userInfo = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		}
			
		
		activityList.add(this);
		
		if(AppCount == 0){
			startTime = new Timestamp(System.currentTimeMillis());
		}
		
		if(activityList.size() > 1){
			// Activity간의 이동이 발생한 경우
			try {
				
				int index = activityList.size() - 2;
				
				Log.i(TAG, "Move Activity: "
						+ activityList.get(index).getClass().getName() + " to "
						+ activityList.get(index + 1).getClass().getName());
				
				HttpRequestBase req = RAPAPIs.ActivityInfo_Move(
						activityList.get(index).getClass().getName()
						, activityList.get(index + 1).getClass().getName());
				
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
		
		activityList.remove(this);
		
		if(AppCount == 0){
			Log.i(TAG, "Application 종료");
			try {
				HttpRequestBase req = RAPAPIs.Common_AppCount();
				RAPHttpClient.getInstance().background(req, null);
				
				endTime = new Timestamp(System.currentTimeMillis());
				HttpRequestBase req2 = RAPAPIs.Common_AppTime(startTime.getTime(), endTime.getTime());
				RAPHttpClient.getInstance().background(req2, null);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/** 엑티비티 카운트 */
	public static int getActivityCount(){
		return activityList.size();
	}
	
	public static Context getLastContext(){
		if(getActivityCount() > 0)
			return activityList.get(getActivityCount() - 1);
		else
			return null;
	}
}
