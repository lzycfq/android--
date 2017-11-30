package com.example.ch25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ch25.R.array;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
ListView lstvItem;
String[] colorname;
TypedArray colorid;
private List<Map<String,Object>> getData(){
	List<Map<String,Object>> listRainbow=new ArrayList<Map<String,Object>>();
	for(int i=0;i<colorname.length;i++){
		Map<String,Object> color=new HashMap<String,Object>();
		color.put("colorname", colorname[i]);
		color.put("colorid",colorid.getInt(i, 0) );
		listRainbow.add(color);
	}
	return listRainbow;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstvItem=(ListView)findViewById(R.id.lstvItem);
        colorname=getResources().getStringArray(R.array.colorname);
        colorid=getResources().obtainTypedArray(R.array.colorid);
        RainbowAdapter ra=new RainbowAdapter(this, getData());
        lstvItem.setAdapter(ra);
    }

    public class RainbowAdapter extends BaseAdapter{
    private LayoutInflater mIndlater;
    private List<Map<String,Object>> mData;
    TextView txtColor;
    public RainbowAdapter(Context context,List<Map<String,Object>> data){
    	super();
    	this.mIndlater=LayoutInflater.from(context);
    	this.mData=data;
    }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView=mIndlater.inflate(R.layout.item_list, null);
			txtColor=(TextView)convertView.findViewById(R.id.textColor);
			String colorname=mData.get(position).get("colorname").toString();
			int colorid=Integer.parseInt(mData.get(position).get("colorid").toString());
			txtColor.setText(colorname);
			txtColor.setBackgroundColor(colorid);
			return convertView;
		}}

  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
