package com.qrcode;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.qrcode.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Curriculum  extends Activity{

	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.zmcurriculum);
	        TextView tv=(TextView)findViewById(R.id.day);
	        Date now=new Date();
	        SimpleDateFormat f=new SimpleDateFormat("yyyy��MM��dd��");
	        
	        tv.setText(f.format(now).toString());
	        
	        ListView lv = (ListView)findViewById(R.id.ListView01);
	        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{getString(R.string.mon),getString(R.string.tue),getString(R.string.wed),getString(R.string.thu),getString(R.string.fri),getString(R.string.sat),getString(R.string.sun)});
	        lv.setAdapter(aa);
	        
	        lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
				
					Intent i=new Intent(Curriculum.this,Dayactivity.class);
					i.putExtra("d", arg2);
					startActivity(i);
					
					
				}
	        	
	        });
	    }
	 public boolean onOptionsItemSelected(MenuItem item){
	    	switch(item.getItemId()){
	    	case R.id.help:
	    		new AlertDialog.Builder(this).setTitle(R.string.help).setMessage(R.string.help_text).setIcon(android.R.drawable.ic_menu_help).show();
	    		break;    	
	    	case R.id.about:
	    		new AlertDialog.Builder(this).setTitle(R.string.about).setMessage(R.string.about_text).setIcon(android.R.drawable.ic_menu_info_details).show();
	    		break;
	    	case R.id.exit:
	    	{
	    		new AlertDialog.Builder(this).setTitle("�Ƿ��˳�?").setPositiveButton("ȷ��",
	    				new DialogInterface.OnClickListener() 
	    				{
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								new AlertDialog.Builder(Curriculum.this).setMessage(
										"�γ̱��Ѿ��˳�������").create().show();
								finish();
								
							}
						}).setNegativeButton("ȡ��",
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										new AlertDialog.Builder(Curriculum.this).setMessage(
												"���Ѿ�ѡ����ȡ��������").create().show();
										
									}
								}).show();
	    		}
	    	}
	    	return false;
	    }
	}
