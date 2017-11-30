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
		// ListView�İ����ݽṹ
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		//JSON�����ݸ�ʽ�ǹ̶��ģ��������ݸ�ʽ˵������
		String[] subject = { "����", "��ѧ", "Ӣ��" };
		for (int i = 0; i < values.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("subject", subject[i]);
			map.put("mark", String.valueOf(values[i]));
			data.add(map);
		}
		// ����SimpleAdapterʵ����������
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), data,
				android.R.layout.simple_list_item_2, new String[] { "subject",
						"mark" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		lsvMark.setAdapter(adapter);
	}

	// �첽������
	class NetTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			String urlString = "http://10.0.2.2:8080/QueryMarkJSON";
			StringBuilder builder = new StringBuilder();
			builder.append("sno=").append(params[0]);
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// ���ñ�����֧�ֶ�ȡ��Ӧ
				conn.setDoInput(true);
				// ���ñ�����ʹ��post��ʽ�ύ
				conn.setDoOutput(true);
				// �����ύ�����ݸ�ʽ
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				// ��DataOutputStream����ķ�ʽд��
				DataOutputStream output = new DataOutputStream(conn.getOutputStream());
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
				Toast.makeText(getApplicationContext(), "��ѯʧ��",Toast.LENGTH_LONG).show();
				return;
			}
			try {
				// ��JSON�ṹ�ַ�����ת��ΪJSON����
				JSONObject jObj = new JSONObject(result);
				//status����ָʾ��ѯ�Ƿ���ȷ
				Boolean status = jObj.getBoolean("status");
				if (!status) {
					Toast.makeText(getApplicationContext(), "���޴���",Toast.LENGTH_LONG).show();
					return;
				}
				//��ȡstudent����
				JSONObject jStu = jObj.getJSONObject("student");
				//��ȡstudent�����е�mark����
				JSONArray jaMark = jStu.getJSONArray("mark");
				int[] mark = new int[jaMark.length()];
				//��JSON������ת��Ϊ���������
				for (int i = 0; i < jaMark.length(); i++) {
					mark[i] = jaMark.getInt(i);
				}
				//����ʾ
				BindListView(mark);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View v) {
		String sno=editSno.getText().toString();
		//�����첽�����������
		new NetTask().execute(sno);		
	}
}
