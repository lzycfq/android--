package com.example.ch41;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context){
		super(context,"FriendsBook.db",null,2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS friends (friendID integer , name varchar(20), title varchar(20), phone varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS extrainfo(friendID integer primary key autoincrement, address varchar(50), email varchar(20))");
	}

}
