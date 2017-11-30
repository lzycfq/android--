package cn.itcast.readsms;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
    //���Buttonʱ�����ķ���
	public void click(View view) {
		// content://sms ��ѯϵͳ���ж��ŵ�uri
		Uri uri = Uri.parse("content://sms/");
         //��ȡContentResolver����
		ContentResolver resolver = getContentResolver();
          //ͨ��ContentResolver�����ѯϵͳ����
		Cursor cursor = resolver.query(uri, new String[] { "address", "date",
				"type", "body" }, null, null, null);
		List<SmsInfo> smsInfos = new ArrayList<SmsInfo>();
		while (cursor.moveToNext()) {
			String address = cursor.getString(0);
			long date = cursor.getLong(1);
			int type = cursor.getInt(2);
			String body = cursor.getString(3);
			SmsInfo smsInfo = new SmsInfo(date, type, body, address);
			smsInfos.add(smsInfo);
		}
		cursor.close();
		SmsUtils.backUpSms(smsInfos, this);
	}
}
