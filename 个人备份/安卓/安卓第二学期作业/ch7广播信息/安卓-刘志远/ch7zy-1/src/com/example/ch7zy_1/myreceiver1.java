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
		// ����һ��Bundle����
		
				// ����ǰһ��BroadcastReceiver�������keyΪfirst����Ϣ
				
				// ��ʾfirst����Ϣ
		

		Bundle bundle=getResultExtras(true);
		String first=bundle.getString("first");
		Toast.makeText(context, String.format("�ӵ�һ��Receiver���յ���Ϣ��:%s", first), Toast.LENGTH_LONG).show();
	}

}
