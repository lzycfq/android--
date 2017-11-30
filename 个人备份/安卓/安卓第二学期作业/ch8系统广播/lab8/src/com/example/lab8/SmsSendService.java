package com.example.lab8;

import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

public class SmsSendService extends Service {
    SmsManager smsManager;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		smsManager=SmsManager.getDefault();
		Log.i("SMSService", "创建后台服务");
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0,new Intent(), 0);
				smsManager.sendTextMessage("5556", null, "check", pi, null);
			}
		}, 0,60000);
	}

}
