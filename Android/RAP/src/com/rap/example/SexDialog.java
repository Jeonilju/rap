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
import android.widget.CheckBox;

import com.rap.R;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class SexDialog extends Dialog{

	private Button okBtn;
	
	private CheckBox man_ch;
	private CheckBox woman_ch;
	private Context context;
	
	public SexDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_sex);
		
		initResource();
		initEvent();
	}
	
	private void initResource(){
		okBtn = (Button) findViewById(R.id.input_btn);
		man_ch = (CheckBox) findViewById(R.id.input_man);
		woman_ch = (CheckBox) findViewById(R.id.input_woman);
	}
	
	private void initEvent(){
		okBtn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					
					if(man_ch.isChecked()){
						HttpRequestBase req = RAPAPIs.UserInfo_Sex(true);
						RAPHttpClient.getInstance().background(req, null);
					}
					else if(woman_ch.isChecked()){
						HttpRequestBase req = RAPAPIs.UserInfo_Sex(false);
						RAPHttpClient.getInstance().background(req, null);
					}
					else{
						ShowDig("체크해주세요");
					}
					
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
