package com.example.ch18zy;

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

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.NameValueTable;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
   EditText editText;
   Button btnclient;
   TextView txtc,txts,txty;
   class networkTask extends AsyncTask<String, Void, String>{
	   protected String doInBackground(String... params) {
			String urlString ="http://10.0.2.2:8080/QueryMark";
			String result = null;
			//����HttpPost������post��ʽ����
			HttpPost request = new HttpPost(urlString);
			//�����ֵ�Եļ���
			List<NameValuePair> postparams=new ArrayList<NameValuePair>();
			//�Լ�ֵ�Ե���ʽ����������
			
			postparams.add(new BasicNameValuePair("sno", params[0]));
			try {
				//��ȫ��������ʵ��ĸ�ʽ���б�������
				request.setEntity(new UrlEncodedFormEntity(postparams,"UTF-8"));
				//����HttpClient
				HttpClient client =new DefaultHttpClient();
				//ִ�����󣬲��õ���Ӧ�Ķ���
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() == 200) {
					//����Ӧ��������Ϊʵ������ת��Ϊ�ַ���
					result=EntityUtils.toString(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		} 
	   protected void onPostExecute(String result) {
			if(result!=null){				
	         //������ַ�����ð�Ų��
				String[] mark=result.split(":");
				//������ֵ�����Ӧ�Ŀؼ��У����д���
				editText.setText("ѧ��:"+ mark[0]);
				txtc.setText("����:"+mark[1]);
				txts.setText("��ѧ:"+mark[2]);
				txty.setText("Ӣ��:"+mark[3]);
	       }
				super.onPostExecute(result);
			}

  
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.edittext);
        btnclient=(Button)findViewById(R.id.btnclient);
        txtc=(TextView)findViewById(R.id.txtc);
        txts=(TextView)findViewById(R.id.txts);
        txty=(TextView)findViewById(R.id.txty);
        btnclient.setOnClickListener(this);
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
		String sno=editText.getText().toString();
		new networkTask().execute(sno);
	}
    
}
