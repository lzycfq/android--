package com.example.class21;

import java.util.ArrayList;
import java.util.List;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

class StudentFragment extends Fragment{
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO 自动生成的方法存根
			View v=inflater.inflate(R.layout.item_layout_studentmenu, container,false);
			TextView textView=(TextView)v.findViewById(R.id.tv_Student);
			textView.setText("学生名单：");
			String []name=new String[]{"Tom","Jerry","Jack","Rose"};
			List<String> names=new ArrayList<String>();
			for(int i=0;i<name.length;i++){
				names.add(name[i]);
			}
			ListView listView=(ListView)v.findViewById(R.id.listStudent);
			listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,names));
			
			return v;
		}
	}