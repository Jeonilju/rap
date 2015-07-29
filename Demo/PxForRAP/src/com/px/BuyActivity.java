package com.px;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.example.android.trivialdrivesample.util.IabHelper;
import com.px.tool.Preference;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.iap.RAPIapInfo;

public class BuyActivity extends RAPBaseActivity{

	IInAppBillingService mService;
	IabHelper mHelper;
	
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
	
	private int mode = 0;
	
	private static final String TAG = "IAPSampleActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		
		mode = 0;
		
		initResourse();
		initEvent();
		initSpiner();
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
					if(mode == 1){
						String categoryLName = sp_categoryL.getSelectedItem().toString();
						HttpRequestBase req = RAPAPIs.GetCategoryM(categoryLName);
						RAPHttpClient.getInstance().background(req, getCategoryM);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btn_categoryS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if(mode == 2){
						String categoryLName = sp_categoryL.getSelectedItem().toString();
						String categoryMName = sp_categoryM.getSelectedItem().toString();
						HttpRequestBase req = RAPAPIs.GetCategoryS(categoryLName, categoryMName);
						RAPHttpClient.getInstance().background(req, getCategoryS);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btn_Items.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mode == 0){
					try {
						HttpRequestBase req = RAPAPIs.getIAP_AllItems();
						RAPHttpClient.getInstance().background(req, getIAPs);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(mode == 1){
					String categoryLName = sp_categoryL.getSelectedItem().toString();
					
					try {
						HttpRequestBase req = RAPAPIs.getIAP_CategoryL(categoryLName);
						RAPHttpClient.getInstance().background(req, getIAPs);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (mode == 2){
					String categoryLName = sp_categoryL.getSelectedItem().toString();
					String categoryMName = sp_categoryM.getSelectedItem().toString();
					
					try {
						HttpRequestBase req = RAPAPIs.getIAP_CategoryM(categoryLName, categoryMName);
						RAPHttpClient.getInstance().background(req, getIAPs);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
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
			}
		});
		
		lv_items.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Buy("rap.item.shoos001");
				RAPIapInfo item = itemList.get(position);
				ShowItemInfo(item);
			}
		});
	}

	private void initSpiner(){
		sp_categoryL.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(adapterM != null)
					adapterM.clear();
				if(adapterS != null)
					adapterS.clear();
				
				((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
				mode = 1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		sp_categoryM.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(adapterS != null)
					adapterS.clear();
				
				((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
				mode = 2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		sp_categoryS.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
				mode = 3;				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
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
	
	ServiceConnection mServiceConn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = IInAppBillingService.Stub.asInterface(service);
		}
	};
	
	Handler getCategoryL = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(BuyActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(BuyActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(BuyActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(BuyActivity.this, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						JSONArray list = new JSONArray(json.getString("res"));
						Log.i(TAG, "응답: " + json.getString("res"));
						
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
						
						itemAdapter = new IAPAdapter(BuyActivity.this,BuyActivity.this, itemList);
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
		
		String price = "";
		if(item.getType() == 1){
			// Main 화폐 결제
			price = "" + item.getPriceMain() + Preference.getString(BuyActivity.this, Preference.PREF_MAIN); 
		}
		else if (item.getType() == 2){
			// Sub 화폐 결제
			price = "" + item.getPriceSub() + Preference.getString(BuyActivity.this, Preference.PREF_SUB);
		}
		else{
			// Real 화폐 결제
			price = "" + item.getPriceReal() + "원";
		}
		
		String message = "이름: " + item.getName()
				+ "\n대분류: " + item.getCategoryL()
				+ "\n중분류: " + item.getCategoryM()
				+ "\n소분류: " + item.getCategoryS();
		
		message += "\n가격: " + price
				+ "\n\n설명: " + item.getDescription()
				+ ""; 
		
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle(item.getName());
		alert.show();
	}
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(BuyActivity.this);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
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
}
