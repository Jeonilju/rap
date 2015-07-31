package com.example.dangzicforrap;

import com.example.models.Preference;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{

	private Button loginBtn, qndBtn;
	
	private EditText emailEt, IpEt;
	
	private BackPressCloseHandler backPressCloseHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initResourse();
		initEvent();
		
		backPressCloseHandler = new BackPressCloseHandler(this);
	}
	
	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
	
	private void initResourse(){
		loginBtn = (Button) findViewById(R.id.login_btn);
		qndBtn = (Button) findViewById(R.id.login_qna);
		
		emailEt = (EditText) findViewById(R.id.login_id_edt);
		IpEt = (EditText) findViewById(R.id.login_pw_edt);
		
		emailEt.setText(Preference.getString(LoginActivity.this, "SAVED_EMAIL"));
	}
	
	private void initEvent(){
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String emailStr = emailEt.getText().toString();
				String ipStr = IpEt.getText().toString();
				
				if(emailStr.equals("")){
					ShowDig("당직일지를 받으실 메일주소를 입력해주세요.");
					return;
				}
				else{
					if(ipStr.equals("")){
						ipStr = "192.168.219.125";
					}
					Preference.putString(LoginActivity.this, "SAVED_IP", ipStr);
					Preference.putString(LoginActivity.this, "SAVED_EMAIL", emailStr);
					
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
				}
			}
		});
		
		qndBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowDig("사용방법을 적어주어야지...ㅋ");
			}
		});
	}
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(LoginActivity.this);
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
}
