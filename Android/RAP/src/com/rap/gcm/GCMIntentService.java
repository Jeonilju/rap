package com.rap.gcm;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.rap.RAPSetting;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "RAP_GCMIntentService";
	
	public GCMIntentService(){ 
		this(RAPSetting.getGCMProjectId());
	}
	
	public GCMIntentService(String project_id) { 
		super(project_id); 
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		try {  
		    Class.forName("android.os.AsyncTask");
		} catch (ClassNotFoundException e) {  
		}  
	}

	@Override
	  protected String[] getSenderIds(Context context) {
	     String[] ids = new String[1];
	     ids[0] = RAPSetting.getGCMProjectId();
	     return ids;
	  }
	 
	
	@Override
	protected void onError(Context arg0, String arg1) {
		Log.i(TAG, "GCM onError");
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		
		Log.i(TAG, "GCM Message Get");
		
		String title = intent.getStringExtra("title");
		String contents = intent.getStringExtra("contents");
		String className = intent.getStringExtra("class");
		String promotion_pk_str = intent.getStringExtra("promotion_pk");
		int promotion_pk = Integer.parseInt(promotion_pk_str);
		
		// target Activity 설정
		Intent targetActivity;
		PendingIntent pIntent = null;
		try {
			targetActivity = new Intent(this, Class.forName(className));
			
			targetActivity.setData(Uri.parse("" + promotion_pk));
			
			targetActivity.putExtra("RAP_GCM_time", false);
			targetActivity.putExtra("promotion_pk", promotion_pk);
			pIntent = PendingIntent.getActivity(getApplicationContext()
	                   , 0
	                   , targetActivity
	                   , PendingIntent.FLAG_UPDATE_CURRENT);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			Log.e(TAG, "존재하지 않는 ClassName입니다. [" + className + "]");
		}
		
		
		Notification notice = new NotificationCompat.Builder(context)
				.setContentTitle(title)
				.setContentText(contents)
				.setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_dialog_email)
				.setTicker("test")
				.setContentIntent(pIntent).build();

		if(RAPSetting.isAlarm())
		{
			// 알림창
			Log.i(TAG, "isAlarm 설정");
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			manager.notify(987465, notice);
		}
		
		if(RAPSetting.isAlarmVib())
		{
			// 진동 설정
			Log.i(TAG, "isAlarmVib 설정");
			Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibe.vibrate(RAPSetting.getVibSize());
		}

		if(RAPSetting.isAlarmSound())
		{
			// 알림음
			Log.i(TAG, "isAlarmSound 설정");
			Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(),RingtoneManager.TYPE_NOTIFICATION);
			Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
			ringtone.play();	
		}
	}
	
	@Override
	protected void onRegistered(Context context, String reg_id) {
		Log.e(TAG, "Register GCM Value: " + reg_id);
		
		try {
			HttpRequestBase req = RAPAPIs.UserInfo_GCM(reg_id);
			RAPHttpClient.getInstance().background(req, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		Log.e(TAG, "Delete GCM Value: " + arg1);
	}
}
