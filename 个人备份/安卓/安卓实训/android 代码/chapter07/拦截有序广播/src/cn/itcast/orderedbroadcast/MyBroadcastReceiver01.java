package cn.itcast.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver01 extends BroadcastReceiver {
    @Override
	public void onReceive(Context context, Intent intent) {
		Log.i("MyBroadcastReceiver01","自定义的广播接收者01,接收到了自定义的广播事件");
	}
}
