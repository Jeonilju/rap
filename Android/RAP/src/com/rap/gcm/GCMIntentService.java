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
		
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		
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
