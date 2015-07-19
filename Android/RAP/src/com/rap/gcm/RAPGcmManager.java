package com.rap.gcm;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;
import com.rap.Preference;
import com.rap.RAPSetting;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class RAPGcmManager {
	
	private static final String TAG = "RAP_GCM";
	
	public static void registerGcm(Context mContext) {
		Log.i(TAG,"GCM을 등록합니다.");
		GCMRegistrar.checkDevice(mContext);
		GCMRegistrar.checkManifest(mContext);
		String myGCMID = GCMRegistrar.getRegistrationId(mContext);
		
		if (myGCMID.equals("")) {
			
			if(RAPSetting.getGCMProjectId().equals("")){
				Log.e(TAG, "GCM Project ID를 설정하지 않았습니다. Project Id를 설정후 호출해주세요.");
				return;
			}
			
			GCMRegistrar.register(mContext,  RAPSetting.getGCMProjectId());
			myGCMID = GCMRegistrar.getRegistrationId(mContext);
		} 
		
		Preference.putString(mContext, Preference.PREF_GCM, myGCMID);
		try {
			HttpRequestBase req = RAPAPIs.UserInfo_GCM(myGCMID);
			RAPHttpClient.getInstance().background(req, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "GCM값: " + myGCMID);
	}
}
