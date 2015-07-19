package com.rap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * ���� ���¿� ���õ� ������ Receive�Ҷ� ���
 * 
 * �� ������
 * 
 * Manifest�� Receiver�� ����Ŀ� ����� �����ϴ�.
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
