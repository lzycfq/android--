package com.example.lab21;

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
import android.widget.Toast;

interface OnSelectCategoryListener{
	public void onCategorySelect(String title);
}

public class fragment_name extends Fragment implements OnItemClickListener{
	ListView listName;
	OnSelectCategoryListener selectListener;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			selectListener=(OnSelectCategoryListener)activity;
		} catch (Exception e) {
			throw new ClassCastException(activity.toString() +
	        		"must implement OnSelectCategoryListener");
		}
	}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.fragement_name, container, false);
	
	listName=(ListView)view.findViewById(R.id.listName);
	ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,getName());
	listName.setAdapter(adapter);
	listName.setOnItemClickListener(this);
	return view;
}

	public List<String> getName() {
		List<String> lname = new ArrayList<String>();
		lname.add("Tom");
		lname.add("Jerry");
		lname.add("Jack");
		lname.add("Rose");
		return lname;
	}
   
	@Override
	public void onItemClick(AdapterView<?> data, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub
		String value=data.getItemAtPosition(pos).toString();
		selectListener.onCategorySelect(value);
		//Toast.makeText(getActivity(), value, 0).show();
	}
}
