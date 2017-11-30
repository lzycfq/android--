package com.qrcode;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


	public class CheckActivity extends Activity {
		Spinner spinner1;
			
			private void display(String string) {
				// TODO Auto-generated method stub
				Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
			}
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_check);
				  spinner1=(Spinner)findViewById(R.id.spinner1);
			        String[] universities= {"ÔçÍË","¿õ¿Î","³Ùµ½"};
			        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, universities);
			        spinner1 = (Spinner)findViewById(R.id.spinner1);
			        spinner1.setAdapter(spinnerAdapter);
			        spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

						public void onItemSelected(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							display(((TextView)arg1).getText().toString());
						}
						
						
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub				
						}    	
			        });
			}

			

		}
