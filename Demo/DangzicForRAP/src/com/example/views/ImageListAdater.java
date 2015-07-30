package com.example.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangzicforrap.R;
import com.example.models.ImageInfo;

public class ImageListAdater extends BaseAdapter{
	private Context mContext = null;
	
	// 문자열을 보관 할 ArrayList
    private ArrayList<ImageInfo>   contentList;
    
    // 생성자
    public ImageListAdater(Context context, ArrayList<ImageInfo> list) {
    	mContext = context;
        contentList = list;
    }
    
    public void setList(ArrayList<ImageInfo> list){
    	contentList = list;
    	this.notifyDataSetChanged();
    }
 
    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return contentList.size();
    }
 
    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }
 
    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        
    	if ( true ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_image_row, parent, false);
             
            // TextView에 현재 position의 문자열 추가
            TextView text = (TextView) convertView.findViewById(R.id.area_name);
            text.setText(contentList.get(position).getArea());
            
            TextView text2 = (TextView) convertView.findViewById(R.id.is_used);
            if(contentList.get(position).isUsed()){
            	// 이미 한지역
            	text2.setText("완료");
            	text2.setTextColor(Color.parseColor("#2A4574"));
            }
            else{
            	// 해야될지역
            	text2.setText("미완료");
            	text2.setTextColor(Color.RED);
            }
        }
         
        return convertView;
    }
}
