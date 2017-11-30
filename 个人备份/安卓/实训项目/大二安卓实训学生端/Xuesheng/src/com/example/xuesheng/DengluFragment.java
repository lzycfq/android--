package com.example.xuesheng;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class DengluFragment extends DialogFragment {
	EditText edsno;
	EditText edpassword;
	String ip;
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		//定义布局对象
		ip=getString(R.string.ip);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.activity_denglu, null);
		edsno = (EditText) view.findViewById(R.id.edsno);
		edpassword = (EditText) view.findViewById(R.id.edpassword);
		builder.setView(view)
				.setPositiveButton("登录",
						new DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int id){
								String sid=edsno.getText().toString();
								String password=edpassword.getText().toString();
								new NetTask().execute(sid,password);
							}
						}).setNegativeButton("取消", null);
		return builder.create();
	}
	class NetTask extends AsyncTask<String, Void, String> {
 		@Override
 		protected String doInBackground(String... params) {
 			String urlString = "http://"+ip+":8080/Za/login";
 			StringBuilder builder = new StringBuilder();
 			builder.append("sid=").append(params[0]).append("&password=").append(params[1]);
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
// 				Toast.makeText(getActivity(), "连接失败", 0).show();
 				System.out.println("失败");
 				return;
 			}
 			try {
 				JSONObject jObj = new JSONObject(result);
 				Boolean status = jObj.getBoolean("status");
 				if (!status) {
 					System.out.println("登录失败");
// 					Toast.makeText(getActivity(), "密码错误", 0).show();
 					return;
 				}
				String sname= jObj.getString("sname");
				String sid= jObj.getString("sid");
//				Toast.makeText(getActivity(), "登录成功", 0).show();
 				CL.setSid(sid);
 				CL.setSname(sname);
 				Guanli.a(sname);
 			} catch (JSONException e) {
 				e.printStackTrace();
 			}
 		}


 	}
}
