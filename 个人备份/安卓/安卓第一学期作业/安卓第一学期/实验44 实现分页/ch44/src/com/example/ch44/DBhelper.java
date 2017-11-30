package com.example.ch44;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public DBhelper(Context context, String name, CursorFactory factory,
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
		     String sql1="CREATE TABLE IF NOT EXISTS students(sid intger primary key autotioncrement,name varchar(20))";
		     db.execSQL(sql1);
		     sql1="CREATE TABLE IF NOT EXISTS  marks(mid intger primary key autotioncrement,sid intger, subject varchar(20),mark intger()";
		     db.execSQL(sql1);
		     sql1="insert into students(name)values('tom')";
		     db.execSQL(sql1);
		     sql1="insert into students(name)values('jery')";
		     db.execSQL(sql1);
		     sql1="insert into students(name)values('jack')";
		     db.execSQL(sql1);
		     sql1="insert into students(name)values('rose')";
		     db.execSQL(sql1);
		     String sql2="CREATE TABLE IF NOT EXISTS students(sid intger primary key autotioncrement,name varchar(20))";
		     db.execSQL(sql2);
		     sql2="CREATE TABLE IF NOT EXISTS  marks(mid intger primary key autotioncrement,sid intger, subject varchar(20),mark intger()";
		     db.execSQL(sql2);
		     sql2="insert into students(name)values('tom')";
		     db.execSQL(sql2);
		     sql2="insert into students(name)values('jery')";
		     db.execSQL(sql2);
		     sql2="insert into students(name)values('jack')";
		     db.execSQL(sql2);
		     sql2="insert into students(name)values('rose')";
		     db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
