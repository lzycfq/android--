package com.example.ch25;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class RainbowAdapter extends BaseAdapter {
 private LayoutInflater mInflater;
 private List<Map<String, Object>> mData;
 TextView txtColor;
 public RainbowAdapter(Context context,List<Map<String, Object>> data){
	 super();
	 this.mInflater=LayoutInflater.from(context);
	 this.mData=data;
 }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=mInflater.inflate(get, null);
		txtColor=(TextView)convertView.findViewById(R.id.textColor);
		String colorname=mData.get(location)
		return null;
	}

}
