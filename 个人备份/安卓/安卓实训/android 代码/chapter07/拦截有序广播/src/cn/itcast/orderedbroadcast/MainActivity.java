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
		// ����㲥���¼�����
		intent.setAction("www.itcast.cn");
		// ��������㲥
//		sendOrderedBroadcast(intent, null);
		MyBroadcastReceiver03 receiver03 = new MyBroadcastReceiver03();
		sendOrderedBroadcast(intent,null,receiver03, null, 0, null, null);
	}    
}
