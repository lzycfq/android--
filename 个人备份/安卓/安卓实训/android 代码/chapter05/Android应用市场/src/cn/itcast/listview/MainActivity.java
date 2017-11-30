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
	// 需要适配的数据
	private String[] names = { "京东商城", "QQ", "QQ斗地主", "新浪微博", "天猫",
			                   "UC浏览器", "微信" };
	
	private int[]  icons = {R.drawable.jd,R.drawable.qq,R.drawable.qq_dizhu,
			                R.drawable.sina,R.drawable.tmall,R.drawable.uc,
			                R.drawable.weixin};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化ListView控件
		mListView = (ListView) findViewById(R.id.lv);
		// 创建一个Adapter的实例
		MyBaseAdapter mAdapter = new MyBaseAdapter();
		// 设置Adapter
		mListView.setAdapter(mAdapter);

		// ArrayAdapter
		// mListView.setAdapter(new ArrayAdapter<String>(this,
		// R.layout.list_item,
		// R.id.tv_list, names));

	}

	// 创建一个类继承BaseAdapter
	class MyBaseAdapter extends BaseAdapter {

		// 得到item的总数
		public int getCount() {
			// 返回ListView Item条目的总数
			return names.length;
		}

		// 得到Item代表的对象
		public Object getItem(int position) {
			// 返回ListView Item条目代表的对象
			return names[position];
		}

		// 得到Item的id
		public long getItemId(int position) {
			// 返回ListView Item的id
			return position;
		}

		// 得到Item的View视图
		public View getView(int position, View convertView, ViewGroup parent) {
			// 将list_item.xml文件找出来并转换成View对象
			View view = View.inflate(MainActivity.this, R.layout.list_item,
					null); // 找到list_item.xml中创建的TextView
			TextView mTextView = (TextView) view.findViewById(R.id.tv_list);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			mTextView.setText(names[position]);
			imageView.setBackgroundResource(icons[position]);
			return view;
		}
	}
}
