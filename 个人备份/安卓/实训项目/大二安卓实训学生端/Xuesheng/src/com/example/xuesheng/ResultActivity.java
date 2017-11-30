package com.example.xuesheng;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.xuesheng.DengluFragment.NetTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {

	private TextView tv;
	private ImageView img;
	private Button btn;
	private Button btnintent;
	private Bundle bundle;
	private WebView web;
	static String kname;
	static String zhuangtaiid;
	String ip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		ip=getString(R.string.ip);
		initView();
		
		initdata();
		
	}
	private void initdata() {
		String zhuangtai="";
		zhuangtaiid="";
		Intent intentvalue = getIntent();
		bundle = intentvalue.getExtras();
		String s=bundle.getString("result");
		final String [] ss=s.split(",");
//		new NetTask().execute(ss[0]);
		String sx="";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  final String time=df.format(new Date());
          try {  
              Date dt1=df.parse(ss[1]);//将字符串转换为date类型  
              Date dt2=df.parse(time);  
              if(dt1.getTime()>dt2.getTime()){  
//                  System.out.println("yes");  
            	  zhuangtai="正常";
            	  zhuangtaiid="0";
              }  
              else  
              {  
//                  System.out.println("no");//运行输出no  
            	  zhuangtai="迟到";
            	  zhuangtaiid="1";
              }  
          } catch (ParseException e) {  
              e.printStackTrace();  
          }  
		if(ss[2].equals("0")){
			sx="上课";
		}else{
			sx="下课";
		}
		
		tv.setText("时间:"+time+","+sx+","+zhuangtai);
		img.setImageBitmap((Bitmap)intentvalue.getParcelableExtra("bitmap"));
		btnintent.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				  web = new WebView(ResultActivity.this);
				  String url=String.format("http://"+ip+":8080/Za/addjilu?kid=%s&sid=%S&time=%s&shangxia=%s&zhuangtaiid=%s", ss[0],CL.sid,time,ss[2],zhuangtaiid);
					web.loadUrl(url);
					setContentView(web);
					ResultActivity.this.finish();
					Toast.makeText(getApplicationContext(), "签到成功", 0).show();
			} 
		});
		
		/*
		 * 点击关闭当前页面
		 * */
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ResultActivity.this.finish();
			}
		});		
	}

	private void initView() {
		tv = (TextView) findViewById(R.id.result_name);
		img = (ImageView) findViewById(R.id.result_bitmap);
		btn = (Button) findViewById(R.id.button_back);
		btnintent = (Button) findViewById(R.id.intent2view);
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
//			web.goBack();
//			return true;
//		}
//		return false;
//		
//	}
/*	class NetTask extends AsyncTask<String, Void, String> {
 		@Override
 		protected String doInBackground(String... params) {
 			String urlString = "http://"+ip+":8080/Za/qkechengbykid";
 			StringBuilder builder = new StringBuilder();
 			builder.append("kid=").append(params[0]);
 			try {
 				URL url = new URL(urlString);
 				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 				conn.setDoInput(true);
 				conn.setDoOutput(true);
 				conn.setRequestProperty("Content-Type",
 						"application/x-www-form-urlencoded");
 				DataOutputStream output = new DataOutputStream(conn.getOutputStream());
 				output.writeBytes(builder.toString());
 				output.flush();
 				output.close();
 				String result = "";
 				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
 					BufferedReader in = new BufferedReader(
 							new InputStreamReader(conn.getInputStream(),
 									"UTF-8"));// 鑾峰彇鐨勬暟鎹紪鐮�
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
 		protected void onPostExecute(String result) {
 			if(result==null){
 				Toast.makeText(getApplicationContext(), "失败", 0).show();
 				return;
 			}
 			try {
 				JSONObject jObj = new JSONObject(result);
 				Boolean status = jObj.getBoolean("status");
 				if (!status) {
 					Toast.makeText(getApplicationContext(), "异常", 0).show();
 					return;
 				}
				kname= jObj.getString("kname");
 				
 			} catch (JSONException e) {
 				e.printStackTrace();
 			}
 		}
	}*/

}

