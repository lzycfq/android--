package com.example.lab17;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	EditText editSno,editPassword;
	Button btnLogin;
	
	class NetworkTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpUtil hu=new HttpUtil();
			String s="http://10.0.2.2:8080/LoginCheck";
			String keys[]={"?sno=","&password="};
			String values[]={params[0],params[1]};
			
			return hu.sendGet(s, keys, values);
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (result.equals("pass"))
				Toast.makeText(getApplicationContext(), "µ«¬º≥…π¶",
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getApplicationContext(), "’À∫≈√‹¬Î¥ÌŒÛ",
						Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}
	}
	class NetworkTask2 extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpUtil hu=new HttpUtil();
			String s="http://10.0.2.2:8080/LoginCheck";
			String keys[]={"sno=","&password="};
			String values[]={params[0],params[1]};
			
			return hu.sendPost(s, keys, values);
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (result.equals("pass"))
				Toast.makeText(getApplicationContext(), "µ«¬º≥…π¶",
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getApplicationContext(), "’À∫≈√‹¬Î¥ÌŒÛ",
						Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}
	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editSno=(EditText)findViewById(R.id.editSno);
		editPassword=(EditText)findViewById(R.id.editPassword);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
		
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String password = editPassword.getText().toString();
		String sno = editSno.getText().toString();
		new NetworkTask().execute(sno, password);
		new NetworkTask2().execute(sno, password);
	}

	
}
