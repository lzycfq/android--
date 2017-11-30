package com.example.chs22_2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//自定义侦听器
interface OnSelectCategoryListener{
	public void onCategorySelect(String title);
}

public class LeftFragment extends Fragment implements OnItemClickListener {
	ListView listView;
	//事件引发对象
	OnSelectCategoryListener selectListener; 
	
	@Override
	public void onAttach(Activity activity) {		
		super.onAttach(activity);
		//要求容器的Activaty必须是实现OnSelectCategoryListener接口的实例
		try {
			selectListener = (OnSelectCategoryListener) activity;
	    } catch (Exception e) {
	        throw new ClassCastException(activity.toString() +
	        		"must implement OnSelectCategoryListener");
	    }

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_left, container, false);
		listView = (ListView) view.findViewById(R.id.listCategory);
		listView.setAdapter(new ArrayAdapter<String>(view.getContext(),
				android.R.layout.simple_list_item_1, getData()));
		listView.setOnItemClickListener(this);
		return view;
	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		data.add("数码产品");
		data.add("生活用品");
		data.add("汽车用品");
		data.add("儿童玩具");
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> data, View view, int pos, long arg3) {
		String value=data.getItemAtPosition(pos).toString();
		//利用侦听器回调Activity
		selectListener.onCategorySelect(value);
	}

}
