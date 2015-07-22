package com.px;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.px.tool.Preference;
import com.rap.activity.RAPBaseActivity;
import com.rap.connect.RAPAPIs;
import com.rap.connect.RAPHttpClient;

public class IntroActivity extends RAPBaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Handler h = new Handler();		
		h.postDelayed(nextIntro, 100);
		
		Handler h2 = new Handler();		
		h2.postDelayed(moveNext, 100*2);
	}

	Runnable nextIntro = new Runnable() {
		public void run() {
			//setContentView(R.layout.activity_intro2);	// �ι��� ȭ��
		}
	};
	
	Runnable moveNext = new Runnable() {
		public void run() {
			if(!Preference.getBoolean(IntroActivity.this, Preference.isFirst)){
				// ó�� �α���
				Preference.putBoolean(IntroActivity.this, Preference.isFirst, true);
				ShowDig("��ο� ������ ���Ű��� ȯ���մϴ�.");
				
				try {
					HttpRequestBase req = RAPAPIs.TakeVirtual_Sub(10000);
					RAPHttpClient.getInstance().background(req, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					HttpRequestBase req = RAPAPIs.TakeVirtual_Main(10000);
					RAPHttpClient.getInstance().background(req, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				Intent intent= new Intent(IntroActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
	};
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(IntroActivity.this);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("Ȯ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent= new Intent(IntroActivity.this, MainActivity.class);
								startActivity(intent);
								finish();
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
