package com.px;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.px.tool.Preference;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class ChargeActivity extends RAPBaseActivity{

	private ListView mainList, subList;
	private Button mainBtn, subBtn;
	
	private ArrayAdapter<String> mainAdapter;
	private ArrayAdapter<String> subAdapter;
	
	public static String mainName = "Main 화폐";
	public static String subName = "Sub 화폐";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		
		initResourse();
		initEvent();
	}

	private void initResourse(){
		mainList = (ListView) findViewById(R.id.charge_list_main);
		subList = (ListView) findViewById(R.id.charge_list_sub);
		
		mainBtn = (Button) findViewById(R.id.charge_btn_main);
		subBtn = (Button) findViewById(R.id.charge_btn_sub);
		
		mainBtn.setText(Preference.getString(this, Preference.PREF_MAIN));
		subBtn.setText(Preference.getString(this, Preference.PREF_SUB));
		
		mainAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		subAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		
		mainName = Preference.getString(ChargeActivity.this, Preference.PREF_MAIN);
		subName = Preference.getString(ChargeActivity.this, Preference.PREF_SUB);
		
		mainAdapter.add("1000 " + mainName);
		mainAdapter.add("2000 " + mainName);
		mainAdapter.add("3000 " + mainName);
		mainAdapter.add("4000 " + mainName);
		mainAdapter.add("5000 " + mainName);
		mainAdapter.add("6000 " + mainName);
		mainAdapter.add("7000 " + mainName);
		mainAdapter.add("8000 " + mainName);
		mainAdapter.add("9000 " + mainName);
		mainAdapter.add("10000 " + mainName);
		
		subAdapter.add("1000 " + subName);
		subAdapter.add("2000 " + subName);
		subAdapter.add("3000 " + subName);
		subAdapter.add("4000 " + subName);
		subAdapter.add("5000 " + subName);
		subAdapter.add("60000 " + subName);
		subAdapter.add("70000 " + subName);
		subAdapter.add("80000 " + subName);
		subAdapter.add("90000 " + subName);
		subAdapter.add("100000 " + subName);
		
		mainList.setAdapter(mainAdapter);
		subList.setAdapter(subAdapter);
	}
	
	private void initEvent(){
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					HttpRequestBase req = RAPAPIs.TakeVirtual_Main((position + 1) * 1000);
					RAPHttpClient.getInstance().background(req, getIAPs);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		subList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					HttpRequestBase req = RAPAPIs.TakeVirtual_Sub((position + 1) * 1000);
					RAPHttpClient.getInstance().background(req, getIAPs);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		mainBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainList.setVisibility(View.VISIBLE);
				subList.setVisibility(View.GONE);
			}
		});

		subBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainList.setVisibility(View.GONE);
				subList.setVisibility(View.VISIBLE);
			}
		});
	}
	
	Handler getIAPs = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(ChargeActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						ShowDig("충전이 완료되었습니다");
						break;
					default:
						ShowDig("충전에 실패했습니다.(" + status + ")");
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	};
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(ChargeActivity.this);
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
