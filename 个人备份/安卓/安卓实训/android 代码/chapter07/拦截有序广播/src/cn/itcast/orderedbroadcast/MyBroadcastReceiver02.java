package cn.itcast.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver02 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("MyBroadcastReceiver02", "�Զ���Ĺ㲥������02,���յ����Զ���Ĺ㲥�¼�");
		Log.i("MyBroadcastReceiver02", "�Զ���Ĺ㲥������02�����յ��˹㲥�¼�");
		abortBroadcast(); // ��������㲥
		Log.i("MyBroadcastReceiver02", "���ǹ㲥������02���㲥�����ս���");

	}
}
