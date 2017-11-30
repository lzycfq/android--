package com.example.ch7zy_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class myreceiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 创建一个Bundle对象
		
				// 解析前一个BroadcastReceiver所存入的key为first的消息
				
				// 提示first的消息
		

		Bundle bundle=getResultExtras(true);
		String first=bundle.getString("first");
		Toast.makeText(context, String.format("从第一个Receiver接收的信息是:%s", first), Toast.LENGTH_LONG).show();
	}

}
