package com.example.lab18;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	TextView txtSno, txtCh, txtMa, txtEn;
	Button btn;
	EditText editSno;

	class NetworkTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String urlString = "http://10.0.2.2:8080/QueryMark";
			String result = "";
			HttpPost request = new HttpPost(urlString);
			List<NameValuePair> postparams = new ArrayList<NameValuePair>();
			postparams.add(new BasicNameValuePair("sno", params[0]));
			try {
				request.setEntity(new UrlEncodedFormEntity(postparams, "UTF-8"));
				HttpClient client = new DefaultHttpClient();
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() == 200) {
					result = EntityUtils.toString(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				String mark[] = result.split(":");
				txtSno.setText(mark[0]);
				txtCh.setText(mark[1]);
				txtMa.setText(mark[2]);
				txtEn.setText(mark[3]);

			}
			super.onPostExecute(result);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtSno = (TextView) findViewById(R.id.txtSno);
		txtCh = (TextView) findViewById(R.id.txtCh);
		txtMa = (TextView) findViewById(R.id.txtMa);
		txtEn = (TextView) findViewById(R.id.txtEn);
		editSno = (EditText) findViewById(R.id.editSno);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (!editSno.getText().toString().equals("")) {
			new NetworkTask().execute(editSno.getText().toString());
		}else{Toast.makeText(getApplicationContext(), "«Î ‰»Î—ß∫≈", 0).show();}
	}

}
