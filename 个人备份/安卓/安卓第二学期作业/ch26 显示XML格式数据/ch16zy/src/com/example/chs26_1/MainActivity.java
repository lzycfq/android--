package com.example.chs26_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
	EditText editSno,editPassword;
	ListView lsvMark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnQuery = (Button) findViewById(R.id.btnQuery);
		btnQuery.setOnClickListener(this);
		editPassword=(EditText)findViewById(R.id.editPassword);
		editSno = (EditText) findViewById(R.id.editSno);
		lsvMark = (ListView) findViewById(R.id.lsvMark);
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

	// 异步任务类
	class NetTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			String urlString = "http://10.0.2.2:8080/QueryMarkXML";
			StringBuilder builder = new StringBuilder();
			builder.append("password=").append(params[1]);
			builder.append("sno=").append(params[0]);
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// 设置本连接支持读取响应
				conn.setDoInput(true);
				// 设置本连接使用post方式提交
				conn.setDoOutput(true);
				// 设置提交的数据格式
				conn.setRequestProperty("Content-Type",	"application/x-www-form-urlencoded");
				// 以DataOutputStream对象的方式写入
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
				// 将字符串以字节流形式写入提交
				output.writeBytes(builder.toString());
				// 清空
				output.flush();
				// 关闭输出
				output.close();
				StringBuilder result = new StringBuilder();
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conn.getInputStream(),
									"UTF-8"));// 获取的数据编码
					String line;
					while ((line = in.readLine()) != null) {
						result.append(line);
					}
					in.close();
				}
				conn.disconnect();
				return result.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result == null) {
				Toast.makeText(getApplicationContext(), "查询失败",	Toast.LENGTH_LONG).show();
				return;
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory	.newInstance();
			try {
				// DOM工厂
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(); // 定义一个xml文档的输入源.
				//将xml格式的字符串放入输入流
				is.setCharacterStream(new StringReader(result));
				//使用DOM工厂对象将XML格式的数据转换为文档对象
				Document document = builder.parse(is);
				//获取根元素
				Element root = document.getDocumentElement();
				//获取status元素（status元素是根元素的第一个子节点）
				Element status = (Element) root.getFirstChild();
				//判断Status元素的内容值
				if (status.getTextContent().equals("false")) {
					Toast.makeText(getApplicationContext(), "查无数据",
							Toast.LENGTH_SHORT).show();
					return;
				}
			    //获取所有的Subject元素
				NodeList subjects = root.getElementsByTagName("Subject");
				List<Map<String, String>> listmark = new ArrayList<Map<String, String>>();
				//遍历所有的Subject元素
				for (int i = 0; i < subjects.getLength(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					//第i个Subject元素
					Element sub = (Element) subjects.item(i);
					//获取Subject元素的name属性（attribute）
					map.put("subject", sub.getAttribute("name"));
					//获取Subject元素的内容值
					map.put("mark", sub.getTextContent());
					listmark.add(map);
				}
				//绑定到ListView
				BindListView(listmark);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void BindListView(List<Map<String, String>> data) {
		// 利用SimpleAdapter实现数据适配
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), data,
				android.R.layout.simple_list_item_2, new String[] { "subject",
						"mark" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		lsvMark.setAdapter(adapter);
	}
protected void btn(String Sno,String Password){
	
}
	@Override
	public void onClick(View v) {
		String sno = editSno.getText().toString();
		String password=editPassword.getText().toString();
		// 启动异步任务访问网络
		new NetTask().execute(sno,password);
	}
}
