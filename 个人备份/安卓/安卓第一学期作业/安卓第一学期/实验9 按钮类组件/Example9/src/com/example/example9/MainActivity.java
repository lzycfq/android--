package com.example.example9;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker datePicker=(DatePicker)findViewById(R.id.datepicker);
        TimePicker timePicker=();
        Calendar c=Calendar.getInstance();
        year =c.get(Calendar.YEAR);
        month =c.get(Calendar.MONTH);
        day =c.get(Calendar.DAY_OF_MONTH);
        hour =c.get(Calendar.HOUR);
        minute=c.get(Calendar.MINUTE);
        datePicker.init(year,month,day,new OnDateChangedListener(){
        	public void OnDateChanged(DatePicker arg1,int year,int month,int day){
        		year=year;
        		month=month;
        		day=day;
        		showDate(year,month,day,hour,minute);			
        	}
			});
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener(){
        	public void onTimeChanged(TimePicker);
                           chour=hour;
        	               minute=minute;
        	               showDate(year,month,day,hour,minute)
        }});
        	
		


    protected void showDate(int year2, int month2, int day2, int hour2,
			int minute2) {
		// TODO 自动生成的方法存根
    	EditText show=(EditText)findViewById(R.id.show);
		show.setText("你的购买日期:"+year+"年"+month+"月"+day+"天"+"hour"+minute+"分");
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
