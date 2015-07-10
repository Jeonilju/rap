package com.rap.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpRequestBase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rap.R;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class AgeDialog extends Dialog{

private Button btnWithdraw;
	
	private EditText etInput;
	private Context context;
	
	private OnClickListener listener = null;
	
	public AgeDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_input);
		
		initResource();
		initEvent();
	}
	
	public void setBtnListener(OnClickListener listener){
		this.listener = listener;
	}
	
	private void initResource(){
		btnWithdraw = (Button) findViewById(R.id.input_btn);
		etInput = (EditText) findViewById(R.id.input_et);
	}
	
	private void initEvent(){
		btnWithdraw.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					if(etInput.getText().toString().equals("")){
						ShowDig("값을 입력해주세요");
						return;
					}
					
					HttpRequestBase req = RAPAPIs.UserInfo_Age(Integer.parseInt(etInput.getText().toString()));
					RAPHttpClient.getInstance().background(req, null);
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void ShowDig(String message){
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(context);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("닫기",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle("");
		alert.show();
	}

}
