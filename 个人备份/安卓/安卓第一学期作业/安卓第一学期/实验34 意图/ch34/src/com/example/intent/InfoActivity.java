package com.example.intent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoActivity extends Activity implements android.view.View.OnClickListener{
	Button btn;
	EditText edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		btn=(Button)findViewById(R.id.btn);
		edit=(EditText)findViewById(R.id.edit);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		Uri uri=Uri.parse("tel:"+edit.getText());
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(uri);
		startActivity(intent);
	}
}
