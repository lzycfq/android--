package com.example.lab21;

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
import android.widget.Toast;

public class fragment_score extends Fragment {
	ListView listScore;
	TextView txtScore;
	String name="无";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_score, container, false);
		txtScore=(TextView)view.findViewById(R.id.txtScore);
		txtScore.setText(name);
		listScore=(ListView)view.findViewById(R.id.listScore);
		listScore.setAdapter(new SimpleAdapter(getActivity(), getScore(),R.layout.list_item,new String[]{"score","subject"}, new int[]{R.id.txtScore,R.id.txtSubject}));
		return view;
	}
    public List<Map<String,Object>> getScore(){
    	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	Map<String,Object> m=new HashMap<String, Object>();
    	m.put("subject","语文");
    	m.put("score", 63);
    	list.add(m);
    	
    	m=new HashMap<String, Object>();
    	m.put("subject","数学");
    	m.put("score", 64);
    	list.add(m);
    	
    	m=new HashMap<String, Object>();
    	m.put("subject","英语");
    	m.put("score", 68);
    	list.add(m);
    	
    	return list;
    }
    public void SetMessage(String msg) {
    	//txtScore.setText("成绩");
    	name=msg;    	
	}
}
