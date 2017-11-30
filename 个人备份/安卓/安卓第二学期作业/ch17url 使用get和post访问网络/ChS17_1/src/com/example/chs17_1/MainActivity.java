package com.example.chs17_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

public class MainActivity extends Activity implements OnClickListener {

	EditText editSno;
	EditText editPassword;
	Button btnLogin;

	class NetworkTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			String urlString = "http://10.0.2.2:8080/LoginCheck";
			//ÀûÓÃ×Ö·û´®Æ´×°ÇëÇóµÄ×Ö·û´®
			StringBuilder builder = new StringBuilder(urlString);
			builder.append("?sno=").append(params[0]);
			builder.append("&password=").append(params[1]);
			URL url;
			try {
				url = new URL(builder.toString());
				//
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();				
				conn.setConnectTimeout(5000);
				if (conn.getResponseCode() == 200) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conn.getInputStream()));
					String line;
					String result = "";
					while ((line = in.readLine()) != null) {
						result = line;
					}					
					return result;
				}
				conn.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if (result.equals("pass"))
				Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦",
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getApplicationContext(), "ÕËºÅÃÜÂë´íÎó",
						Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editSno = (EditText) findViewById(R.id.editSno);
		editPassword = (EditText) findViewById(R.id.editPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
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
	public void onClick(View v) {
		String password = editPassword.getText().toString();
		String sno = editSno.getText().toString();
		new NetworkTask().execute(sno, password);
	}
}
