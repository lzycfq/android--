package com.example.ch39;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.Toast;

public class AddRemarkActivity extends Activity {

	private static final String FILE_NAME = "remark.txt";

	EditText editDate;
	EditText editPlace;
	EditText editContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_remark);

		editDate = (EditText) findViewById(R.id.editDate);
		editPlace = (EditText) findViewById(R.id.editPlace);
		editContent = (EditText) findViewById(R.id.editContent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_remark, menu);
		return true;
	}

	public void save(View v) {
		
		//-----根据三个文本框输入的内容，构建Reamrk对象。-----
		try {
			//---定义一个FileOutputStream的对象writer------
			
			ObjectOutputStream objWriter = new ObjectOutputStream(writer);
			objWriter.writeObject(remark);
			objWriter.close();
			Toast.makeText(getBaseContext(), "写入成功", 0).show();
		} catch (Exception e) {
			setResult(RESULT_CANCELED);
			finish();
		}
		//-----设置返回码RESULT_OK;------
		finish();
	}

	public void cancel(View v) {
		//-----设置返回码RESULT_CANCEL;------
		
		finish();
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
