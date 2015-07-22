package com.px;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mylist;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initResourse();
		initEvent();
		
	}
	 
	private void initResourse(){
		adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		adapter.add("����");
		adapter.add("��");
		adapter.add("���");
		adapter.add("����");
		adapter.add("���̽�ũ��");
		
		mylist = (ListView) findViewById(R.id.main_list);
		mylist.setAdapter(adapter);
	}
	
	private void initEvent(){
		mylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Intent intent = new Intent(MainActivity.this, CameraActivity.class);
//				intent.putExtra("type", adapterList.get(position).getArea());
//				intent.putExtra("isUsed", adapterList.get(position).isUsed());
//				startActivity(intent);
//				finish();
			}
		});
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
