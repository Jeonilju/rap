package com.rap.connect;

import android.os.Handler;
import android.os.Message;

public class RAPCallback{

	private Handler callback;
	
	public RAPCallback(final CallbackResponse callback) {
		this.callback = new Handler(){
			@Override
			public void handleMessage(Message msg){
				if(msg.what == -1){
					callback.error(null);
				}
				else{
					callback.success(null);
				}
			}
		};
	}
}
