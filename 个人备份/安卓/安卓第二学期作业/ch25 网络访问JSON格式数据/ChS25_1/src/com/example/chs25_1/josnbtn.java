package com.example.chs25_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class josnbtn extends Activity {
	 EditText sno,password;
	 Button btn;
	 TextView information;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.josnbtn);
		sno=(EditText)findViewById(R.id.sno);
		password=(EditText)findViewById(R.id.password);
		btn=(Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sno=((EditText)findViewById(R.id.sno)).getText().toString();
				String password=((EditText)findViewById(R.id.password)).getText().toString();
				if(sno.equals("123456")&&password.equals("123456")){
				   information.setText("用户登陆成功");
				   Intent in=new Intent();
				   in.setClass(josnbtn.this,MainActivity.class);
				   startActivity(in);
				}
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