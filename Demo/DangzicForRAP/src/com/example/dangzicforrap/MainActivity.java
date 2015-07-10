package com.example.dangzicforrap;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.models.ImageInfo;
import com.example.models.Preference;
import com.example.views.ImageListAdater;

public class MainActivity extends Activity {

	private ListView mylist;
	private ArrayList<ImageInfo> adapterList;
	private ImageListAdater adapter;
	
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
				finish();
			}
		});
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		for(int n=1;n < 36;n++){
			if(Preference.getBoolean(MainActivity.this, "" + n + ".jpg")){
				adapterList.get(n-1).setUsed(true);
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
