package cn.itcast.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver03 extends BroadcastReceiver {
    @Override
	public void onReceive(Context context, Intent intent) {
		Log.i("MyBroadcastReceiver03","�Զ���Ĺ㲥������03,���յ����Զ���Ĺ㲥�¼�");
	}
}
