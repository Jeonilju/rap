package com.px;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
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

import com.android.vending.billing.IInAppBillingService;
import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
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
	
	private static final String TAG = "IAPSampleActivity";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		
		initResourse();
		initEvent();
		
		//initBillingService();
	}
	
//	private void initBillingService() {
//
//		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwgA810Pm1Fcj2DzetUzbXXMZCTC0bDlA56/R/45NO/FZeeOWlNyNBSlBcp8c8v/5xzikJr3s8/3b/V0bjC0fuw+xY5I9y+Y7dAF7VDNbNspT9abT1gAZO2RU/XVi7ZPEriXmoYU7kgoR6y5/31Cx7GWuvEPuR63Fmi17mlJA5gnge1IfXoGLhzom/nro1dlrzv7zqA5N+HcE9GqS0uNDRq9mMeH0HaffUKfus8Pt9xlpo+hUwCegRax2SirjL71YQR1nGRG0D4heT/1wOB5d3Fi8u/LgbTQ0lSG9mbnmipjKy4BdAoNuoM5guzJ4mNbycUGxKf7jW/8vNddxZCbgfQIDAQAB"; // (구글에서 발급받은 바이너리키를 입력해줍니다)
//
//		mHelper = new IabHelper(this, base64EncodedPublicKey);
//		mHelper.enableDebugLogging(true);
//		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
//			public void onIabSetupFinished(IabResult result) {
//				if (!result.isSuccess()) {
//					// 구매오류처리 ( 토스트하나 띄우고 결제팝업 종료시키면 되겠습니다 )
//					Toast.makeText(BuyActivity.this, "결제모듈 초기화에 실패했습니다.", Toast.LENGTH_SHORT).show();
//				}
//
//				AlreadyPurchaseItems();
//				// AlreadyPurchaseItems();
//				// 메서드는 구매목록을 초기화하는 메서드입니다.
//				// v3으로 넘어오면서 구매기록이 모두 남게 되는데 재구매 가능한 상품( 게임에서는 코인같은아이템은 ) 구매후
//				// 삭제해주어야 합니다.
//				// 이 메서드는 상품 구매전 혹은 후에 반드시 호출해야합니다.
//				// ( 재구매가 불가능한 1회성 아이템의경우 호출하면 안됩니다 )
//			}
//		});
//		
//		Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
//		// This is the key line that fixed everything for me
//		intent.setPackage("com.android.vending");
//
//		bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
//		
////		bindService(new Intent(
////				"com.android.vending.billing.InAppBillingService.BIND"),
////				mServiceConn, Context.BIND_AUTO_CREATE);
//	}
//
//	public void Buy(String id_item) {
//		// Var.ind_item = index;
//		try {
//			Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(), id_item, "inapp", "test");
//			PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
//
//			if (pendingIntent != null) {
//				 startIntentSenderForResult(
//						 pendingIntent.getIntentSender(),
//						 1001, new Intent(), Integer.valueOf(0), Integer.valueOf(0),
//						 Integer.valueOf(0));
//			} else {
//				Toast.makeText(BuyActivity.this, "결제에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
//				// 결제가 막혔다면
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		System.out.println("requestCode : " + requestCode);
//		System.out.println("resultCode : " + resultCode);
//		if (requestCode == 1001)
//			if (resultCode == RESULT_OK) {
//				if (!mHelper
//						.handleActivityResult(requestCode, resultCode, data)) {
//					super.onActivityResult(requestCode, resultCode, data);
//
//					int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
//					String purchaseData = data
//							.getStringExtra("INAPP_PURCHASE_DATA");
//					String dataSignature = data
//							.getStringExtra("INAPP_DATA_SIGNATURE");
//
//					// 여기서 아이템 추가 해주시면 됩니다.
//					// 만약 서버로 영수증 체크후에 아이템 추가한다면, 서버로 purchaseData ,
//					// dataSignature 2개 보내시면 됩니다.
//					Toast.makeText(BuyActivity.this, "결제 성공", Toast.LENGTH_SHORT).show();
//				} else {
//					// 구매취소 처리 
//					Toast.makeText(BuyActivity.this, "결제모듈 초기화에 실패했습니다.(2)", Toast.LENGTH_SHORT).show();
//				}
//			} else {
//				// 구매취소 처리
//				Toast.makeText(BuyActivity.this, "결제모듈 초기화에 실패했습니다.(3)", Toast.LENGTH_SHORT).show();
//			}
//		else {
//			// 구매취소 처리
//			Toast.makeText(BuyActivity.this, "결제모듈 초기화에 실패했습니다.(4)", Toast.LENGTH_SHORT).show();
//		}
//	}
	
	public void AlreadyPurchaseItems() {
		try {
			Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
			int response = ownedItems.getInt("RESPONSE_CODE");
			if (response == 0) {
				ArrayList purchaseDataList = ownedItems
					.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
			String[] tokens = new String[purchaseDataList.size()];
			for (int i = 0; i < purchaseDataList.size(); ++i) {
				String purchaseData = (String) purchaseDataList.get(i);
				JSONObject jo = new JSONObject(purchaseData);
				tokens[i] = jo.getString("purchaseToken");
				// 여기서 tokens를 모두 컨슘 해주기
				mService.consumePurchase(3, getPackageName(), tokens[i]);
			}
		}

		// 토큰을 모두 컨슘했으니 구매 메서드 처리
	} catch (Exception e) {
		e.printStackTrace();
	}
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
				//Buy("rap.item.shoos001");
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
}
