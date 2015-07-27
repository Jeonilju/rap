package com.px;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
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

import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;
import com.rap.iap.RAPIapInfo;

public class IAPAdapter extends BaseAdapter{

	private static final String TAG = "IAPAdapter";
	
	public ArrayList<RAPIapInfo> contentsList = null;
	private LayoutInflater inflater = null;
	private ViewHolder viewHolder = null;
	private Context mContext = null;
	
	public IAPAdapter(Context context, ArrayList<RAPIapInfo> list) {
		super();
		mContext = context;
		this.inflater = LayoutInflater.from(mContext);
		contentsList = list;
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
//					try {
//						HttpRequestBase req = RAPAPIs.BuyItemByMain(contentsList.get(position).getPk());
//						RAPHttpClient.getInstance().background(req, null);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
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
