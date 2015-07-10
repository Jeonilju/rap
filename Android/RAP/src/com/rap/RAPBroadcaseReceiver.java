package com.rap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 앱의 상태에 관련된 정보를 Receive할때 사용
 * 
 * 앱 삭제시
 * 
 * Manifest에 Receiver를 등록후에 사용이 가능하다.
 * <receiver android:name="com.rap.RAPBroadcaseReceiver">
	<intent-filter>
		<action android:name="android.intent.action.PACKAGE_REMOVED" />
		<data android:scheme="package" />
	</intent-filter>
</receiver>
 * */
public class RAPBroadcaseReceiver extends BroadcastReceiver {

	private static final String TAG = "RAPBroadcaseReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "intent : ");
		Log.d(TAG, "  action = " + intent.getAction());
		Log.d(TAG, "  data = " + intent.getData());
	}
	
}
