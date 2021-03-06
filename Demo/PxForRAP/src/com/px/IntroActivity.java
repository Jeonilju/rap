package com.px;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.px.tool.Preference;
import com.rap.RAPSetting;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class IntroActivity extends RAPBaseActivity{

	private static final String TAG = "IntroActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		RAPSetting.setRAPKey("1");
		
		try {
			HttpRequestBase req = RAPAPIs.CheckVirtual_Main();
			RAPHttpClient.getInstance().background(req, getMainHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		//Handler h = new Handler();		
		//h.postDelayed(nextIntro, 100);
		
		//Handler h2 = new Handler();		
		//h2.postDelayed(moveNext, 100*2);
	}

	Runnable nextIntro = new Runnable() {
		public void run() {
			//setContentView(R.layout.activity_intro2);	// �ι��� ȭ��
		}
	};
	
	Runnable moveNext = new Runnable() {
		public void run() {
			if(!Preference.getBoolean(IntroActivity.this, Preference.isFirst)){
				// ó�� �α���
				Preference.putBoolean(IntroActivity.this, Preference.isFirst, true);
				ShowDig("��ο� ������ ���Ű��� ȯ���մϴ�.\nó�� ���� �����в� 10000" + Preference.getString(IntroActivity.this, Preference.PREF_MAIN)
						+ "�� 10000" + Preference.getString(IntroActivity.this, Preference.PREF_SUB) + "�� �帳�ϴ�.");
				
				try {
					HttpRequestBase req = RAPAPIs.TakeVirtual_Sub(10000);
					RAPHttpClient.getInstance().background(req, takeCallback);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				Intent intent= new Intent(IntroActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
	};
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(IntroActivity.this);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("Ȯ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent= new Intent(IntroActivity.this, MainActivity.class);
								startActivity(intent);
								finish();
							}
						});

		AlertDialog alert = alt_bld.create();

		alert.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				finish();
			}
		});

		alert.setTitle("");
		alert.show();
	}

	Handler getMainHandler = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(IntroActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONObject list = new JSONObject(json.getString("res"));
						Log.i(TAG, "����: " + json.getString("res"));
						
						JSONObject mainInfo = new JSONObject(json.getString("res"));
						Preference.putString(IntroActivity.this, Preference.PREF_MAIN, mainInfo.getString("name"));
						
						try {
							HttpRequestBase req = RAPAPIs.CheckVirtual_Sub();
							RAPHttpClient.getInstance().background(req, getSubHandler);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						break;
					default:
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	};
	
	Handler getSubHandler = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(IntroActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONObject list = new JSONObject(json.getString("res"));
						Log.i(TAG, "����: " + json.getString("res"));
						
						JSONObject subInfo = new JSONObject(json.getString("res"));
						Preference.putString(IntroActivity.this, Preference.PREF_SUB, subInfo.getString("name"));
						
						Handler h2 = new Handler();		
						h2.postDelayed(moveNext, 100);
						break;
					default:
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	};
	
	
	Handler takeCallback = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(IntroActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						try {
							HttpRequestBase req = RAPAPIs.TakeVirtual_Main(10000);
							RAPHttpClient.getInstance().background(req, takeCallback2);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
	};
	
	Handler takeCallback2 = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(IntroActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						Intent intent= new Intent(IntroActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
						break;
					default:
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
	};
	
}
