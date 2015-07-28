package com.px;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Purchase;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.iap.RAPIapInfo;

public class IAPAdapter extends BaseAdapter{

	private static final String TAG = "IAPAdapter";
	
	IInAppBillingService mService;
	IabHelper mHelper;
	
	public ArrayList<RAPIapInfo> contentsList = null;
	private LayoutInflater inflater = null;
	private ViewHolder viewHolder = null;
	private Context mContext = null;
	private Activity mActivity = null;
	
	public IAPAdapter(Activity mActivity, Context context, ArrayList<RAPIapInfo> list) {
		super();
		mContext = context;
		this.mActivity = mActivity;
		this.inflater = LayoutInflater.from(mContext);
		contentsList = list;
		
		initBillingService();
	}

	private void initBillingService() {

		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwgA810Pm1Fcj2DzetUzbXXMZCTC0bDlA56/R/45NO/FZeeOWlNyNBSlBcp8c8v/5xzikJr3s8/3b/V0bjC0fuw+xY5I9y+Y7dAF7VDNbNspT9abT1gAZO2RU/XVi7ZPEriXmoYU7kgoR6y5/31Cx7GWuvEPuR63Fmi17mlJA5gnge1IfXoGLhzom/nro1dlrzv7zqA5N+HcE9GqS0uNDRq9mMeH0HaffUKfus8Pt9xlpo+hUwCegRax2SirjL71YQR1nGRG0D4heT/1wOB5d3Fi8u/LgbTQ0lSG9mbnmipjKy4BdAoNuoM5guzJ4mNbycUGxKf7jW/8vNddxZCbgfQIDAQAB"; // (구글에서 발급받은 바이너리키를 입력해줍니다)

		mHelper = new IabHelper(mContext, base64EncodedPublicKey);
		mHelper.enableDebugLogging(true);
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				if (!result.isSuccess()) {
					// 구매오류처리 ( 토스트하나 띄우고 결제팝업 종료시키면 되겠습니다 )
					Toast.makeText(mContext, "결제모듈 초기화에 실패했습니다.", Toast.LENGTH_SHORT).show();
				}

				AlreadyPurchaseItems();
				// AlreadyPurchaseItems();
				// 메서드는 구매목록을 초기화하는 메서드입니다.
				// v3으로 넘어오면서 구매기록이 모두 남게 되는데 재구매 가능한 상품( 게임에서는 코인같은아이템은 ) 구매후
				// 삭제해주어야 합니다.
				// 이 메서드는 상품 구매전 혹은 후에 반드시 호출해야합니다.
				// ( 재구매가 불가능한 1회성 아이템의경우 호출하면 안됩니다 )
			}
		});
		
		Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
		// This is the key line that fixed everything for me
		intent.setPackage("com.android.vending");

		mContext.bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
		
//		bindService(new Intent(
//				"com.android.vending.billing.InAppBillingService.BIND"),
//				mServiceConn, Context.BIND_AUTO_CREATE);
	}

	public void Buy(String id_item) {
		// Var.ind_item = index;
		try {
			Bundle buyIntentBundle = mService.getBuyIntent(3, mContext.getPackageName(), id_item, "inapp", "test");
			PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");

			if (pendingIntent != null) {
				mHelper.launchPurchaseFlow(mActivity, mContext.getPackageName(), 1001,  mPurchaseFinishedListener, "test");
			} else {
				Toast.makeText(mContext, "결제에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
				// 결제가 막혔다면
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener  = new IabHelper.OnIabPurchaseFinishedListener() {
		   public void onIabPurchaseFinished(IabResult result, Purchase purchase) 
		   {
			   // 여기서 아이템 추가 해주시면 됩니다.
			   // 만약 서버로 영수증 체크후에 아이템 추가한다면, 서버로 purchase.getOriginalJson() , purchase.getSignature() 2개 보내시면 됩니다.
		   }
		};
	
	public void AlreadyPurchaseItems() {
		try {
			Bundle ownedItems = mService.getPurchases(3, mContext.getPackageName(), "inapp", null);
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
				mService.consumePurchase(3, mContext.getPackageName(), tokens[i]);
			}
		}

		// 토큰을 모두 컨슘했으니 구매 메서드 처리
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
	public void refresh() {
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return contentsList.size();
	}

	@Override
	public RAPIapInfo getItem(int position) {
		return contentsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;

		view = inflater.inflate(R.layout.adapter_buy_row, parent, false);                        
		viewHolder = new ViewHolder();
		viewHolder.itemImage = (ImageView) view.findViewById(R.id.buy_row_image);
		viewHolder.name = (TextView) view.findViewById(R.id.buy_row_name);
		viewHolder.buyBtn = (Button) view.findViewById(R.id.buy_row_btn);
		
		
		//viewHolder.itemImage.setImageBitmap(bm);
		viewHolder.name.setText("" + contentsList.get(position).getName());
		viewHolder.buyBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 아이템 결제
				Log.i(TAG, "아이템 결제하면 되는부분: " + contentsList.get(position).getName());
				
				if(contentsList.get(position).getType() == 1){
					try {
						HttpRequestBase req = RAPAPIs.BuyItemByMain(contentsList.get(position).getPk());
						RAPHttpClient.getInstance().background(req, buyItemCallback);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(contentsList.get(position).getType() == 2){
					try {
						HttpRequestBase req = RAPAPIs.BuyItemBySub(contentsList.get(position).getPk());
						RAPHttpClient.getInstance().background(req, buyItemCallback);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(contentsList.get(position).getType() == 3){
					Buy(contentsList.get(position).getGoogle_id());
				}
			}
		});
		
		
		view.setTag(viewHolder); // For recycle

		return view;
	}
	
	Handler buyItemCallback = new Handler(){

		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1) {
				Toast.makeText(mContext, "연결 실패 \n잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
			}
			else{
				int status;
				try {
					JSONObject json = new JSONObject(msg.getData().getString("res"));
					status = json.getInt("httpStatusCode");
					
					switch (status) {
					case 200:
						ShowDig("구매가 완료되었습니다.");
						break;
					default:
						ShowDig("구매에 실패하셨습니다.(" + status + ")");
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	};
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(mContext);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle("결제정보");
		alert.show();
	}
	
	class ViewHolder {
		
		public ImageView itemImage;
		public TextView name;
		public Button buyBtn;
	}
}
