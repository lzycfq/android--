package cn.itcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
	public void onReceive(Context context, Intent intent) {
	    Log.i("MyBroadcastReceiver", "�Զ���Ĺ㲥������,���յ��˹㲥�¼�");
	    Log.i("MyBroadcastReceiver",intent.getAction());
	}
}
