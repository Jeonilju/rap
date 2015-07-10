package com.rap;

import com.rap.status.RAPNetworkConnection;

public class RAPSetting {
	
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
}
