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
		
		adapterList.add(new ImageInfo("시너지", false));
		adapterList.add(new ImageInfo("다이나믹", false));
		adapterList.add(new ImageInfo("스마트", false));
		adapterList.add(new ImageInfo("북카페", false));
		adapterList.add(new ImageInfo("복사기", false));
		adapterList.add(new ImageInfo("3층 화장실 (세면대)", false));
		adapterList.add(new ImageInfo("3층 화장실 (핸드타올)", false));
		adapterList.add(new ImageInfo("스마트 TV", false));
		adapterList.add(new ImageInfo("프리룸", false));
		adapterList.add(new ImageInfo("체력단련실 (런닝머신)", false));
		adapterList.add(new ImageInfo("체력단련실 (벤치)", false));
		adapterList.add(new ImageInfo("애니콜", false));
		adapterList.add(new ImageInfo("센스", false));
		adapterList.add(new ImageInfo("지펠", false));
		adapterList.add(new ImageInfo("갤럭시", false));
		adapterList.add(new ImageInfo("에덴", false));
		adapterList.add(new ImageInfo("4층 화상실 (세면대)", false));
		adapterList.add(new ImageInfo("4층 화장실 (핸드타올)", false));
		adapterList.add(new ImageInfo("샤워실 (거울)", false));
		adapterList.add(new ImageInfo("샤워실 (바닥)", false));
		adapterList.add(new ImageInfo("샤워실 (벽면)", false));
		adapterList.add(new ImageInfo("샤워실 (선반)", false));
		adapterList.add(new ImageInfo("샤워실 (세제)", false));
		adapterList.add(new ImageInfo("샤워실 (바구니)", false));
		adapterList.add(new ImageInfo("탕비실 (인덕션)", false));
		adapterList.add(new ImageInfo("탕비실 (싱크대)", false));
		adapterList.add(new ImageInfo("탕비실 (자판기)", false));
		adapterList.add(new ImageInfo("탕비실 (식탁)", false));
		adapterList.add(new ImageInfo("탕비실 (밥솥)", false));
		adapterList.add(new ImageInfo("탕비실 (식기)", false));
		adapterList.add(new ImageInfo("수면실 (매트릭스)", false));
		adapterList.add(new ImageInfo("수면실 (이불 배개)", false));
		adapterList.add(new ImageInfo("수면실 (요)", false));
		adapterList.add(new ImageInfo("수면실 (락커)", false));
		adapterList.add(new ImageInfo("건조실", false));
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
