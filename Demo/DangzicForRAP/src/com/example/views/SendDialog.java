package com.example.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.connector.Sender;
import com.example.dangzicforrap.R;

public class SendDialog extends Dialog implements android.view.View.OnClickListener{

private static final String TAG = "PointUsingListDialog";
	
	private Context mContext;
	
	private Button sendBtn, canselBtn;
	private EditText user1, user2, order;
	private Spinner sp_user1, sp_user2, sp_order;
	
	private ArrayAdapter<String> adapter;
	
	private ProgressDialog dialog;
	
	public SendDialog(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_send);
		
		initResource();
		initEvent();
		init();
	}
	
	private void init(){
		
	}
	
	private void initResource(){
		sendBtn = (Button) findViewById(R.id.send_send_btn);
		canselBtn = (Button) findViewById(R.id.send_cansel_btn);
		
		user1 = (EditText) findViewById(R.id.et_user1);
		user2 = (EditText) findViewById(R.id.et_user2);
		order = (EditText) findViewById(R.id.et_orderer);
		
		sp_user1 = (Spinner) findViewById(R.id.sp_user1);
		sp_user2 = (Spinner) findViewById(R.id.sp_user2);
		sp_order = (Spinner) findViewById(R.id.sp_orderer);
		
		adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item);
		
		adapter.add("21-1기");
		adapter.add("21-2기");
		adapter.add("22-1기");
		adapter.add("22-2기");
		adapter.add("23-1기");
		adapter.add("23-2기");
		adapter.add("24-1기");
		adapter.add("24-2기");
		adapter.add("25-1기");
		adapter.add("25-2기");
		adapter.add("26-1기");
		adapter.add("26-2기");
		adapter.add("27-1기");
		adapter.add("27-2기");
		
		sp_user1.setAdapter(adapter);
		sp_user2.setAdapter(adapter);
		sp_order.setAdapter(adapter);
	}
	
	private void initEvent(){
		sendBtn.setOnClickListener(this);
		canselBtn.setOnClickListener(this);
		
		
	}

	private void SendData(){
		
		String user1_str = "", user2_str = "", order_str = "";
		user1_str = user1.getText().toString();
		user2_str = user2.getText().toString();
		order_str = order.getText().toString();
		
		if(user1_str.equals("") || user2_str.equals("") || order_str.equals("") ){
			ShowDig("항목을 입력해주세요.");
			return;
		}
		else{
			dialog = ProgressDialog.show(mContext, "", "데이터베이스 생성중입니다.\n잠시만 기다려주세요.\n( 최초 1회만 수행합니다. )", true);
			Sender send = new Sender(mContext, CompleteParser);
			send.run();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.send_send_btn:
			
			SendData();
			
			break;
		case R.id.send_cansel_btn:
			dismiss();
			break;
		}		
	}
	
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
		alert.setTitle("안내");
		alert.show();
	}
	
	Handler CompleteParser = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == -1){
				if(dialog != null){
					dialog.dismiss();
				}
			}
			else{
				if(dialog != null)
					dialog.setMessage("전송중입니다.\n잠시만 기다려주세요(" + msg.what + "/" + 35 + ")");
			}
		}
	};
}
