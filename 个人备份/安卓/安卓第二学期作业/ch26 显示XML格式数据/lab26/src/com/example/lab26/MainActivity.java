package com.example.lab26;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

public class MainActivity extends Activity implements OnClickListener{
	
	EditText editNo,editPassword;
	Button btnLogin;
	TextView txtNo,txtName;
	String no,name;
	
	class NetTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String urlstring = "http://10.0.2.2:8080/LoginCheckXML";
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
			if(result==null){
				Toast.makeText(getApplicationContext(), "��ѯʧ��", 0).show();
				return;
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory	.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(); 
				is.setCharacterStream(new StringReader(result));
				Document document = builder.parse(is);
				Element root = document.getDocumentElement();
				Element status = (Element) root.getFirstChild();
				if (status.getTextContent().equals("false")) {
					Toast.makeText(getApplicationContext(), "��������",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Element stu=(Element)root.getLastChild();
				Element sno=(Element)stu.getFirstChild();
				Element sname=(Element)stu.getLastChild();
				no=sno.getTextContent();
				name=sname.getTextContent();
				txtNo.setText("ѧ�ţ�"+no);
				txtName.setText("������"+name);
				
               
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
        txtNo=(TextView)findViewById(R.id.txtNo);
        txtName=(TextView)findViewById(R.id.txtName);
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
		String ino=editNo.getText().toString();
		String pw=editPassword.getText().toString();
		new NetTask().execute(ino,pw);
		
	}
    
}
