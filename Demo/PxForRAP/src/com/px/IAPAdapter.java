package com.px;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
						RAPHttpClient.getInstance().background(req, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(contentsList.get(position).getType() == 2){
					try {
						HttpRequestBase req = RAPAPIs.BuyItemBySub(contentsList.get(position).getPk());
						RAPHttpClient.getInstance().background(req, null);
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
	
	class ViewHolder {
		
		public ImageView itemImage;
		public TextView name;
		public Button buyBtn;
	}
}
