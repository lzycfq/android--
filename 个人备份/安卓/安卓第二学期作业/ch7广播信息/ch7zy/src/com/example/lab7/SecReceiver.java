package com.example.lab7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SecReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
        Bundle b=getResultExtras(true);
        String ms=b.getString("ms");
        Toast.makeText(context,String.format("�ӵ�һ��Receiver���յ���Ϣ��:%s", ms) , 0).show();
	}

}
