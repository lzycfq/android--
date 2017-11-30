package com.example.chs5_1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText editNumber, editContent;
	//定义SmsManager对象
	SmsManager smsManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取SmsManager对象实例
		smsManager=SmsManager.getDefault();
		editNumber=(EditText)findViewById(R.id.editNumber);
		editContent=(EditText)findViewById(R.id.editContent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendClick(View view){
		//创建PendingIntent对象
		PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
		//发送短信
		smsManager.sendTextMessage(editNumber.getText().toString(), 
				null, editContent.getText().toString(), pi, null);
		
		Toast.makeText(getApplicationContext(), "短信发送成功", Toast.LENGTH_SHORT).show();
	}
}
