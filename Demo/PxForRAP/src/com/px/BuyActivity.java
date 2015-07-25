package com.px;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.iap.RAPIapInfo;

public class BuyActivity extends RAPBaseActivity{

	private Button btn_categoryL, btn_categoryM, btn_categoryS, btn_Items;
	private Spinner sp_categoryL, sp_categoryM, sp_categoryS;
	private ListView lv_items;

	private IAPAdapter itemAdapter;
	private ArrayList<RAPIapInfo> itemList;
	
	private ArrayList<String> categoryLList;
	private ArrayList<String> categoryMList;
	private ArrayList<String> categorySList;
	
	private ArrayAdapter<String> adapterL; 
	private ArrayAdapter<String> adapterM;
	private ArrayAdapter<String> adapterS;
	
	private static final String TAG = "IAPSampleActivity";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		
		initResourse();
		initEvent();
	}
	
	private void initResourse(){
		
		btn_categoryL = (Button) findViewById(R.id.iap_btn1);
		btn_categoryM = (Button) findViewById(R.id.iap_btn2);
		btn_categoryS = (Button) findViewById(R.id.iap_btn3);
		btn_Items = (Button) findViewById(R.id.iap_btn_getItems);
		
		sp_categoryL = (Spinner) findViewById(R.id.iap_sp1);
		sp_categoryM = (Spinner) findViewById(R.id.iap_sp2);
		sp_categoryS = (Spinner) findViewById(R.id.iap_sp3);
		
		lv_items = (ListView) findViewById(R.id.iap_list);
		
		categoryLList = new ArrayList<String>();
		categoryMList = new ArrayList<String>();
		categorySList = new ArrayList<String>();
	}
	
	private void initEvent(){
		btn_categoryL.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try {
					HttpRequestBase req = RAPAPIs.GetCategoryL();
					RAPHttpClient.getInstance().background(req, getCategoryL);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btn_categoryM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String categoryLName = sp_categoryL.getSelectedItem().toString();
					HttpRequestBase req = RAPAPIs.GetCategoryM(categoryLName);
					RAPHttpClient.getInstance().background(req, getCategoryM);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btn_categoryS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String categoryLName = sp_categoryL.getSelectedItem().toString();
					String categoryMName = sp_categoryM.getSelectedItem().toString();
					HttpRequestBase req = RAPAPIs.GetCategoryS(categoryLName, categoryMName);
					RAPHttpClient.getInstance().background(req, getCategoryS);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btn_Items.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String categoryLName = sp_categoryL.getSelectedItem().toString();
				String categoryMName = sp_categoryM.getSelectedItem().toString();
				String categorySName = sp_categoryS.getSelectedItem().toString();
				
				try {
					HttpRequestBase req = RAPAPIs.getIAP_CategoryS(categoryLName, categoryMName, categorySName);
					RAPHttpClient.getInstance().background(req, getIAPs);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		lv_items.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				RAPIapInfo item = itemList.get(position);
				ShowItemInfo(item);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	Handler getCategoryL = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(BuyActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONArray list = new JSONArray(json.getString("res"));
						
						categoryLList.clear();
						for(int n=0;n < list.length();n++){
							categoryLList.add(list.getJSONObject(n).getString("categoryL"));
						}
						adapterL = new ArrayAdapter<String>(BuyActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryLList);
						sp_categoryL.setAdapter(adapterL);
						
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
	
	Handler getCategoryM = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(BuyActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONArray list = new JSONArray(json.getString("res"));
						
						categoryMList.clear();
						for(int n=0;n < list.length();n++){
							categoryMList.add(list.getJSONObject(n).getString("categoryM"));
						}
						
						adapterM = new ArrayAdapter<String>(BuyActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryMList);
						sp_categoryM.setAdapter(adapterM);
						
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
	Handler getCategoryS = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(BuyActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONArray list = new JSONArray(json.getString("res"));
						
						categorySList.clear();
						for(int n=0;n < list.length();n++){
							categorySList.add(list.getJSONObject(n).getString("categoryS"));
						}
						
						adapterS = new ArrayAdapter<String>(BuyActivity.this, android.R.layout.simple_spinner_dropdown_item, categorySList);
						sp_categoryS.setAdapter(adapterS);
						
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
	Handler getIAPs = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(BuyActivity.this, "���� ���� \n����Ŀ� �ٽ� �õ����ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONArray list = new JSONArray(json.getString("res"));
						Log.i(TAG, "����: " + json.getString("res"));
						
						itemList = new ArrayList<RAPIapInfo>();
						for(int n=0;n < list.length();n++){
							
							//int pk, String Name, int PriceReal, int PriceMain, int PriceSub, int type, String google_id, String imagePath,
							//String categoryL, String categoryM, String categoryS
							itemList.add(new RAPIapInfo(
									list.getJSONObject(n).getInt("pk"),
									list.getJSONObject(n).getString("name"),
									list.getJSONObject(n).getInt("price_real"),
									list.getJSONObject(n).getInt("price_main"),
									list.getJSONObject(n).getInt("price_sub"),
									list.getJSONObject(n).getInt("using_type"),
									list.getJSONObject(n).getString("google_id"),
									list.getJSONObject(n).getString("imagePath"),
									list.getJSONObject(n).getString("categoryl"),
									list.getJSONObject(n).getString("categorym"),
									list.getJSONObject(n).getString("categorys"),
									list.getJSONObject(n).getString("description")
									));
						}
						
						itemAdapter = new IAPAdapter(BuyActivity.this, itemList);
						lv_items.setAdapter(itemAdapter);
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

	private void ShowItemInfo(RAPIapInfo item) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(BuyActivity.this);
		alt_bld.setMessage("�̸�: " + item.getName()
				+ "\n��з�: " + item.getCategoryL()
				+ "\n�ߺз�: " + item.getCategoryM()
				+ "\n�Һз�: " + item.getCategoryS()
				+ "\n\n����: " + item.getDescription()
				+ "")
				.setCancelable(false)
				.setPositiveButton("Ȯ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle(item.getName());
		alert.show();
	}
}
