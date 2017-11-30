package cn.itcast.ipdail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {
    @Override
	public void onReceive(Context context, Intent intent) {
         // ��ȡ����ĵ绰����
		String outcallnumber=getResultData();
         // ����SharedPreferences����,��ȡ�ö����д洢��IP����
		SharedPreferences sp=context.getSharedPreferences(
"config",Context.MODE_PRIVATE);
		String ipnumber=sp.getString("ipnumber","");
         // ��IP������ӵ��Ⲧ�绰��ǰ��
		setResultData(ipnumber+outcallnumber);
	}
}	
