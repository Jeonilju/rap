package com.rap.connect;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class RAPHttpClient {

	//public static final String HOST = "192.168.0.20";
	//public static final String HOST = "192.168.10.250";
	public static final String HOST = "210.118.74.208";
	public static final int PORT = 8080;
	
	private static final String TAG = "RAPHttpClient";
	
	private static RAPHttpClient instance = null;
	
	private HttpClient httpClient = null;
	
	private RAPHttpClient() {
		httpClient =  new DefaultHttpClient();
	}
	
	public static RAPHttpClient getInstance() {
		if (instance == null) {
			instance = new RAPHttpClient();
		}	
		return instance;
	}
	
	public synchronized void background(final HttpRequestBase request, final Handler callback) throws IOException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Message msg = new Message();
					msg.what = 1;

					Bundle data = new Bundle();
					data.putString("res", RAPHttpClient.getInstance().excute(request));
					msg.setData(data);
					
					if(callback != null)
						callback.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					
					if(callback != null)
						callback.sendEmptyMessage(-1);
				}
			}
		}).start();
	}
		
	/**
	 * Server�� ���� ������ �޾����� ȣ���Ѵ�.
	 * getInstence �޼ҵ带 ȣ�������� ������ Handler�� response�� �����͸� �����Ѵ�.
	 * 
	 * ������ Handler���� ������ ���� �����͸� ���� �� �ִ�.
	 * httpStatusCode:  mssage.getData().getInt("httpStatusCode");
	 * JSONArray:  mssage.getData().getString("res");
	 * 
	 * mssage.getData().getString("res")�� �޾Ƽ� new JSONArray(res) ������ ����Ͻø� �˴ϴ�.
	 * 
	 * */
	final ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		@Override
		public String handleResponse(HttpResponse response) {
			String res = null;
			JSONObject result = new JSONObject();
			
			try {
				if(response.getHeaders("Set-Cookie").length > 0)
					result.put("Set-Cookie", response.getHeaders("Set-Cookie")[0].getValue());
				
				result.put("httpStatusCode", response.getStatusLine().getStatusCode());
				StringBuffer sb = new StringBuffer();
				byte[] b = new byte[1024];
				
				long langht = response.getEntity().getContentLength();
				int index;				
				InputStream is = response.getEntity().getContent();
				for(int  n=0;  n < langht; n+=index){
					
					if((index = is.read(b)) > 0)
						sb.append(new String(b, 0, index));
					else
						Log.e(TAG, "read value is " + index);
				}
				
				Log.i(TAG, "res: " + sb.toString());
				result.put("res", sb.toString() );
			} catch (Exception e) {
				Log.e(TAG, "err: " + e.getMessage());
			} finally{
			}
			
			return result.toString();
		}
	};
	
	public String excute(final HttpRequestBase httpRequest) throws IOException {
		return httpClient.execute(httpRequest, responseHandler);
	}
	
	public static final String getBaseURL() {
		return "http://" + HOST + ":" + PORT + "/RAP";
	}
	
	public static void close() {
		instance = null;
	}
	
}
