package com.example.dangzicforrap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.models.ImageInfo;
import com.example.models.Preference;
import com.example.views.ImageListAdater;
import com.example.views.SendDialog;

public class MainActivity extends Activity {

	private ListView mylist;
	private ArrayList<ImageInfo> adapterList;
	private ImageListAdater adapter;
	
	private Button resetBtn, sendBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initResourse();
		initEvent();
	}

	private void initResourse(){
		adapterList = new ArrayList<ImageInfo>();
		
		adapterList.add(new ImageInfo("�ó���", false));
		adapterList.add(new ImageInfo("���̳���", false));
		adapterList.add(new ImageInfo("����Ʈ", false));
		adapterList.add(new ImageInfo("��ī��", false));
		adapterList.add(new ImageInfo("�����", false));
		adapterList.add(new ImageInfo("3�� ȭ��� (�����)", false));
		adapterList.add(new ImageInfo("3�� ȭ��� (�ڵ�Ÿ��)", false));
		adapterList.add(new ImageInfo("����Ʈ TV", false));
		adapterList.add(new ImageInfo("������", false));
		adapterList.add(new ImageInfo("ü�´ܷý� (���׸ӽ�)", false));
		adapterList.add(new ImageInfo("ü�´ܷý� (��ġ)", false));
		adapterList.add(new ImageInfo("�ִ���", false));
		adapterList.add(new ImageInfo("����", false));
		adapterList.add(new ImageInfo("����", false));
		adapterList.add(new ImageInfo("������", false));
		adapterList.add(new ImageInfo("����", false));
		adapterList.add(new ImageInfo("4�� ȭ��� (�����)", false));
		adapterList.add(new ImageInfo("4�� ȭ��� (�ڵ�Ÿ��)", false));
		adapterList.add(new ImageInfo("������ (�ſ�)", false));
		adapterList.add(new ImageInfo("������ (�ٴ�)", false));
		adapterList.add(new ImageInfo("������ (����)", false));
		adapterList.add(new ImageInfo("������ (����)", false));
		adapterList.add(new ImageInfo("������ (����)", false));
		adapterList.add(new ImageInfo("������ (�ٱ���)", false));
		adapterList.add(new ImageInfo("����� (�δ���)", false));
		adapterList.add(new ImageInfo("����� (��ũ��)", false));
		adapterList.add(new ImageInfo("����� (���Ǳ�)", false));
		adapterList.add(new ImageInfo("����� (��Ź)", false));
		adapterList.add(new ImageInfo("����� (���)", false));
		adapterList.add(new ImageInfo("����� (�ı�)", false));
		adapterList.add(new ImageInfo("����� (��Ʈ����)", false));
		adapterList.add(new ImageInfo("����� (�̺� �谳)", false));
		adapterList.add(new ImageInfo("����� (��)", false));
		adapterList.add(new ImageInfo("����� (��Ŀ)", false));
		adapterList.add(new ImageInfo("������", false));
		adapterList.add(new ImageInfo("Best", true));
		adapterList.add(new ImageInfo("Worst", true));

		adapter = new ImageListAdater(MainActivity.this, adapterList);
		mylist = (ListView) findViewById(R.id.main_list);
		mylist.setAdapter(adapter);
		
		resetBtn = (Button) findViewById(R.id.main_reset);
		sendBtn = (Button) findViewById(R.id.main_send);
	}
	
	private void initEvent(){
		mylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, CameraActivity.class);
				intent.putExtra("type", adapterList.get(position).getArea());
				intent.putExtra("isUsed", adapterList.get(position).isUsed());
				startActivity(intent);
			}
		});
		
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for(int n=1;n < 36;n++){
					Preference.putBoolean(MainActivity.this, "" + n + "_.jpg", false);
				}
				ShowDig("�ʱ�ȭ�Ǿ����ϴ�.");
			}
		});
		
		sendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SendDialog dig = new SendDialog(MainActivity.this);
				dig.show();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		for(int n=1;n < 36;n++){
			if(Preference.getBoolean(MainActivity.this, "" + n + "_.jpg")){
				adapterList.get(n-1).setUsed(true);
			}
		}
		
	}
	
	private void ShowDig(String message) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(MainActivity.this);
		alt_bld.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("Ȯ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						});

		AlertDialog alert = alt_bld.create();
		alert.setTitle("�ȳ�");
		alert.show();
	}
}
