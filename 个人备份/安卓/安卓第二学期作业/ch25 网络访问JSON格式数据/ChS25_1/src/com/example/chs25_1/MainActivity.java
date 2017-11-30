package com.example.chs25_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button btnQuery;
	EditText editSno;
	ListView lsvMark;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnQuery=(Button)findViewById(R.id.btnQuery);
		editSno=(EditText)findViewById(R.id.editSno);
		lsvMark=(ListView)findViewById(R.id.lsvMark);		
		btnQuery.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {	
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void BindListView(int[] values) {
		// ListView的绑定数据结构
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		//JSON的数据格式是固定的，根据数据格式说明排列
		String[] subject = { "语文", "数学", "英语" };
		for (int i = 0; i < values.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("subject", subject[i]);
			map.put("mark", String.valueOf(values[i]));
			data.add(map);
		}
		// 利用SimpleAdapter实现数据适配
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), data,
				android.R.layout.simple_list_item_2, new String[] { "subject",
						"mark" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		lsvMark.setAdapter(adapter);
	}

	// 异步任务类
	class NetTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			String urlString = "http://10.0.2.2:8080/QueryMarkJSON";
			StringBuilder builder = new StringBuilder();
			builder.append("sno=").append(params[0]);
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// 设置本连接支持读取响应
				conn.setDoInput(true);
				// 设置本连接使用post方式提交
				conn.setDoOutput(true);
				// 设置提交的数据格式
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				// 以DataOutputStream对象的方式写入
				DataOutputStream output = new DataOutputStream(conn.getOutputStream());
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
				Toast.makeText(getApplicationContext(), "查询失败",Toast.LENGTH_LONG).show();
				return;
			}
			try {
				// 将JSON结构字符串，转换为JSON对象
				JSONObject jObj = new JSONObject(result);
				//status属性指示查询是否正确
				Boolean status = jObj.getBoolean("status");
				if (!status) {
					Toast.makeText(getApplicationContext(), "查无此人",Toast.LENGTH_LONG).show();
					return;
				}
				//获取student对象
				JSONObject jStu = jObj.getJSONObject("student");
				//获取student对象中的mark数组
				JSONArray jaMark = jStu.getJSONArray("mark");
				int[] mark = new int[jaMark.length()];
				//将JSON的数组转换为程序的数组
				for (int i = 0; i < jaMark.length(); i++) {
					mark[i] = jaMark.getInt(i);
				}
				//绑定显示
				BindListView(mark);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View v) {
		String sno=editSno.getText().toString();
		//启动异步任务访问网络
		new NetTask().execute(sno);		
	}
}
