package com.example.ch7zy_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class myreceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 提示广播的消息
		Toast.makeText(
				context, String.format("Intent的action是%s，消息内容:%s", intent.getAction(),
						intent.getStringExtra("msg")), Toast.LENGTH_LONG)
				.show();

			
	 
			// 创建一个Bundle对象，并存入数据
			Bundle bu=new Bundle();
			
			// 将信息传入bundle对象
			bu.putString("fn","这是第一个广播");
			// 将bundle放入结果中
			setResultExtras(bu);
			

	}

}
