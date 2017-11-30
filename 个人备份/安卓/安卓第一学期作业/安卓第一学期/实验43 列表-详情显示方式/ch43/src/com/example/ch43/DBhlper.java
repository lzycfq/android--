package com.example.ch43;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhlper extends SQLiteOpenHelper {

	public DBhlper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
     String sql="CREATE TABLE IF NOT EXISTS students(sid intger primary key autotioncrement,name varchar(20))";
     db.execSQL(sql);
     sql="CREATE TABLE IF NOT EXISTS  marks(mid intger primary key autotioncrement,sid intger, subject varchar(20),mark intger()";
     db.execSQL(sql);
     sql="insert into students(name)values('tom')";
     db.execSQL(sql);
     sql="insert into students(name)values('jery')";
     db.execSQL(sql);
     sql="insert into students(name)values('jack')";
     db.execSQL(sql);
     sql="insert into students(name)values('rose')";
     db.execSQL(sql);
      sql="insert into marks(sid,subject,mark)values(1,ÓïÎÄ,86)";
      db.execSQL(sql);
      sql="insert into marks(sid,subject,mark)values(1,ÊýÑ§,86)";
      db.execSQL(sql);
      sql="insert into marks(sid,subject,mark)values(1,Ó¢Óï,86)";
      db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
