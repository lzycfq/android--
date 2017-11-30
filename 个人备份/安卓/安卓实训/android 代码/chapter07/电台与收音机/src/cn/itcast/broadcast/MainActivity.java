package cn.itcast.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void send(View view) {
		Intent intent = new Intent();
		// 定义广播的事件类型
		intent.setAction("www.itcast.cn");
		// 发送广播
		sendBroadcast(intent);
	}
}
