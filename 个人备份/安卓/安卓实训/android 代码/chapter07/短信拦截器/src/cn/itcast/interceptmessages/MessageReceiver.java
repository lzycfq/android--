package cn.itcast.interceptmessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		// 获取所有的短信数据
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for (Object obj : objs) {
			// 将Pdu中的对象转化成SmsMessage对象
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			Log.i("MessageReceiver", "body:" + body);
			Log.i("MessageReceiver", "sender:" + sender);
			if ("15555215556".equals(sender)) {
				Log.i("MessageReceiver", "垃圾短信,立刻终止");
				abortBroadcast();
			}
		}
	}
}
