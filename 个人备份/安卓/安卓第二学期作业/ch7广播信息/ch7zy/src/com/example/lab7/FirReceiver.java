package com.example.lab7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FirReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String ps=intent.getStringExtra("ps");
		Toast.makeText(context,String.format("Intent的action是%s，消息内容:%s", intent.getAction(),ps), 0).show();
		Bundle b = new Bundle();
		b.putString("ms", "lab7 android");
		setResultExtras(b);
	}

}
