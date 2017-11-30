package com.example.example20160413;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.example20160413.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;



public class MainActivity extends Activity {
	private int[] pic = { R.drawable.pic_oracle, R.drawable.pic_javase,
			R.drawable.pic_javaweb, R.drawable.pic_javaee,
			R.drawable.pic_android, R.drawable.pic_game };
	private String[][] bookinfo = { { "Oracle���ݿ�", "������" }, { "Java SE�����γ�", "���" },
			{ "Java WEB�ۺϿ���", "MLDN" }, { "Java EE�߼�����", "���˻�" },
			{ "AndroidǶ��ʽ����", "��С��" }, { "Java ��Ϸ����", "����" } };
	private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        getData();
        BookAdapter bookAdapter=new BookAdapter (this,getData());
        listView.setAdapter(bookAdapter);
		
    }
    private List<Map<String, String>> getData(){
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < pic.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pic", String.valueOf(pic[i]));
			map.put("title", String.valueOf(bookinfo[i][0]));
			map.put("author", String.valueOf(bookinfo[i][1]));
			map.put("type", "���");
			map.put("score", String.valueOf(R.drawable.start_05));
			list.add(map);
		}
		return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
