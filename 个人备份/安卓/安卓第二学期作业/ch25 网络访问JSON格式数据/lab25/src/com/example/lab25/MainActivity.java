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
				// 将输入的内容进行utf8编码
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
				// 设置本连接支持读取响应
				conn.setDoInput(true);
				// 设置本连接使用post方式提交
				conn.setDoOutput(true);
				// 设置提交的数据格式
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				// 以DataOutputStream对象的方式写入
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
				// 将字符串以字节流形式写入提交
				output.writeBytes(builder.toString());
				// 清空
				output.flush();
				// 关闭输出
				output.close();
				String result = "";
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conn.getInputStream(),
									"UTF-8"));// 获取的数据编码
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
				Toast.makeText(getApplicationContext(), "登陆失败", 0).show();
			}
			try {
				JSONObject jObj=new JSONObject(result);
				boolean status=jObj.getBoolean("status");
				if(!status){
					Toast.makeText(getBaseContext(), "该学号不存在", 0).show();
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
