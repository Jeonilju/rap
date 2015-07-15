package com.rap.connect;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.rap.RAPSetting;
import com.rap.user.RAPUser;


public class RAPAPIs {

	private static final String TAG = "RAP_APIs";
	
	public static HttpRequestBase getTest() {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/setUserAge");
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	public static HttpRequestBase example(int data) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/member/login");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("data", "" + data));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////														////////////////////////
	///////////////////////						Common 정보						////////////////////////
	///////////////////////														////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 앱 카운트 증가
	 * */
	public static HttpRequestBase Common_AppCount() throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/count");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		return httpPost;
	}
	
	/**
	 * 앱 사용시간 전송
	 * */
	public static HttpRequestBase Common_AppTime(long startTime, long endTime) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/time");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("startTime", "" + startTime));
		nameValuePairs.add(new BasicNameValuePair("endTime", "" + endTime));
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////														////////////////////////
	///////////////////////						Activity 정보						////////////////////////
	///////////////////////														////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Activity 이동 정보 전송
	 * @param previousActivity 이전 Activity 이름
	 * @param currentActiviy 현재 Activity 이름
	 * @throws UnsupportedEncodingException 
	 * */
	public static HttpRequestBase ActivityInfo_Move(String previousActivity, String currentActiviy) throws UnsupportedEncodingException{
		
		Log.i(TAG, "Activity 이동 정보 API 호출");
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/Activity/move");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("activity_name", "" + previousActivity));
		nameValuePairs.add(new BasicNameValuePair("activityb_name", "" + currentActiviy));
		
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	/**
	 * Activity 빈도 정보 전송
	 * @param currentActiviy 현재 Activity 이름
	 * @throws UnsupportedEncodingException 
	 * */
	public static HttpRequestBase ActivityInfo_Move(String currentActiviy) throws UnsupportedEncodingException{
		
		Log.i(TAG, "Activity 이동 정보 API 호출");
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/Activity/frequency");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("activityb_name", "" + currentActiviy));
		
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////														////////////////////////
	///////////////////////						사용자 정보							////////////////////////
	///////////////////////														////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * GPS 정보 전송
	 * @param isMan	남자면 True, 여자면 False
	 * @throws UnsupportedEncodingException 
	 * */
	public static HttpRequestBase UserInfo_GCM(String gcm_id) throws UnsupportedEncodingException{
		
		Log.i(TAG, "사용자 GCM 정보 API 호출");
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/sex");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("gcm_id", "" + gcm_id));
		
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	/**
	 * 성별 정보 전송
	 * @param isMan	남자면 True, 여자면 False
	 * @throws UnsupportedEncodingException 
	 * */
	public static HttpRequestBase UserInfo_Sex(boolean isMan) throws UnsupportedEncodingException{
		
		Log.i(TAG, "사용자 성별정보 API 호출");
		
		if(isMan)
			Log.i(TAG, "성별: 남자");
		else
			Log.i(TAG, "성별: 여자");
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/sex");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		if(isMan)
			nameValuePairs.add(new BasicNameValuePair("sex", "" + 1));
		else
			nameValuePairs.add(new BasicNameValuePair("sex", "" + 2));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	/** 
	 * 위치 정보 전송
	 * @param lat 위도
	 * @param lon 경도
	 * */
	public static HttpRequestBase UserInfo_Location(double lat, double lon) throws UnsupportedEncodingException{
	
		Log.i(TAG, "사용자 위치정보 API 호출");
		Log.i(TAG, "lat/len: " + lat + "/" + lon);
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/location");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("lat", "" + lat));
		nameValuePairs.add(new BasicNameValuePair("len", "" + lon));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	/** 
	 * 위치 정보 전송
	 * @param lat 위도
	 * @param lon 경도
	 * */
	public static HttpRequestBase UserInfo_Location(Context mContext) throws UnsupportedEncodingException{
	
		Log.i(TAG, "사용자 위치정보 API 호출");
		Location userLocation = RAPUser.getLocation(mContext);
		if(userLocation == null){
			Log.e(TAG, "사용자의 위치정보를 가져올 수 없습니다.");
			Log.e(TAG, "잠시후에 호출해주세요.");
			return null;
		}
		Log.i(TAG, "lat/lon: " + userLocation.getLatitude() + "/" + userLocation.getLongitude());
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/location");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("lat", "" + RAPUser.getLocation(mContext).getLatitude()));
		nameValuePairs.add(new BasicNameValuePair("lon", "" + RAPUser.getLocation(mContext).getLongitude()));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	/** 
	 * 나이 정보 전송
	 * @param age 나이
	 * */
	public static HttpRequestBase UserInfo_Age(int age) throws UnsupportedEncodingException{
		
		Log.i(TAG, "사용자 나이정보 API 호출");
		Log.i(TAG, "name: " + RAPUser.getUserId());
		Log.i(TAG, "key: " + RAPSetting.getRAPKey());
		Log.i(TAG, "Age: " + age);
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/age");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("age", "" + age));
		
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		//httpPost.setEntity(entityRequest);
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		return httpPost;
	}
	
	public static HttpRequestBase UserInfo_Age2(int age) throws UnsupportedEncodingException{
		
		Log.i(TAG, "사용자 나이정보 API 호출2");
		Log.i(TAG, "name: " + RAPUser.getUserId());
		Log.i(TAG, "key: " + RAPSetting.getRAPKey());
		Log.i(TAG, "Age: " + age);
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/age");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("age", "" + age));
		
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		//httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		return httpPost;
	}

	/**
	 * OS 정보 전송
	 * */
	public static HttpRequestBase UserInfo_OS() throws UnsupportedEncodingException{
		
		Log.i(TAG, "사용자 OS정보 API 호출");
		Log.i(TAG, "OS: " + RAPUser.getOSVersion());
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/os");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("os_version", "" + RAPUser.getOSVersion()));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	/**
	 * Device 정보 전송
	 * */
	public static HttpRequestBase UserInfo_Device() throws UnsupportedEncodingException{
		
		Log.i(TAG, "사용자 OS정보 API 호출");
		Log.i(TAG, "Device: " + RAPUser.getOSVersion());
		
		HttpPost httpPost = new HttpPost(RAPHttpClient.getBaseURL() + "/APIs/User/device");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", "" + RAPUser.getUserId()));
		nameValuePairs.add(new BasicNameValuePair("key", "" + RAPSetting.getRAPKey()));
		nameValuePairs.add(new BasicNameValuePair("device_vertion", "" + RAPUser.getDeviceVersion()));
		UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		
		return httpPost;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////														////////////////////////
	///////////////////////						카테고리 정보						////////////////////////
	///////////////////////														////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static HttpRequestBase GetCategoryL() {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/getCategoryL?project_key=" + RAPSetting.getRAPKey());
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	public static HttpRequestBase GetCategoryM(String CategoryL) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/getCategoryM?project_key=" + RAPSetting.getRAPKey() + "&CategoryL=" + CategoryL);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	public static HttpRequestBase GetCategoryS(String CategoryL, String CategoryM) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/getCategoryS?project_key=" + RAPSetting.getRAPKey() + "&CategoryL=" + CategoryL  + "&CategoryM=" + CategoryM);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////														////////////////////////
	///////////////////////						IAP 정보							////////////////////////
	///////////////////////														////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static HttpRequestBase getIAP_CategoryL(String CategoryL) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/getIAP_CategoryL?project_key=" + RAPSetting.getRAPKey() + "&CategoryL=" + CategoryL );
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	public static HttpRequestBase getIAP_CategoryM(String CategoryL, String CategoryM) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/getIAP_CategoryM?project_key=" + RAPSetting.getRAPKey() + "&CategoryL=" + CategoryL  + "&CategoryM=" + CategoryM);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	public static HttpRequestBase getIAP_CategoryS(String CategoryL, String CategoryM, String CategoryS) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/getIAP_CategoryS?project_key=" + RAPSetting.getRAPKey() + "&CategoryL=" + CategoryL  + "&CategoryM=" + CategoryM + "&CategoryS=" + CategoryS);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////														////////////////////////
	///////////////////////						Virtual Money 정보				////////////////////////
	///////////////////////														////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  Main 가상화폐의 정보를 조회합니다.
	 * */
	public static HttpRequestBase CheckVirtual_Main() {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/checkVirtualMain?project_key=" + RAPSetting.getRAPKey());
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * Sub 가상화폐의 정보를 조회합니다.
	 * */
	public static HttpRequestBase CheckVirtual_Sub() {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/checkVirtualSub?project_key=" + RAPSetting.getRAPKey());
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 사용자의 Main 가상화폐를 가져옵니다.
	 * */
	public static HttpRequestBase GetVirtual_Main() {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/GetVirtualMain?project_key=" + RAPSetting.getRAPKey() + "&User=" + RAPUser.getUserId());
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 사용자의 Main 가상화폐를 사용합니다
	 * */
	public static HttpRequestBase UseVirtual_Main(int money) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/UseVirtualMain?project_key=" + RAPSetting.getRAPKey()  + "&User=" + RAPUser.getUserId() + "&money=" + money);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 사용자의 Main 가상화폐를 추가합니다
	 * */
	public static HttpRequestBase TakeVirtual_Main(int money) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/TakeVirtualMain?project_key=" + RAPSetting.getRAPKey()  + "&User=" + RAPUser.getUserId() + "&money=" + money);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 사용자의 Sub 가상화폐를 가져옵니다.
	 * */
	public static HttpRequestBase GetVirtual_Sub() {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/GetVirtualSub?project_key=" + RAPSetting.getRAPKey() + RAPSetting.getRAPKey() + "&User=" + RAPUser.getUserId());
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 사용자의 Sub 가상화폐를 사용합니다
	 * 
	 * */
	public static HttpRequestBase UseVirtual_Sub(int money) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/UseVirtualSub?project_key=" + RAPSetting.getRAPKey() + "&User=" + RAPUser.getUserId() + "&money=" + money);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 사용자의 Sub 가상화폐를 추가합니다
	 * 
	 * */
	public static HttpRequestBase TakeVirtual_Sub(int money) {
		String url = (RAPHttpClient.getBaseURL() + "/APIs/TakeVirtualSub?project_key=" + RAPSetting.getRAPKey() + "&User=" + RAPUser.getUserId() + "&money=" + money);
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
}
