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

	// �첽������
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
				// ���ñ�����֧�ֶ�ȡ��Ӧ
				conn.setDoInput(true);
				// ���ñ�����ʹ��post��ʽ�ύ
				conn.setDoOutput(true);
				// �����ύ�����ݸ�ʽ
				conn.setRequestProperty("Content-Type",	"application/x-www-form-urlencoded");
				// ��DataOutputStream����ķ�ʽд��
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
				// ���ַ������ֽ�����ʽд���ύ
				output.writeBytes(builder.toString());
				// ���
				output.flush();
				// �ر����
				output.close();
				StringBuilder result = new StringBuilder();
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conn.getInputStream(),
									"UTF-8"));// ��ȡ�����ݱ���
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
				Toast.makeText(getApplicationContext(), "��ѯʧ��",	Toast.LENGTH_LONG).show();
				return;
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory	.newInstance();
			try {
				// DOM����
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(); // ����һ��xml�ĵ�������Դ.
				//��xml��ʽ���ַ�������������
				is.setCharacterStream(new StringReader(result));
				//ʹ��DOM��������XML��ʽ������ת��Ϊ�ĵ�����
				Document document = builder.parse(is);
				//��ȡ��Ԫ��
				Element root = document.getDocumentElement();
				//��ȡstatusԪ�أ�statusԪ���Ǹ�Ԫ�صĵ�һ���ӽڵ㣩
				Element status = (Element) root.getFirstChild();
				//�ж�StatusԪ�ص�����ֵ
				if (status.getTextContent().equals("false")) {
					Toast.makeText(getApplicationContext(), "��������",
							Toast.LENGTH_SHORT).show();
					return;
				}
			    //��ȡ���е�SubjectԪ��
				NodeList subjects = root.getElementsByTagName("Subject");
				List<Map<String, String>> listmark = new ArrayList<Map<String, String>>();
				//�������е�SubjectԪ��
				for (int i = 0; i < subjects.getLength(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					//��i��SubjectԪ��
					Element sub = (Element) subjects.item(i);
					//��ȡSubjectԪ�ص�name���ԣ�attribute��
					map.put("subject", sub.getAttribute("name"));
					//��ȡSubjectԪ�ص�����ֵ
					map.put("mark", sub.getTextContent());
					listmark.add(map);
				}
				//�󶨵�ListView
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
		// ����SimpleAdapterʵ����������
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
		// �����첽�����������
		new NetTask().execute(sno,password);
	}
}
