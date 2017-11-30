package com.example.ch40;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends Activity {
    Button btnok;
    EditText editText,editDB,editwt;
    DBHepler DB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		btnok=(Button)findViewById(R.id.btnok);
		editText=(EditText)findViewById(R.id.editText);
		editDB=(EditText)findViewById(R.id.editDB);
		editwt=(EditText)findViewById(R.id.editwt);
		 DBHepler db=new DBHepler(this);
	        db.getReadableDatabase();
	       
	      btnok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = DB.getWritableDatabase();
				 ContentValues values = new ContentValues();
				 String num=editText.getText().toString();
				 String nu=editDB.getText().toString();
				 String	mu= editwt.getText().toString();
			     values.put("编号", "xxx");
			     values.put("地址", 4);
			     values.put("电子邮件", "xx");
			     db.insert("friends", null, values);
			     Long r=db.insert("friends", null, values);
			    	if(r>0){Toast.makeText(getBaseContext(), "添加成功", 0).show();}
			    	db.close();
			    
			    
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message, menu);
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
