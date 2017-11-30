package com.example.ch43;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MarkActivity extends Activity {
	ListView list;
	TextView TEXT;
	String sid;
	String name; 
	DBhelper  dbhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mark);
		list=(ListView)findViewById(R.id.list);
		TEXT=(TextView)findViewById(R.id.TEXT);
		sid=getIntent().getStringExtra("name");
		dbhelper=new DBhelper(this, name, null, 0);
		TEXT.setText(name+"³É¼¨");
		bindDate();
		
			
		}
	List<Map<String,Object>> getDate(){
			SQLiteDatabase db=dbhelper.getReadableDatabase();
			String sql="select subject,mark from marks where sid='''+sid+" ;
			List<Map<String,Object>> marks=new ArrayList<Map<String,Object>>();
			Cursor cu=db.rawQuery(sql, null);
			while(cu.moveToNext()){
				Map<String,Object> mark=new HashMap<String,Object>();
				mark.put("sbject",cu.getString(0));
				mark.put("mrak",cu.getInt(1));
			marks.add(mark);
			}
			return marks;
	}
	void bindDate(){
		SimpleAdapter adapter=new SimpleAdapter
				(this,getDate(),android.R.layout.simple_list_item_2,new String[]{"mark","subject"}, new int[]{android.R.id.text1,android.R.id.text2});
		list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mark, menu);
		return true;
	}

}
