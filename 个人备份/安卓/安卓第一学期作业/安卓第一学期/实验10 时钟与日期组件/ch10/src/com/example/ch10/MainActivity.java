package com.example.ch10;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;


public class MainActivity extends Activity {
	int cYear,cMonth,cDay,cHour,cMinute;
	DatePicker dPicker=null;
	TimePicker tPicker=null;
	TextView txtDateTime=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c=Calendar.getInstance();
        cYear=c.get(Calendar.YEAR);
        cMonth=c.get(Calendar.MONTH)+1;
        cDay=c.get(Calendar.DAY_OF_MONTH);
        
        dPicker=(DatePicker) findViewById(R.id.dPicker);
        tPicker=(TimePicker) findViewById(R.id.tPicker);
        txtDateTime=(TextView) findViewById(R.id.txtDateTime);
        dPicker.init(cYear, cMonth, cDay, new OnDateChangedListener(){

			@Override
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				cYear=year;
				cMonth=monthOfYear;
				cDay=dayOfMonth;
				ShowMsg();
			}
        });
        tPicker.setOnTimeChangedListener(new OnTimeChangedListener(){

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				cHour=hourOfDay;
				cMinute=minute;
				ShowMsg();
			}
        });
        
    }
    private void ShowMsg(){
    	txtDateTime.setText(String.format("%d-%d-%d %d:%d", cYear,cMonth,cDay,cHour,cMinute));
    	
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
