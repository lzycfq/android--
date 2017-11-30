package cn.itcast.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonDBProvider extends ContentProvider {
	// ����һ��uri��ƥ���� ����ƥ��uri ���·������������ ���� -1
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int INSERT = 1;    //�������ƥ��Uri·���ɹ�ʱ������
	private static final int DELETE = 2;    //ɾ������ƥ��Uri·���ɹ�ʱ������
	private static final int UPDATE = 3;    //��������ƥ��Uri·���ɹ�ʱ������
	private static final int QUERY = 4;     //��ѯ����ƥ��Uri·���ɹ�ʱ������
	private static final int QUERYONE = 5; //��ѯһ������ƥ��Uri·���ɹ�ʱ������
	//���ݿ������Ķ���
	private PersonSQLiteOpenHelper helper;
	static {
		// ���һ��ƥ�����.
		matcher.addURI("cn.itcast.contentprovider.personprovider", "insert", INSERT);
		matcher.addURI("cn.itcast.contentprovider.personprovider", "delete", DELETE);
		matcher.addURI("cn.itcast.contentprovider.personprovider", "update", UPDATE);
		matcher.addURI("cn.itcast.contentprovider.personprovider", "query", QUERY);
         //����ġ�#����Ϊͨ������Ƿ��ϡ�query/���Է���QUERYONE�ķ�����
		matcher.addURI("cn.itcast.contentprovider.personprovider", "query/#", QUERYONE);
	}
	//�������ṩ�߱�������ʱ�� ���� �ʺ� ���ݵĳ�ʼ��
	public boolean onCreate() {
		helper = new PersonSQLiteOpenHelper(getContext());
		return false;
	}
    //��ѯ���ݲ���
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if (matcher.match(uri) == QUERY) { //ƥ���ѯ��Uri·��
			//ƥ��ɹ� ,���ز�ѯ�Ľ����
			SQLiteDatabase db = helper.getReadableDatabase();
			//�������ݿ�����Ĳ�ѯ���ݵķ���
			Cursor cursor = db.query("person", projection, selection,
					selectionArgs, null, null, sortOrder);
			return cursor;
		} else if (matcher.match(uri) == QUERYONE) { 
			//ƥ��ɹ�,����id��ѯ���� 
			long id = ContentUris.parseId(uri);
			SQLiteDatabase db = helper.getReadableDatabase();
			Cursor cursor = db.query("person", projection, "id=?",
					new String[]{id+""}, null, null, sortOrder);
			return cursor;
		} else {
			throw new IllegalArgumentException("·����ƥ��,����ִ�в�ѯ����");
		}
	}
	//��ȡ��ǰUri����������
	public String getType(Uri uri) {
		if (matcher.match(uri) == QUERY) {
			// ���ز�ѯ�Ľ����
			return "vnd.android.cursor.dir/person";
		} else if (matcher.match(uri) == QUERYONE) {
			return "vnd.android.cursor.item/person";
		}
		return null;
	}
	//�������
	public Uri insert(Uri uri, ContentValues values) {
		if (matcher.match(uri) == INSERT) { 
			//ƥ��ɹ� ���ز�ѯ�Ľ����
			SQLiteDatabase db = helper.getWritableDatabase();
			db.insert("person", null, values);
			} else { 
			throw new IllegalArgumentException("·����ƥ��,����ִ�в������");
		}
		return null;
	}
	//ɾ������
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if (matcher.match(uri) == DELETE) { 
			//ƥ��ɹ� ���ز�ѯ�Ľ����
			SQLiteDatabase db = helper.getWritableDatabase();
			db.delete("person", selection, selectionArgs);
			} else { 
			throw new IllegalArgumentException("·����ƥ��,����ִ��ɾ������");
		}
		return 0;
	}
	//��������
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if (matcher.match(uri) == UPDATE) { 
			//ƥ��ɹ� ���ز�ѯ�Ľ����
			SQLiteDatabase db = helper.getWritableDatabase();
			db.update("person", values, selection, selectionArgs);
		} else { 
			throw new IllegalArgumentException("·����ƥ��,����ִ���޸Ĳ���");
		}
		return 0;
	}
}
