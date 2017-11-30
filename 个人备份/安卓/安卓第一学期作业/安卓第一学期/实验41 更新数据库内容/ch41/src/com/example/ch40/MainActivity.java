package com.example.ch40;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	DBHepler dbhelper;
 EditText edit,editb;
 Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=(EditText)findViewById(R.id.edit);
        editb=(EditText)findViewById(R.id.editb);
        btn=(Button)findViewById(R.id.btn);
        DBHepler db=new DBHepler(this);
        db.getReadableDatabase();
       /* btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 SQLiteDatabase db = dbhelper.getWritableDatabase();
				 ContentValues values = new ContentValues();
			     values.put("name", "xxx");
			     values.put("电话", 4);
			     db.insert("friends", null, values);
			     db.close();
			}
			
		});*/
    }

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



public void data(View v){
	 SQLiteDatabase db = dbhelper.getWritableDatabase();
	 ContentValues values = new ContentValues();
	 String name=edit.getText().toString();
	 String num=editb.getText().toString();
     values.put("name", "xxx");
     values.put("num", 4);
     db.insert("friends", null, values);
     db.close();
     long r = db.insert("friends", null, values);
     if(r>0){
    	 Toast.makeText(this, "成功", 0).show();
     }
}
}



