package cn.itcast.orderedbroadcast;

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
		// 发送有序广播
//		sendOrderedBroadcast(intent, null);
		MyBroadcastReceiver03 receiver03 = new MyBroadcastReceiver03();
		sendOrderedBroadcast(intent,null,receiver03, null, 0, null, null);
	}    
}
