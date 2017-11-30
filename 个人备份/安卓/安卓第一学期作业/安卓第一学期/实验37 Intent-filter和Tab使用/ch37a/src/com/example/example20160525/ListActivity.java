package com.example.example20160525;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListActivity extends Activity {
	ListView listView;
	String name[]=new String[]{"节操","羽大大","诸哥","王俞"};
	String str[]=new String[]{"主公","忠臣","内奸","反贼"};
	int id[]=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
	String s[]=new String[]{"img","name","title"};
	int i[]=new int[]{R.id.imgPic,R.id.txtName,R.id.txtTitle};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listView=(ListView)findViewById(R.id.lstvStudent);
		SimpleAdapter listAdapter=new SimpleAdapter(this, getData(),R.layout.list_item_example, s,i );
	    listView.setAdapter(listAdapter);
	}
	private List<Map<String,Object>> getData(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<id.length;i++){
			Map<String, Object> map=new HashMap<String, Object>();
		map.put(s[0], id[i]);
		map.put(s[1], name[i]);
		map.put(s[2], str[i]);
		list.add(map);
		}
		return list;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
