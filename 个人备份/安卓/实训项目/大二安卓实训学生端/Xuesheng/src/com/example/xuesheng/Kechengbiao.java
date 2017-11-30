package com.example.xuesheng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class Kechengbiao extends Fragment{
	ListView list1;
	ListView list2;
	ListView list3;
	ListView list4;
	ListView list5;
	DBHepler dbHepler;
	ArrayList<Map<String, String>> week1;
	ArrayList<Map<String, String>> week2;
	ArrayList<Map<String, String>> week3;
	ArrayList<Map<String, String>> week4;
	ArrayList<Map<String, String>> week5;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View view=inflater.inflate(R.layout.kechengbiao, container, false);
		 week1 = new ArrayList<Map<String, String>>();
		 week2 = new ArrayList<Map<String, String>>();
		 week3 = new ArrayList<Map<String, String>>();
		 week4 = new ArrayList<Map<String, String>>();
		 week5 = new ArrayList<Map<String, String>>();
		 list1=(ListView) view.findViewById(R.id.list1);
		 list2=(ListView) view.findViewById(R.id.list2);
		 list3=(ListView) view.findViewById(R.id.list3);
		 list4=(ListView) view.findViewById(R.id.list4);
		 list5=(ListView) view.findViewById(R.id.list5);
		 dbHepler=new DBHepler(getActivity());
	        dbHepler.getReadableDatabase();
			init();
			show();
		 return view;
	}
	
	private void show() {
		list1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(getActivity(),KechengxiangqingActivity.class);
				i.putExtra("kname", week1.get(position).get("kname"));
				i.putExtra("classrooom", week1.get(position).get("classrooom"));
				i.putExtra("tname", week1.get(position).get("tname"));
				i.putExtra("time", week1.get(position).get("time"));
				i.putExtra("week", week1.get(position).get("week"));
				i.putExtra("jie", week1.get(position).get("jie"));
				startActivityForResult(i, 1);
			}
		});
		list3.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(getActivity(),KechengxiangqingActivity.class);
				i.putExtra("kname", week3.get(position).get("kname"));
				i.putExtra("classrooom", week3.get(position).get("classrooom"));
				i.putExtra("tname", week3.get(position).get("tname"));
				i.putExtra("time", week3.get(position).get("time"));
				i.putExtra("week", week3.get(position).get("week"));
				i.putExtra("jie", week3.get(position).get("jie"));
				startActivityForResult(i, 1);
			}
		});
		list4.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(getActivity(),KechengxiangqingActivity.class);
				i.putExtra("kname", week4.get(position).get("kname"));
				i.putExtra("classrooom", week4.get(position).get("classrooom"));
				i.putExtra("tname", week4.get(position).get("tname"));
				i.putExtra("time", week4.get(position).get("time"));
				i.putExtra("week", week4.get(position).get("week"));
				i.putExtra("jie", week4.get(position).get("jie"));
				startActivityForResult(i, 1);
			}
		});
		list5.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(getActivity(),KechengxiangqingActivity.class);
				i.putExtra("kname", week5.get(position).get("kname"));
				i.putExtra("classrooom", week5.get(position).get("classrooom"));
				i.putExtra("tname", week5.get(position).get("tname"));
				i.putExtra("time", week5.get(position).get("time"));
				i.putExtra("week", week5.get(position).get("week"));
				i.putExtra("jie", week5.get(position).get("jie"));
				startActivityForResult(i, 1);
			}
		});
		list2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(getActivity(),KechengxiangqingActivity.class);
				i.putExtra("kname", week2.get(position).get("kname"));
				i.putExtra("classrooom", week2.get(position).get("classrooom"));
				i.putExtra("tname", week2.get(position).get("tname"));
				i.putExtra("time", week2.get(position).get("time"));
				i.putExtra("week", week2.get(position).get("week"));
				i.putExtra("jie", week2.get(position).get("jie"));
				startActivityForResult(i, 1);
			}
		});
		
		
	}

	public  void init() {
		week1.clear();
		week2.clear();
		week3.clear();
		week4.clear();
		week5.clear();
    	SQLiteDatabase db=dbHepler.getReadableDatabase();
    	Cursor cursor=db.query("kechengbiao", null, "week=?", new String[]{"周一"}, null, null ,null);
    	while(cursor.moveToNext()){
    		 Map<String, String> mapjilu = new HashMap<String, String>();
    	String kname=cursor.getString(cursor.getColumnIndex("kname"));
    	String classrooom=cursor.getString(cursor.getColumnIndex("classroom"));
    	String tname=cursor.getString(cursor.getColumnIndex("tname"));
    	String time=cursor.getString(cursor.getColumnIndex("time"));
    	String week=cursor.getString(cursor.getColumnIndex("week"));
    	String jie=cursor.getString(cursor.getColumnIndex("jie"));
		mapjilu.put("kname",kname );
		mapjilu.put("classrooom",classrooom );
		mapjilu.put("tname",tname );
		mapjilu.put("time",time );
		mapjilu.put("week",week );
		mapjilu.put("jie",jie );
		week1.add(mapjilu);
    	}
    	cursor=db.query("kechengbiao", null, "week=?", new String[]{"周二"}, null, null ,null);
    	while(cursor.moveToNext()){
    		Map<String, String> mapjilu = new HashMap<String, String>();
    		String kname=cursor.getString(cursor.getColumnIndex("kname"));
    		String classrooom=cursor.getString(cursor.getColumnIndex("classroom"));
    		String tname=cursor.getString(cursor.getColumnIndex("tname"));
    		String time=cursor.getString(cursor.getColumnIndex("time"));
    		String week=cursor.getString(cursor.getColumnIndex("week"));
    		String jie=cursor.getString(cursor.getColumnIndex("jie"));
    		mapjilu.put("kname",kname );
    		mapjilu.put("classrooom",classrooom );
    		mapjilu.put("tname",tname );
    		mapjilu.put("time",time );
    		mapjilu.put("week",week );
    		mapjilu.put("jie",jie );
    		week2.add(mapjilu);
    	}
    	cursor=db.query("kechengbiao", null, "week=?", new String[]{"周三"}, null, null ,null);
    	while(cursor.moveToNext()){
    		Map<String, String> mapjilu = new HashMap<String, String>();
    		String kname=cursor.getString(cursor.getColumnIndex("kname"));
    		String classrooom=cursor.getString(cursor.getColumnIndex("classroom"));
    		String tname=cursor.getString(cursor.getColumnIndex("tname"));
    		String time=cursor.getString(cursor.getColumnIndex("time"));
    		String week=cursor.getString(cursor.getColumnIndex("week"));
    		String jie=cursor.getString(cursor.getColumnIndex("jie"));
    		mapjilu.put("kname",kname );
    		mapjilu.put("classrooom",classrooom );
    		mapjilu.put("tname",tname );
    		mapjilu.put("time",time );
    		mapjilu.put("week",week );
    		mapjilu.put("jie",jie );
    		week3.add(mapjilu);
    	}
    	cursor=db.query("kechengbiao", null, "week=?", new String[]{"周四"}, null, null ,null);
    	while(cursor.moveToNext()){
    		Map<String, String> mapjilu = new HashMap<String, String>();
    		String kname=cursor.getString(cursor.getColumnIndex("kname"));
    		String classrooom=cursor.getString(cursor.getColumnIndex("classroom"));
    		String tname=cursor.getString(cursor.getColumnIndex("tname"));
    		String time=cursor.getString(cursor.getColumnIndex("time"));
    		String week=cursor.getString(cursor.getColumnIndex("week"));
    		String jie=cursor.getString(cursor.getColumnIndex("jie"));
    		mapjilu.put("kname",kname );
    		mapjilu.put("classrooom",classrooom );
    		mapjilu.put("tname",tname );
    		mapjilu.put("time",time );
    		mapjilu.put("week",week );
    		mapjilu.put("jie",jie );
    		week4.add(mapjilu);
    	}
    	cursor=db.query("kechengbiao", null, "week=?", new String[]{"周五"}, null, null ,null);
    	while(cursor.moveToNext()){
    		Map<String, String> mapjilu = new HashMap<String, String>();
    		String kname=cursor.getString(cursor.getColumnIndex("kname"));
    		String classrooom=cursor.getString(cursor.getColumnIndex("classroom"));
    		String tname=cursor.getString(cursor.getColumnIndex("tname"));
    		String time=cursor.getString(cursor.getColumnIndex("time"));
    		String week=cursor.getString(cursor.getColumnIndex("week"));
    		String jie=cursor.getString(cursor.getColumnIndex("jie"));
    		mapjilu.put("kname",kname );
    		mapjilu.put("classrooom",classrooom );
    		mapjilu.put("tname",tname );
    		mapjilu.put("time",time );
    		mapjilu.put("week",week );
    		mapjilu.put("jie",jie );
    		week5.add(mapjilu);
    	}
   	 SimpleAdapter jiluAdapter = new SimpleAdapter(getActivity(), week1,
				R.layout.time, new String[] { "kname" }, new int[] { R.id.time});
		list1.setAdapter(jiluAdapter);
		SimpleAdapter jiluAdapter2 = new SimpleAdapter(getActivity(), week2,
				R.layout.time, new String[] { "kname" }, new int[] { R.id.time});
		list2.setAdapter(jiluAdapter2);
		SimpleAdapter jiluAdapter3 = new SimpleAdapter(getActivity(), week3,
				R.layout.time, new String[] { "kname" }, new int[] { R.id.time});
		list3.setAdapter(jiluAdapter3);
		SimpleAdapter jiluAdapter4 = new SimpleAdapter(getActivity(), week4,
				R.layout.time, new String[] { "kname" }, new int[] { R.id.time});
		list4.setAdapter(jiluAdapter4);
		SimpleAdapter jiluAdapter5 = new SimpleAdapter(getActivity(), week5,
				R.layout.time, new String[] { "kname" }, new int[] { R.id.time});
		list5.setAdapter(jiluAdapter5);
    	cursor.close();
    	db.close();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		init();
	}
}
