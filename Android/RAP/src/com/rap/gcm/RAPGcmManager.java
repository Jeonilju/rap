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
		Log.i(TAG,"GCM�� ����մϴ�.");
		GCMRegistrar.checkDevice(mContext);
		GCMRegistrar.checkManifest(mContext);
		String myGCMID = GCMRegistrar.getRegistrationId(mContext);
		
		if (myGCMID.equals("")) {
			
			if(RAPSetting.getGCMProjectId().equals("")){
				Log.e(TAG, "GCM Project ID�� �������� �ʾҽ��ϴ�. Project Id�� ������ ȣ�����ּ���.");
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
		Log.i(TAG, "GCM��: " + myGCMID);
	}
}
