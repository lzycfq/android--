package cn.itcast.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver03 extends BroadcastReceiver {
    @Override
	public void onReceive(Context context, Intent intent) {
		Log.i("MyBroadcastReceiver03","自定义的广播接收者03,接收到了自定义的广播事件");
	}
}
