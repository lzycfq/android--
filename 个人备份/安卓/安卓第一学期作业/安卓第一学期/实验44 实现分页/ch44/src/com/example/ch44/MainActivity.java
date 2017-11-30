package com.example.ch44;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	DBhelper dbhelper;
	List<Map<String,Object>> students=new ArrayList<Map<String,Object>>();
	ListView list;
	int pageCount=7;
	int currentPage=1;
	int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new DBhelper(this, null, null, currentPage);
        list=(ListView)findViewById(R.id.list);
        total=getCount();
      ((List<Map<String, Object>>) list).addAll(getDate(currentPage));
        bindDate();
    }
 void bindDate(){
    	SimpleAdapter str=new SimpleAdapter(this, students, android.R.layout.simple_list_item_1,new String[]{"name"},new int[]{android.R.id.text1});
    	 list.setAdapter(str);
 }
List<Map<String,Object>> getDate(int page){
	int start=pageCount*(page-1);
	if(start>=total){
		return null;
	}
	SQLiteDatabase db=dbhelper.getReadableDatabase();
	String sql="select * from students limit"+start+","+pageCount;
	 List<Map<String,Object>> students=new ArrayList<Map<String,Object>>();

	
		 Cursor cursor=db.rawQuery(sql,null);
		
		 while(cursor.moveToNext()){
			 Map<String,Object> data=new HashMap<String, Object>();
			 data.put("sid", cursor.getInt(1));
			 data.put("name", cursor.getString(2));
			students.add(data);
		 }
		 return students;
}
int getCount(){
	int count=0;
	SQLiteDatabase db=dbhelper.getReadableDatabase();
	String sql="select * from students ";
	 Cursor cursor=db.rawQuery(sql,null);
	 if(cursor.moveToNext()){
		 count=cursor.getInt(0);
	 }
	 return count;
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void btnOK(View v){
    	if(currentPage*pageCount>=total){
    		Toast.makeText(this, "no more",Toast.LENGTH_SHORT);
    		return;
    	}
    	else{
    		currentPage++;
    		students.addAll(getDate(currentPage));
    		bindDate();
    	}
    }
}
