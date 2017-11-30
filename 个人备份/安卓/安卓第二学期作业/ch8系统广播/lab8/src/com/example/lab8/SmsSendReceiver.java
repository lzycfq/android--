package com.example.lab8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SmsSendReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent i = new Intent(context, SmsSendService.class);
		context.startService(i);
         Log.i("SmsSendReceiver", "SMS·þÎñ×¢²á");
	}

}
