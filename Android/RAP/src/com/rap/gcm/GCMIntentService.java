package com.rap.gcm;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.rap.RAPSetting;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "RAP_GCM";
	
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
		
//		// target Activity 설정
//		Intent targetActivity = new Intent(this, IntroActivity.class);
//		PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext()
//                   , 0
//                   , targetActivity
//                   , Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//		
//		Notification notice = new NotificationCompat.Builder(this)
//				.setContentTitle(title)
//				.setContentText(contents)
//				.setSmallIcon(R.drawable.app_icon1)
//				.setAutoCancel(true).setContentIntent(pIntent).build();
//
//		if(Preference.getBoolean(context, Conf.PREFERENCE_ALARM))
//		{
//			// 알림창
//			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//			manager.notify(1, notice);
//		}
//		
//		if(Preference.getBoolean(context, Conf.PREFERENCE_ALARM_VIB))
//		{
//			// 진동 설정
//			Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//			vibe.vibrate(500);
//		}
//
//		if(Preference.getBoolean(context, Conf.PREFERENCE_ALARM_SOUND))
//		{
//			// 알림음
//			Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(),RingtoneManager.TYPE_NOTIFICATION);
//			Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
//			ringtone.play();	
//		}
		
		if(intent.hasExtra("type")){
			String type = intent.getStringExtra("type");
			Log.e("getmessage", "getmessage:" + type);
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
