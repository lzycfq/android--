package com.example.ch39;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String FILE_NAME = "remark.txt";
	TextView textRemark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textRemark = (TextView) findViewById(R.id.textRemark);
	}

	public void openWrite(View v) {
	
		//-----利用显式Intent打开AddRemarkActivity----
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		super.onActivityResult(requestCode, resultCode, data);
		
		if (/* -----返回码是自己预留的号码*/) {
			try {
				
				//-----定义一个FileInputStream的对象名为reader----

				ObjectInputStream objReader = new ObjectInputStream(reader);
				Remark remark = (Remark) objReader.readObject();
				String s = String.format("时间:%s \n地点:%s \n内容:%s",
						remark.getDate(), remark.getPlace(),
						remark.getContent());

				textRemark.setText(s);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
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
}
