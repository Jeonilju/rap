package com.px;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.px.tool.BackPressCloseHandler;
import com.px.tool.Preference;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class MainActivity extends RAPBaseActivity {

	private static final String TAG = "MainActivity";
	
	private BackPressCloseHandler backPressCloseHandler;
	
	private Button buy, point, charge, buylist;

	public static int Main_point = 0;
	public static int Sub_point = 0;
	
	public static String mainName = "Main 화폐";
	public static String subName = "Sub 화폐";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//RAPSetting.setGCMProjectId("1065883592772");
		//RAPGcmManager.registerGcm(this);
		initResourse();
		initEvent();

		backPressCloseHandler = new BackPressCloseHandler(this);
	}

	private void initResourse() {
		buy = (Button) findViewById(R.id.main_btn_buy);
		point = (Button) findViewById(R.id.main_btn_point);
		charge = (Button) findViewById(R.id.main_btn_charge);
		buylist = (Button) findViewById(R.id.main_btn_buylist);

		mainName = Preference.getString(MainActivity.this, Preference.PREF_MAIN);
		subName = Preference.getString(MainActivity.this, Preference.PREF_SUB);
	}

	private void initEvent() {
		buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, BuyActivity.class);
				startActivity(intent);
			}
		});
		
		point.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					HttpRequestBase req = RAPAPIs.GetVirtual_Sub();
					RAPHttpClient.getInstance().background(req, getSubMoney);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		charge.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ChargeActivity.class);
				startActivity(intent);
			}
		});
		
		buylist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowDig("사용하지 않는 메뉴입니다.");
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
	
	Handler getSubMoney = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(MainActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						Log.i(TAG, json.getString("res"));
						JSONObject point = new JSONObject(json.getString("res"));
						Sub_point = point.getInt("point");
						
						try {
							HttpRequestBase req2 = RAPAPIs.GetVirtual_Main();
							RAPHttpClient.getInstance().background(req2, getMainMoney);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					default:
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
	};
	
	Handler getMainMoney = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(MainActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONObject point = new JSONObject(json.getString("res"));
						Log.i(TAG, json.getString("res"));
						Main_point = point.getInt("point");
						
						printPoint();
						
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
	
	private void printPoint() {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(MainActivity.this);
		alt_bld.setMessage("현재 보유하고 계신 포인트는 다음과 같습니다. \n\n" 
				+ mainName + ": " + Main_point + "\n"
				+ subName + ": " + Sub_point)
				.setCancelable(false)
				.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle("잔액 조회");
		alert.show();
	}
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(MainActivity.this);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle("안내");
		alert.show();
	}

}