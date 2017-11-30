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
			if(result==null){
				Toast.makeText(getApplicationContext(), "查询失败", 0).show();
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
					Toast.makeText(getApplicationContext(), "查无数据",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Element stu=(Element)root.getLastChild();
				Element sno=(Element)stu.getFirstChild();
				Element sname=(Element)stu.getLastChild();
				no=sno.getTextContent();
				name=sname.getTextContent();
				txtNo.setText("学号："+no);
				txtName.setText("姓名："+name);
				
               
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
