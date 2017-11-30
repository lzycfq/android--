package com.example.chs17_2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
			
			StringBuilder builder = new StringBuilder();
			try {
				//将输入的内容进行utf8编码
				builder.append("sno=").append(URLEncoder.encode(params[0],"UTF-8"));
			} catch (UnsupportedEncodingException ex) {				
				ex.printStackTrace();
			}
			builder.append("&password=").append(params[1]);			
			
			try {							
				URL url = new URL(urlString);				
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();	
				//设置本连接支持读取响应
				conn.setDoInput(true);
				//设置本连接使用post方式提交
				conn.setDoOutput(true);
				//设置提交的数据格式
				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				//以DataOutputStream对象的方式写入
				DataOutputStream output=new DataOutputStream(conn.getOutputStream());
				//将字符串以字节流形式写入提交
				output.writeBytes(builder.toString());
				//清空
				output.flush();
				//关闭输出
				output.close();
				String result = "";
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conn.getInputStream(),"UTF-8"));//获取的数据编码
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
			if (result.equals("pass"))
				Toast.makeText(getApplicationContext(), "登录成功",
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getApplicationContext(), "登录失败",
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
