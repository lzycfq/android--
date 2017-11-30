package com.example.ch25a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText edname;
	EditText ednam;
	EditText edna;
	EditText edn;
	String [] name={"刘","志","远"};
	String b="";
	String [] Degree = { "打", "酱", "油", "的" };
	String str="";
	String [] Hobby = { "咏春", "羽毛球", "击剑", "吃饭" };
    String h="";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edname=(EditText)findViewById(R.id.edname);
				 ednam=(EditText)findViewById(R.id.ednam);
				 edna=(EditText)findViewById(R.id.edna);
				 edn=(EditText)findViewById(R.id.edn);
				
  edname.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		AlertDialog.Builder A=new Builder(MainActivity.this);
		A.setTitle("名字");
		A.setItems(name, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				String a=("名字")+name[which];
				edname.setText(a);
		
		
	}
});
//		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				edname.setText(b);
//				
//			}
//		});
//		builder.create().show();
//	}
//});

		edna.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog datedlg=new DatePickerDialog(MainActivity.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						String date=String.format("%s-%s-%s",year,monthOfYear+1,dayOfMonth);
						ednam.setText(date);
						
					}
				},1900, 0, 1);
				datedlg.show();
				
			}
		});

		ednam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(MainActivity.this);
				builder.setTitle("学历");
				builder.setSingleChoiceItems(Degree, 0,new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						str+=Degree[which];
						str+="";
						
					}
				});
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ednam.setText(str);
						
					}
				});
				builder.create().show();
			}
		});

		edn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(MainActivity.this);
				builder.setTitle("爱好");
				builder.setMultiChoiceItems(Hobby, null, new DialogInterface.OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						if(isChecked){
							h+=Hobby[which];
							h+=" ";
						}else{h="";}
						
					}
				});
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						edn.setText(h);
					}
				});
				builder.create().show();
			}
		});
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

