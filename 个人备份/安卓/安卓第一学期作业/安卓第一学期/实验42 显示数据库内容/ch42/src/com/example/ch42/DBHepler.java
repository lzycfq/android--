package com.example.ch42;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHepler extends SQLiteOpenHelper {
    public DBHepler(Context context){
    	super(context,"FriendsBook.db",null,1);
    }
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists friends(CREATE TABLE IF NOT EXISTS friends " +
				"(friendID integer primary key autoincrement, name varchar(20), title varchar(20), phone varchar(20)))");
		db.execSQL("create table if not exists friends,valuse(xxx,xxx)");
	}


	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
     /*db.execSQL("create table if not exists extrainfo" +
     		"(CREATE TABLE IF NOT EXISTS extrainfo(friendID integer primary key , address varchar(50), email varchar(20))");*/
	}

}
