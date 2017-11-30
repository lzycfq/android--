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
		// 注册内容观察者
		resolver.registerContentObserver(uri, true, new MyObserver(
				new Handler()));
	}

	// 自定义的内容观察者
	private class MyObserver extends ContentObserver {
		public MyObserver(Handler handler) {
			super(handler);
		}

		// 当内容观察者 观察到是数据库的内容变化了 调用这个方法
		// 观察到 消息邮箱里面有一条数据库内容变化的通知.
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Toast.makeText(MainActivity.this, "数据库的内容变化了.", 1).show();
			Uri uri = Uri.parse("content://sms/");
			// 获取ContentResolver对象
			ContentResolver resolver = getContentResolver();
			// 查询变化的数据
			Cursor cursor = resolver.query(uri, new String[] { "address",
					"date", "type", "body" }, null, null, null);
			// 因为短信是倒序排列 因此获取最新一条就是第一个
			cursor.moveToFirst();
			String address = cursor.getString(0);
			String body = cursor.getString(3);
			// 更改UI界面
			mSmsTv.setText("短信内容：" + body + "\n" + "短信地址：" + address);
			cursor.close();
		}
	}
}
