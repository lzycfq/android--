package cn.itcast.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver02 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("MyBroadcastReceiver02", "自定义的广播接收者02,接收到了自定义的广播事件");
		Log.i("MyBroadcastReceiver02", "自定义的广播接收者02，接收到了广播事件");
		abortBroadcast(); // 拦截有序广播
		Log.i("MyBroadcastReceiver02", "我是广播接收者02，广播被我终结了");

	}
}
