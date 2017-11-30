package com.example.lab25;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	TextView txtno,txtname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		txtno=(TextView)findViewById(R.id.txtno);
		txtname=(TextView)findViewById(R.id.txtname);
		Intent i=getIntent();
		String no=i.getStringExtra("no");
		String name=i.getStringExtra("name");
		txtno.setText(no);
		txtname.setText(name);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
