package com.example.ch43;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener{
DBhelper dbhelper;
List<Map<String,Object>> student;
ListView lstv;
TextView text;
public void bindDate(){
	SimpleAdapter str=new SimpleAdapter(this, student, android.R.layout.simple_list_item_1,new String[]{"name"},new int[]{android.R.id.text1});
	 lstv.setAdapter(str);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBhelper db=new DBhelper(this, null, null, 0);
        db.getReadableDatabase();
        lstv=(ListView)findViewById(R.id.lstv);
        text=(TextView)findViewById(R.id.text);
        bindDate();
    }
    List<Map<String,Object>> getdata(){			
		
	 SQLiteDatabase db = dbhelper.getReadableDatabase();
	 String sql="select * from students";
	 List<Map<String,Object>> students=new ArrayList<Map<String,Object>>();

	// ContentValues values = new ContentValues();
	 Cursor cursor=dbhelper.rawQuery(sql,null);
	 //Cursor cursor = db.query("friend", null, null, null, null, null, null);
	 while(cursor.moveToNext()){
		 Map<String,Object> data=new HashMap<String, Object>();
		 data.put("sid", cursor.getInt(1));
		 data.put("name", cursor.getString(2));
		students.add(data);
	 }
	 return student;
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		// TODO Auto-generated method stub
		Intent in=new Intent(this,MarkActivity.class);
		//in.putExtra("sid", in.
	/*	SQLiteDatabase db = dbhelper.getReadableDatabase();
		 String sql="select * from students";
		 List<Map<String,Object>> students=new ArrayList<Map<String,Object>>();

		// ContentValues values = new ContentValues();
		 Cursor cursor=dbhelper.rawQuery(sql,null);
		 //Cursor cursor = db.query("friend", null, null, null, null, null, null);
		 while(cursor.moveToNext()){
			 Map<String,Object> data=new HashMap<String, Object>();
			 data.put("sid", cursor.getInt(1));
			 data.put("name", cursor.getString(2));*/
		
		 
		
		startActivity(in);
	}
    
	}

