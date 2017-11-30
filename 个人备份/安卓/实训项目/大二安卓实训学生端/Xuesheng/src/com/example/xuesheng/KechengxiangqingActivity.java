package com.example.xuesheng;

import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class KechengxiangqingActivity extends Activity{
	EditText ed_kname;
	EditText ed_classroom;
	static EditText ed_time;
	EditText ed_tname;
	TextView text_jie;
	Button btn_baocun;
	DBHepler dbHepler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kechengxiangqing);
		ed_kname=(EditText) findViewById(R.id.ed_kname);
		ed_classroom=(EditText) findViewById(R.id.ed_classroom);
		ed_time=(EditText) findViewById(R.id.ed_time);
		ed_tname=(EditText) findViewById(R.id.ed_tname);
		text_jie=(TextView) findViewById(R.id.text_jie);
		btn_baocun=(Button) findViewById(R.id.btn_baocun);
		
		Intent i=getIntent();
		String kname=i.getStringExtra("kname");
		String classrooom=i.getStringExtra("classrooom");
		String tname=i.getStringExtra("tname");
		String time=i.getStringExtra("time");
		final String week=i.getStringExtra("week");
		final String jie=i.getStringExtra("jie");
		
		 dbHepler=new DBHepler(this);
		text_jie.setText(week+" "+jie);
		ed_classroom.setText(classrooom);
		ed_kname.setText(kname);
		ed_time.setText(time);
		ed_tname.setText(tname);
		
		ed_time.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TimeFragment t=new TimeFragment();
				t.show(getFragmentManager(), "time");
			}
		});
		
		btn_baocun.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String classroom=ed_classroom.getText().toString();
				String kname=ed_kname.getText().toString();
				String time=ed_time.getText().toString();
				String tname=ed_tname.getText().toString();
				
				 SQLiteDatabase  db=dbHepler.getWritableDatabase();
			        ContentValues values=new ContentValues();
			        values.put("kname", kname);
			        values.put("classroom", classroom);
			        values.put("tname", tname);
			        values.put("time", time);
					int line_number=db.update("kechengbiao", values, "week=? and jie=?", new String[]{week,jie});
					if(line_number>0){
						Toast.makeText(getApplicationContext(), "保存成功", 0).show();
					}
					
					db.close();
				KechengxiangqingActivity.this.finish();
			}
		});
	}
	public static class TimeFragment extends DialogFragment implements OnTimeSetListener{
		  public Dialog onCreateDialog(Bundle savedInstanceState) {
			    final Calendar c = Calendar.getInstance();
			    int hourOfDay = c.get(Calendar.HOUR);
			    int minute = c.get(Calendar.MINUTE);
			    return new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);
			  }
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			ed_time.setText(hourOfDay+":"+minute);
		}
		
	}

}
