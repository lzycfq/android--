package com.example.datetimeproject;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {
DatePicker dPicker;
TimePicker tPicker;
EditText show;
int cyear;int cmonth;int cday;int chour;int cminute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dPicker=(DatePicker)findViewById(R.id.dPicker);
		tPicker=(TimePicker)findViewById(R.id.tPicker);
		
		Calendar c=Calendar.getInstance();
		cyear=c.get(Calendar.YEAR);
		cmonth=c.get(Calendar.MONTH);
		cday=c.get(Calendar.DAY_OF_MONTH);
		chour=c.get(Calendar.HOUR);
		cminute=c.get(Calendar.MINUTE);
		
		dPicker.init(cyear, cmonth, cday,new OnDateChangedListener(){

			@Override
			public void onDateChanged(DatePicker arg0, int year, int month, int day) {
				cyear=year;
				cmonth=month;
				cday=day;
				showmsg(year,month,day,chour,cminute);
			}});
		tPicker.setOnTimeChangedListener(new OnTimeChangedListener(){

			@Override
			public void onTimeChanged(TimePicker arg0, int hour, int minute) {
				chour=hour;
				cminute=minute;
				showmsg(cyear,cmonth,cday,hour,minute);
				
			}});
		
	}
    private  void showmsg(int year,int month,int day,int hour,int minute){
    	show=(EditText)findViewById(R.id.show);
    	show.setText(year+"-"+(month+1)+"-"+day+" "+hour+":"+day);
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
