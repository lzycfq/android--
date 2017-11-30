package com.example.lab25;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	EditText editNo, editPassword;
	Button btnLogin;
	String sno,name;

	class NetTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String urlstring = "http://10.0.2.2:8080/LoginCheckJSON";
			StringBuilder builder = new StringBuilder();
			try {
				// ����������ݽ���utf8����
				builder.append("sno=").append(
						URLEncoder.encode(params[0], "UTF-8"));
			} catch (UnsupportedEncodingException ex) {
				ex.printStackTrace();
			}
			builder.append("&password=").append(params[1]);
			try {
				URL url = new URL(urlstring);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// ���ñ�����֧�ֶ�ȡ��Ӧ
				conn.setDoInput(true);
				// ���ñ�����ʹ��post��ʽ�ύ
				conn.setDoOutput(true);
				// �����ύ�����ݸ�ʽ
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				// ��DataOutputStream����ķ�ʽд��
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
				// ���ַ������ֽ�����ʽд���ύ
				output.writeBytes(builder.toString());
				// ���
				output.flush();
				// �ر����
				output.close();
				String result = "";
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conn.getInputStream(),
									"UTF-8"));// ��ȡ�����ݱ���
					String line;

					while ((line = in.readLine()) != null) {
						result = line;
					}
					in.close();
				}
				conn.disconnect();
				return result;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result==null){
				Toast.makeText(getApplicationContext(), "��½ʧ��", 0).show();
			}
			try {
				JSONObject jObj=new JSONObject(result);
				boolean status=jObj.getBoolean("status");
				if(!status){
					Toast.makeText(getBaseContext(), "��ѧ�Ų�����", 0).show();
				}
				JSONObject stu=jObj.getJSONObject("student");
				sno=stu.getString("sno");
				name=stu.getString("name");
				Switch(sno, name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editNo=(EditText)findViewById(R.id.editNo);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String no=editNo.getText().toString();
		String password=editPassword.getText().toString();
		new NetTask().execute(no,password);
		/*Intent i=new Intent(this,LoginActivity.class);
		i.putExtra("no", sno);
		i.putExtra("name", name);
		startActivity(i);*/
		//Toast.makeText(getBaseContext(), sno+name, 0).show();
	}
	public void Switch(String n,String na){
		Intent i=new Intent(this,LoginActivity.class);
		i.putExtra("no", n);
		i.putExtra("name", na);
		startActivity(i);
	}
	

}
