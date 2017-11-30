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
			//定义HttpPost对象，以post方式请求
			HttpPost request = new HttpPost(urlString);
			//定义键值对的集合
			List<NameValuePair> postparams=new ArrayList<NameValuePair>();
			//以键值对的形式添加请求参数
			
			postparams.add(new BasicNameValuePair("sno", params[0]));
			try {
				//将全部参数以实体的格式进行编码与打包
				request.setEntity(new UrlEncodedFormEntity(postparams,"UTF-8"));
				//定义HttpClient
				HttpClient client =new DefaultHttpClient();
				//执行请求，并得到响应的对象
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() == 200) {
					//将响应的内容作为实体整体转换为字符串
					result=EntityUtils.toString(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		} 
	   protected void onPostExecute(String result) {
			if(result!=null){				
	         //将结果字符串按冒号拆分
				String[] mark=result.split(":");
				//将各项值填入对应的控件中，多行代码
				editText.setText("学号:"+ mark[0]);
				txtc.setText("语文:"+mark[1]);
				txts.setText("数学:"+mark[2]);
				txty.setText("英语:"+mark[3]);
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
