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
		// ��ʾ�㲥����Ϣ
		Toast.makeText(
				context, String.format("Intent��action��%s����Ϣ����:%s", intent.getAction(),
						intent.getStringExtra("msg")), Toast.LENGTH_LONG)
				.show();

			
	 
			// ����һ��Bundle���󣬲���������
			Bundle bu=new Bundle();
			
			// ����Ϣ����bundle����
			bu.putString("fn","���ǵ�һ���㲥");
			// ��bundle��������
			setResultExtras(bu);
			

	}

}
