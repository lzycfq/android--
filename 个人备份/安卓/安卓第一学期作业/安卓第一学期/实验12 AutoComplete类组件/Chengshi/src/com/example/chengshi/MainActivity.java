package com.example.chengshi;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	Spinner spnCity = null;
	Spinner spnDistrict = null;
	TextView texta = null;
	String city, district;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources res = getResources();
		String[] shi = res.getStringArray(R.array.city);

		spnCity = (Spinner) findViewById(R.id.spnCity);
		spnDistrict = (Spinner) findViewById(R.id.spnDistrict);
		texta = (TextView) findViewById(R.id.texta);
		ArrayAdapter<String> adpCity = new ArrayAdapter<String>(this,
				R.layout.list_item, shi);
		// ArrayAdapter<String> adpDistrict;
		spnCity.setAdapter(adpCity);
		spnCity.setOnItemSelectedListener(new OnItemSelectedListener() {
			ArrayAdapter<String> adpDistrict;

			@Override
			public void onItemSelected(AdapterView<?> src, View arg1,
					int position, long arg3) {

				switch (position) {
				case 0:
					adpDistrict = new ArrayAdapter<String>(getBaseContext(),
							R.layout.list_item, getResources().getStringArray(
									R.array.guangzhou));
					break;
				case 1:
					adpDistrict = new ArrayAdapter<String>(getBaseContext(),
							R.layout.list_item, getResources().getStringArray(
									R.array.shenzhen));
					break;
				case 2:
					adpDistrict = new ArrayAdapter<String>(getBaseContext(),
							R.layout.list_item, getResources().getStringArray(
									R.array.foshan));
					break;
				default:
					break;
				}
				spnDistrict.setAdapter(adpDistrict);
				city = src.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		spnDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> src, View view,
					int position, long id) {
				district = src.getItemAtPosition(position).toString();
				texta.setText("您选择的是" + city + "市" + district + "区");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
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
