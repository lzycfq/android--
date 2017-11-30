package com.example.class21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


class ExamFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		
		View view= inflater.inflate(R.layout.item_layout_stuentexam, container, false);
		TextView textView=(TextView)view.findViewById(R.id.tv_StuExam);
		textView.setText("Jerry的成绩是：");
		textView.setTextSize(32);
		int result[]={63,64,68};
		String lesson[]={"语文","英语","数学"};
		List<Map<String,String>> data=new ArrayList<Map<String,String>>();
		for(int i=0;i<lesson.length;i++){
			Map<String,String> map=new HashMap<String, String>();
			map.put("result", Integer.toString(result[i]));
			map.put("lesson", lesson[i]);
			data.add(map);
			
		}
		ListView listView=(ListView)view.findViewById(R.id.listExam);
		listView.setAdapter(new SimpleAdapter(getActivity(), data, android.R.layout.simple_list_item_2, new String[]{"result","lesson"},new int[]{android.R.id.text1,android.R.id.text2}));
		return view;
	}
}