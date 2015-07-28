package com.rap.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.iap.RAPIapInfo;

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
		
		Intent intent = getIntent();
		
		if(intent.getData() != null){
			try{
				int promotion_pk = Integer.parseInt(intent.getDataString());
				if(promotion_pk !=  -1){
					try {
						HttpRequestBase req = RAPAPIs.Promotion_send(promotion_pk);
						RAPHttpClient.getInstance().background(req, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				Log.e(TAG, "관리자에게 문의하세요.");
			}
		}
		
		
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
				RAPHttpClient.getInstance().background(req, baseCallback);
				
				endTime = new Timestamp(System.currentTimeMillis());
				
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

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}
	
	Handler baseCallback = new Handler(){

		@Override
		public void handleMessage(Message msg){
			HttpRequestBase req2;
			try {
				req2 = RAPAPIs.Common_AppTime(startTime.getTime(), endTime.getTime());
				RAPHttpClient.getInstance().background(req2, null);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	};
}
