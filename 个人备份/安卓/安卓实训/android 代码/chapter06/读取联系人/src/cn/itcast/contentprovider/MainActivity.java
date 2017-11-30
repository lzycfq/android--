package cn.itcast.contentprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView lv;
	private List<Person>  persons ;
	//����һ��Handler���������̼߳�ͨ��
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100://���յ����ݲ�ѯ��ϵ���Ϣ
				//UI�߳�����ListView
				lv.setAdapter(new MyAdapter());
				break;
			}
		};
	};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        //����������ݡ���ѯ�����ǱȽϺ�ʱ��,�����Ҫ�����߳���������������
        new Thread(){
        	public void run() {
        		//�������
              AddData();  
        		//��ȡpersons����
        		getPersons(); 
        		//�����ѯ������ ����UI�̷߳�����Ϣ
        		if(persons.size() > 0){
        			handler.sendEmptyMessage(100);
        		}
        	};
        }.start();
    }
    //��person���в���10������
	public void AddData(){
		PersonDao2 dao = new PersonDao2(this);
		long number = 885900000l;
		Random random = new Random();
		for(int i=0;i<10;i++){
			dao.add("wangwu"+i, Long.toString(number+i), random.nextInt(5000));
		}
	}
    //����ContentResolver�����ѯ��Ӧ�ó���ʹ��ContentProvider��¶������
	private void getPersons() {
		//����Ҫ��ȡ��ѯ��uri
		String url = "content://cn.itcast.contentprovider.personprovider/query";
		Uri uri = Uri.parse(url);
		//��ȡContentResolver���� ��������ʹ�ú������ϸ����
        ContentResolver contentResolver = getContentResolver();
        //����ContentResolver�����ѯ���ݵõ�һ��Cursor����
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        persons = new ArrayList<Person>();
        //���cursorΪ�����������÷���
        if(cursor == null){
        	return;
        }
        while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			Person p = new Person(id, name, number);
			persons.add(p);
		}
		cursor.close();
	}
    //������
    private class MyAdapter extends BaseAdapter{
    private static final String TAG = "MyAdapter";
     	// ����listview�����ܹ��ж����Ŀ
		public int getCount() {
			return persons.size(); //��Ŀ���� == ���ϵ�size
		}
		public Object getItem(int position) {
			return persons.get(position);
		}
		public long getItemId(int position) {
			return 0;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			//�õ�ĳ��λ�ö�Ӧ��person����
			Person person = persons.get(position);
			View view = View.inflate(MainActivity.this, R.layout.list_item, null);
			//һ��Ҫ��view��������Ѱ�Һ��ӵ�id
            //����
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_name.setText("����:"+person.getName());
              //�绰
			TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
			tv_phone.setText("�绰:"+person.getNumber());
			return view;
		}
    }
}
