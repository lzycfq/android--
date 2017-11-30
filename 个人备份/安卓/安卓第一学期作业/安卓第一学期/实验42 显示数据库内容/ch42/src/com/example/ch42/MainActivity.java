package com.example.ch42;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;



import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
 ListView list;
 DBHepler DB;
 DBHepler dbhelper;
 EditText edit,editb;
 Button btn;
 public void bingdata(){
	 ArrayAdapter<String> str=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
	 list.setAdapter(str);
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHepler db=new DBHepler(this);
        db.getReadableDatabase();
        edit=(EditText)findViewById(R.id.edit);
        editb=(EditText)findViewById(R.id.editb);
        btn=(Button)findViewById(R.id.btn);
        bingdata();
        
    }
    List<Map<String,String>> getdata(){
    	 List<Map<String,String>> map=new ArrayList<Map<String,String>>();
			
		
	 SQLiteDatabase db = DB.getWritableDatabase();
	 ContentValues values = new ContentValues();
	 Cursor cursor = db.query("friend", null, null, null, null, null, null);
	 while(cursor.moveToNext()){
		 Map<String,String> data=new HashMap<String, String>();
		 data.put("name", cursor.getString(0));
		 data.put("title", cursor.getString(0));
		 map.add(data);
	 }
	 return map;
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
    values.put("name", "xxx");
    values.put("µç»°", 4);
    db.insert("friends", null, values);
    db.close();
    long r = db.insert("friends", null, values);
    if(r>0){
   	 Toast.makeText(this, "Ìí¼Ó", 0).show();
    }
}
}
