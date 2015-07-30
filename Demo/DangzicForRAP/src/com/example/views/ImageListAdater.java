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
	
	// ���ڿ��� ���� �� ArrayList
    private ArrayList<ImageInfo>   contentList;
    
    // ������
    public ImageListAdater(Context context, ArrayList<ImageInfo> list) {
    	mContext = context;
        contentList = list;
    }
    
    public void setList(ArrayList<ImageInfo> list){
    	contentList = list;
    	this.notifyDataSetChanged();
    }
 
    // ���� �������� ���� ����
    @Override
    public int getCount() {
        return contentList.size();
    }
 
    // ���� �������� ������Ʈ�� ����, Object�� ��Ȳ�� �°� �����ϰų� ���Ϲ��� ������Ʈ�� ĳ�����ؼ� ���
    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }
 
    // ������ position�� ID �� ����
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    // ��� �� ������ ����
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ����Ʈ�� ������鼭 ���� ȭ�鿡 ������ �ʴ� �������� converView�� null�� ���·� ��� ��
        
    	if ( true ) {
            // view�� null�� ��� Ŀ���� ���̾ƿ��� ��� ��
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_image_row, parent, false);
             
            // TextView�� ���� position�� ���ڿ� �߰�
            TextView text = (TextView) convertView.findViewById(R.id.area_name);
            text.setText(contentList.get(position).getArea());
            
            TextView text2 = (TextView) convertView.findViewById(R.id.is_used);
            if(contentList.get(position).isUsed()){
            	// �̹� ������
            	text2.setText("�Ϸ�");
            	text2.setTextColor(Color.parseColor("#2A4574"));
            }
            else{
            	// �ؾߵ�����
            	text2.setText("�̿Ϸ�");
            	text2.setTextColor(Color.RED);
            }
        }
         
        return convertView;
    }
}
