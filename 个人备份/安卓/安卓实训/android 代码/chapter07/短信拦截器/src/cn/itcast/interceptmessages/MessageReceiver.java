package cn.itcast.interceptmessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		// ��ȡ���еĶ�������
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for (Object obj : objs) {
			// ��Pdu�еĶ���ת����SmsMessage����
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			Log.i("MessageReceiver", "body:" + body);
			Log.i("MessageReceiver", "sender:" + sender);
			if ("15555215556".equals(sender)) {
				Log.i("MessageReceiver", "��������,������ֹ");
				abortBroadcast();
			}
		}
	}
}
