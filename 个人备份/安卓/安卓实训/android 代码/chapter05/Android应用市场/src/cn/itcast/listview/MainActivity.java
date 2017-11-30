package cn.itcast.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView mListView;
	// ��Ҫ���������
	private String[] names = { "�����̳�", "QQ", "QQ������", "����΢��", "��è",
			                   "UC�����", "΢��" };
	
	private int[]  icons = {R.drawable.jd,R.drawable.qq,R.drawable.qq_dizhu,
			                R.drawable.sina,R.drawable.tmall,R.drawable.uc,
			                R.drawable.weixin};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ��ListView�ؼ�
		mListView = (ListView) findViewById(R.id.lv);
		// ����һ��Adapter��ʵ��
		MyBaseAdapter mAdapter = new MyBaseAdapter();
		// ����Adapter
		mListView.setAdapter(mAdapter);

		// ArrayAdapter
		// mListView.setAdapter(new ArrayAdapter<String>(this,
		// R.layout.list_item,
		// R.id.tv_list, names));

	}

	// ����һ����̳�BaseAdapter
	class MyBaseAdapter extends BaseAdapter {

		// �õ�item������
		public int getCount() {
			// ����ListView Item��Ŀ������
			return names.length;
		}

		// �õ�Item����Ķ���
		public Object getItem(int position) {
			// ����ListView Item��Ŀ����Ķ���
			return names[position];
		}

		// �õ�Item��id
		public long getItemId(int position) {
			// ����ListView Item��id
			return position;
		}

		// �õ�Item��View��ͼ
		public View getView(int position, View convertView, ViewGroup parent) {
			// ��list_item.xml�ļ��ҳ�����ת����View����
			View view = View.inflate(MainActivity.this, R.layout.list_item,
					null); // �ҵ�list_item.xml�д�����TextView
			TextView mTextView = (TextView) view.findViewById(R.id.tv_list);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			mTextView.setText(names[position]);
			imageView.setBackgroundResource(icons[position]);
			return view;
		}
	}
}
