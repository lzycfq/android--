package com.example.ch13;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends Activity {
	TextView se=null;
	TextView ou=null;
	Spinner spnC=null;
	Spinner spnD=null;
	TextView text=null;
	String city,district;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);Resources res = getResources();
		String[] shi = res.getStringArray(R.array.city);
		spnC=(Spinner) findViewById(R.id.spnC);
		spnD=(Spinner) findViewById(R.id.spnD);
		text=(TextView) findViewById(R.id.text);
		se=(TextView)findViewById(R.id.se);
		ou=(TextView)findViewById(R.id.ou);
		ArrayAdapter<String> adpCity = new ArrayAdapter<String>(this,R.layout.list_item,shi);
		spnC.setAdapter(adpCity);
		spnC.setOnItemSelectedListener(new OnItemSelectedListener() {
			ArrayAdapter<String> adpDistrict;
			public void onItemSelected(AdapterView<?> src, View arg1,
					int position, long arg3) {
				switch(position){
				    case 0:
					    adpDistrict=new ArrayAdapter<String>(getBaseContext(),R.layout.list_item,getResources().getStringArray(R.array.guangzhou));
					    break;
				    case 1:
						adpDistrict=new ArrayAdapter<String>(getBaseContext(),R.layout.list_item,getResources().getStringArray(R.array.shenzhen));
						break;
				    case 2:
				    	adpDistrict=new ArrayAdapter<String>(getBaseContext(),R.layout.list_item,getResources().getStringArray(R.array.foshan));
				    	break;
				    default:
				    	break;
				}
				spnD.setAdapter(adpDistrict);
				text.setText(src.getItemAtPosition(position).toString());
			
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		spnC.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> src, View arg,
					int positon, long arg3) {
				district=src.getItemAtPosition(positon).toString();
			text.setText(String.format("%市,%区",city,district));
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自动生成的方法存根
				
			}
		});
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
