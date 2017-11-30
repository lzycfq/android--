package cn.itcast.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {
	private static final String TAG = "PersonSQLiteOpenHelper";
	// ���ݿ�Ĺ��췽��  �����������ݿ������ ���ݿ��ѯ�Ľ���� ���ݿ�İ汾
	public PersonSQLiteOpenHelper(Context context) {
		super(context, "person.db", null, 3);
	}
	// ���ݿ��һ�α�������ʱ�� ���õķ���
	public void onCreate(SQLiteDatabase db) {
		//��ʼ�����ݿ�ı�ṹ
		db.execSQL("create table person (id integer primary key autoincrement, name varchar(20), number varchar(20)) ");
	}
	// �����ݿ�İ汾�ŷ����仯��ʱ��(���ӵ�ʱ��)����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG,"���ݿ�İ汾�仯��...");
	}
}
