package com.example.xuesheng;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHepler extends SQLiteOpenHelper {
	private static final String name = "Student.db"; 
	private static final int version = 2;
	public DBHepler(Context context) {
		super(context,name, null, version);

	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS kechengbiao (kechengID integer primary key autoincrement, kname varchar(20), classroom varchar(20), tname varchar(20),jie varchar(20),week varchar(20),time varchar(20))");
		//周一
		 ContentValues values=new ContentValues();
	        values.put("kname", "移动应用高级开发技术");
	        values.put("classroom", "c403");
	        values.put("tname", "Boniz");
	        values.put("jie", "1-2节");
	        values.put("week", "周一");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "JavaScript");
	        values.put("classroom", "c403");
	        values.put("tname", "张老师");
	        values.put("jie", "3-4节");
	        values.put("week", "周一");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "职业发展与创业教育");
	        values.put("classroom", "教学楼120");
	        values.put("tname", "童老师");
	        values.put("jie", "5-6节");
	        values.put("week", "周一");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", " ");
	        values.put("tname", " ");
	        values.put("jie", "7-8节");
	        values.put("week", "周一");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	      //周二
	        values=new ContentValues();
	        values.put("kname", "数据结构");
	        values.put("classroom", "C11");
	        values.put("tname", "肖老师");
	        values.put("jie", "1-2节");
	        values.put("week", "周二");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "JavaScript");
	        values.put("classroom", "C9");
	        values.put("tname", "张老师");
	        values.put("jie", "3-4节");
	        values.put("week", "周二");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "5-6节");
	        values.put("week", "周二");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "7-8节");
	        values.put("week", "周二");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	      //周三
	        values=new ContentValues();
	        values.put("kname", "毛概");
	        values.put("classroom", "教学楼220");
	        values.put("tname", "姚老师");
	        values.put("jie", "1-2节");
	        values.put("week", "周三");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "移动应用高级开发技术");
	        values.put("classroom", "C403");
	        values.put("tname", "Boniz");
	        values.put("jie", "3-4节");
	        values.put("week", "周三");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "Java Web");
	        values.put("classroom", "C403");
	        values.put("tname", "Boniz");
	        values.put("jie", "5-6节");
	        values.put("week", "周三");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "数据结构");
	        values.put("classroom", "C机12");
	        values.put("tname", "肖老师");
	        values.put("jie", "7-8节");
	        values.put("week", "周三");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	      //周四
	        values=new ContentValues();
	        values.put("kname", "Java Web开发");
	        values.put("classroom", "C机4");
	        values.put("tname", "Boniz");
	        values.put("jie", "1-2节");
	        values.put("week", "周四");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "平面设计");
	        values.put("classroom", "C403");
	        values.put("tname", "刘老师");
	        values.put("jie", "3-4节");
	        values.put("week", "周四");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "平面设计");
	        values.put("classroom", "C机11");
	        values.put("tname", "刘老师");
	        values.put("jie", "5-6节");
	        values.put("week", "周四");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "7-8节");
	        values.put("week", "周四");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        //周五
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "1-2节");
	        values.put("week", "周五");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "3-4节");
	        values.put("week", "周五");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "5-6节");
	        values.put("week", "周五");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	        values=new ContentValues();
	        values.put("kname", "");
	        values.put("classroom", "");
	        values.put("tname", "");
	        values.put("jie", "7-8节");
	        values.put("week", "周五");
	        values.put("time", "");
	        db.insert("kechengbiao", null, values);
	        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
