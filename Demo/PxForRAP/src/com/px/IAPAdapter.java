package com.px;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rap.iap.RAPIapInfo;

public class IAPAdapter extends BaseAdapter{

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
	public View getView(int position, View convertView, ViewGroup parent) {
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