package com.rap.user;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.rap.Preference;
import com.rap.activity.RAPBaseActivity;

public class RAPUser {

	private static final String TAG = "RAPUser";
	
	public static String name = "";
	public boolean isMan;
	public double let;
	public double lan;
	public int age;
	public String device;
	public String OS;
	
	/** 사용자 이름 반환*/
	public static String getUserId(){
		
		Context context = RAPBaseActivity.getLastContext();
		
		if(context == null){
			
			if(name.equals("")){
				Log.e(TAG, "기기 고유값을 가지고올 수 없습니다.");
				Log.e(TAG, "RAPBaseActivity를 상속받은 Activity가 존재하지 않습니다.");
				Log.e(TAG, "Context가 존재하지 않습니다.");
				
				return "Temp User";
			}
			else{
				return name;
			}
		}
		else{
			name = android.provider.Settings.Secure.getString(RAPBaseActivity.getLastContext().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			return 	name;
		}
		
	}
	
	/** OS 버전 반환 */
	public static String getOSVersion(){
		return android.os.Build.VERSION.RELEASE;
	}

	/** Device 정보 반환 */
	public static String getDeviceVersion(){
		return Build.BOARD;
	}
	
	/** 위치 정보 반환 */
	public static Location getLocation(Context mContext) {
		Location location = null;

		LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);

		if (provider == null) {
			// GPS 사용 불가시 네트워크를 통해 위치정보 얻음
			provider = LocationManager.NETWORK_PROVIDER;
		}

		location = locationManager.getLastKnownLocation(provider);
		
		if(location == null)
			Log.e(TAG, "위치정보를 읽어올 수 없습니다.");
		
		return location;
	}
}
