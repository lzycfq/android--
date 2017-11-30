package cn.itcast.messreceiver;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import cn.itcast.messreceiver.R;

public class MainActivity extends Activity {
	private TextView mSmsTv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSmsTv = (TextView) findViewById(R.id.sms_tv);
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		// ע�����ݹ۲���
		resolver.registerContentObserver(uri, true, new MyObserver(
				new Handler()));
	}

	// �Զ�������ݹ۲���
	private class MyObserver extends ContentObserver {
		public MyObserver(Handler handler) {
			super(handler);
		}

		// �����ݹ۲��� �۲쵽�����ݿ�����ݱ仯�� �����������
		// �۲쵽 ��Ϣ����������һ�����ݿ����ݱ仯��֪ͨ.
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Toast.makeText(MainActivity.this, "���ݿ�����ݱ仯��.", 1).show();
			Uri uri = Uri.parse("content://sms/");
			// ��ȡContentResolver����
			ContentResolver resolver = getContentResolver();
			// ��ѯ�仯������
			Cursor cursor = resolver.query(uri, new String[] { "address",
					"date", "type", "body" }, null, null, null);
			// ��Ϊ�����ǵ������� ��˻�ȡ����һ�����ǵ�һ��
			cursor.moveToFirst();
			String address = cursor.getString(0);
			String body = cursor.getString(3);
			// ����UI����
			mSmsTv.setText("�������ݣ�" + body + "\n" + "���ŵ�ַ��" + address);
			cursor.close();
		}
	}
}
