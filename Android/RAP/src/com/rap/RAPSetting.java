package com.rap;

import android.util.Log;

import com.rap.connect.RAPHttpClient;
import com.rap.status.RAPNetworkConnection;

public class RAPSetting {
	
	private static final String TAG = "RAPSetting";
	
	/** 프로젝트 Key */
	private static String		RAPKey;
	
	/** OS 정보 수집 여부 */
	private static boolean		isOSInfo = false;
	
	/** 기기 정보 수집 여부 */
	private static boolean 		isDeviceInfo = false;
	
	/** 위치 정보 수집 여부 */
	private static boolean 		isPosition = false;

	/** GCM 사용 여부 */
	private static boolean		isGCM = false;
	
	/** GCM 프로젝트 Key값 */
	private static String		GCMProjectId = "";
	
	/** GCM Device 값 */
	private static String		GCMValue = "";
	
	/** 프로모션 반응속도 사용 여부 */
	private static boolean		isPromotion = false;
	
	/** 앱 실행 카운트 */
	private static boolean		isAppCounnt = false;
	
	/** 앱 실행 시간 */
	private static boolean		isRunningTime = false;
	
	/** GCM 알람  */
	private static boolean		isAlarm = true;
	
	/** GCM 알람 진동 */
	private static boolean		isAlarmVib = true;
	
	/** GCM 알람 진동 세기 */
	private static int			VibSize = 500;
	
	/** GCM 알람 소리 */
	private static boolean		isAlarmSound = true;
	
	private static RAPNetworkConnection 			NetworkConnectionMode = RAPNetworkConnection.RealTime;
	
	public static RAPNetworkConnection getNetworkConnectionMode() {
		return NetworkConnectionMode;
	}

	public static void setNetworkConnectionMode(RAPNetworkConnection networkConnectionMode) {
		NetworkConnectionMode = networkConnectionMode;
	}

	public static boolean isGCM() {
		return isGCM;
	}

	public static void setGCM(boolean isGCM) {
		RAPSetting.isGCM = isGCM;
	}

	public static String getGCMProjectId() {
		return GCMProjectId;
	}

	public static void setGCMProjectId(String gCMProjectId) {
		GCMProjectId = gCMProjectId;
	}

	public static boolean isPromotion() {
		return isPromotion;
	}

	public static void setPromotion(boolean isPromotion) {
		RAPSetting.isPromotion = isPromotion;
	}

	public static boolean isAppCounnt() {
		return isAppCounnt;
	}

	public static void setAppCounnt(boolean isAppCounnt) {
		RAPSetting.isAppCounnt = isAppCounnt;
	}

	public static boolean isRunningTime() {
		return isRunningTime;
	}

	public static void setRunningTime(boolean isRunningTime) {
		RAPSetting.isRunningTime = isRunningTime;
	}

	public static String getRAPKey() {
		if(RAPKey == null){
			Log.e(TAG, "프로젝트 Key값을 등록한 후 사용해주세요.");
			Log.e(TAG, "프로젝트 Key는 데쉬보드에서 발급받으실 수 있습니다.");
			Log.e(TAG, "" + RAPHttpClient.getBaseURL() + "/index" + "로 접속해주세요.");
		}
		return RAPKey;
	}

	public static void setRAPKey(String _RAPKey) {
		RAPKey = _RAPKey;
	}

	public static boolean isOSInfo() {
		return isOSInfo;
	}

	public static void setOSInfo(boolean _isOSInfo) {
		isOSInfo = _isOSInfo;
	}

	public static boolean isDeviceInfo() {
		return isDeviceInfo;
	}

	public static void setDeviceInfo(boolean _isDeviceInfo) {
		isDeviceInfo = _isDeviceInfo;
	}

	public static boolean isPosition() {
		return isPosition;
	}

	public static void setPosition(boolean _isPosition) {
		isPosition = _isPosition;
	}
	
	public static String getGCMValue() {
		return GCMValue;
	}

	public static void setGCMValue(String gCMValue) {
		GCMValue = gCMValue;
	}

	public static boolean isAlarm() {
		return isAlarm;
	}

	public static void setAlarm(boolean isAlarm) {
		RAPSetting.isAlarm = isAlarm;
	}

	public static boolean isAlarmVib() {
		return isAlarmVib;
	}

	public static void setAlarmVib(boolean isAlarmVib) {
		RAPSetting.isAlarmVib = isAlarmVib;
	}

	public static int getVibSize() {
		return VibSize;
	}

	public static void setVibSize(int vibSize) {
		VibSize = vibSize;
	}

	public static boolean isAlarmSound() {
		return isAlarmSound;
	}

	public static void setAlarmSound(boolean isAlarmSound) {
		RAPSetting.isAlarmSound = isAlarmSound;
	}
	
}
