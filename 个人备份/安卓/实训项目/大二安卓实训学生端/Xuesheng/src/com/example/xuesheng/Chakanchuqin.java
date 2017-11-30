package com.example.xuesheng;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Chakanchuqin extends Fragment implements OnClickListener {
	Button btn_bykechengchaxun;
	Button btn_bychuqinchaxun;
	static EditText ed_kecheng;
	RadioButton radio_zhengchang;
	RadioButton radio_chidao;
	RadioButton radio_kuangke;
	static ArrayList<Map<String, String>> listkecheng;
	static ArrayList<Map<String, String>> listjilu;
	static ArrayList<String> listkechengid;
	static String kid;
	ListView list;
	String ip;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ip=getString(R.string.ip);
		View view = inflater.inflate(R.layout.chakanchuqin, container, false);
		listkecheng = new ArrayList<Map<String, String>>();
		listjilu = new ArrayList<Map<String, String>>();
		listkechengid = new ArrayList<String>();
		list = (ListView) view.findViewById(R.id.list);
		radio_zhengchang = (RadioButton) view
				.findViewById(R.id.radio_zhengchang);
		radio_chidao = (RadioButton) view.findViewById(R.id.radio_chidao);
		radio_kuangke = (RadioButton) view.findViewById(R.id.radio_kuangke);

		ed_kecheng = (EditText) view.findViewById(R.id.ed_kecheng);
		btn_bychuqinchaxun = (Button) view
				.findViewById(R.id.btn_bychuqinchaxun);
		btn_bykechengchaxun = (Button) view
				.findViewById(R.id.btn_bykechengchaxun);
		new NetTask().execute(CL.sid);
		ed_kecheng.setOnClickListener(this);
		btn_bychuqinchaxun.setOnClickListener(this);
		btn_bychuqinchaxun.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (CL.sid == null) {
						Toast.makeText(getActivity(), "请先登录", 0).show();
						return true;
					} else {
						String chuqin = "";
						if (radio_zhengchang.isChecked()) {
							chuqin = "0";
						} else if (radio_chidao.isChecked()) {
							chuqin = "1";
						} else if (radio_kuangke.isChecked()) {
							chuqin = "2";
						}
						new NetTask_jilubychuqin().execute(chuqin, CL.sid);
						return false;
					}
				} else {
					return false;
				}
			}
		});

		btn_bykechengchaxun.setOnClickListener(this);
		btn_bykechengchaxun.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (CL.sid == null) {
						Toast.makeText(getActivity(), "请先登录", 0).show();
						return true;
					} else {
						if(ed_kecheng.getText().toString().length()<=0){
							Toast.makeText(getActivity(), "请选择课程", 0).show();
							return true;
						}else{
						new NetTask_jilubykid().execute(kid, CL.sid);
						return false;
						}
					}
				}
				return false;
			}
		});
		return view;
	}

	public void onClick(View v) {
		if (v.getId() == R.id.ed_kecheng) {
			if (CL.sid == null) {
				Toast.makeText(getActivity(), "请先登录", 0).show();
			} else {
				showkecheng();
			}
		}
		if (v.getId() == R.id.btn_bykechengchaxun) {
			showjilu();
		}
		if (v.getId() == R.id.btn_bychuqinchaxun) {
			showjilu();
		}
	}

	public void showjilu() {
		SimpleAdapter jiluAdapter = new SimpleAdapter(getActivity(), listjilu,
				R.layout.list_jilu, new String[] { "time", "kname","sname", 
						"shangxiake", "zhuangtai" }, new int[] {
						R.id.text_time, R.id.text_kname, R.id.text_sname,R.id.text_shangxiake,
						 R.id.text_zhuangtai });
		list.setAdapter(jiluAdapter);
	}

	public void showkecheng() {
		AlertDialog.Builder builder = new Builder(getActivity()); // 创建警告对话框
		SimpleAdapter bookAdapter = new SimpleAdapter(getActivity(),
				listkecheng, R.layout.t, new String[] { "kecheng" },
				new int[] { R.id.time });
		builder.setAdapter(bookAdapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				ed_kecheng.setText(listkecheng.get(which).get("kecheng")
						.toString());
				kid = listkechengid.get(which);
			}
		});
		builder.create().show(); // 创建对话框并显示
	}

	class NetTask extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... params) {
			String urlString = "http://"+ip+":8080/Za/qkechengbysid";
			StringBuilder builder = new StringBuilder();
			builder.append("sid=").append(params[0]);
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
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
			if (result == null) {
				Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
				return;
			}
			try {
				JSONObject jObj = new JSONObject(result);
				Boolean status = jObj.getBoolean("status");
				if (!status) {
					Toast.makeText(getActivity(), "查询失败", 0).show();
					return;
				}
				// {'status':true,'kecheng':[{kid:009,kname:魂斗罗}]}
				JSONArray array = jObj.getJSONArray("kecheng");

				Map<String, String> mapkecheng = new HashMap<String, String>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject jobject = array.getJSONObject(i);
					mapkecheng = new HashMap<String, String>();
					listkechengid.add(jobject.getString("kid"));
					mapkecheng.put("kecheng", jobject.getString("kname"));
					listkecheng.add(mapkecheng);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	class NetTask_jilubykid extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... params) {
			String urlString = "http://"+ip+":8080/Za/qjilubykidandsid";
			StringBuilder builder = new StringBuilder();
			builder.append("kid=").append(params[0]).append("&sid=")
					.append(params[1]);
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
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
			if (result == null) {
				Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
				return;
			}
			try {
				JSONObject jObj = new JSONObject(result);
				Boolean status = jObj.getBoolean("status");
				if (!status) {
					Toast.makeText(getActivity(), "查询失败", 0).show();
					return;
				}
				// {'status':true,'jilu':[{time:2016-12-27
				// 14:47:50.0,kname:开心消消乐,shangxiake:上课,sname:小红,zhuangtai:迟到}]}
				JSONArray array = jObj.getJSONArray("jilu");
				listjilu.clear();
				Map<String, String> mapjilu = new HashMap<String, String>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject jobject = array.getJSONObject(i);
					mapjilu = new HashMap<String, String>();
					String time=jobject.getString("time").replace("*", ":");
					mapjilu.put("time",time);
					mapjilu.put("kname", jobject.getString("kname"));
					mapjilu.put("shangxiake", jobject.getString("shangxiake"));
					mapjilu.put("sname", jobject.getString("sname"));
					mapjilu.put("zhuangtai", jobject.getString("zhuangtai"));
					listjilu.add(mapjilu);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	class NetTask_jilubychuqin extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... params) {
			String urlString = "http://"+ip+":8080/Za/qjilubyzhuangtaiandsid";
			StringBuilder builder = new StringBuilder();
			builder.append("zhuangtai=").append(params[0]).append("&sid=")
					.append(params[1]);
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				DataOutputStream output = new DataOutputStream(
						conn.getOutputStream());
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
			if (result == null) {
				Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
				return;
			}
			listjilu.clear();
			try {
				JSONObject jObj = new JSONObject(result);
				Boolean status = jObj.getBoolean("status");
				if (!status) {
					Toast.makeText(getActivity(), "查询失败", 0).show();
					return;
				}
				// {'status':true,'jilu':[{time:2016-12-27
				// 14:47:50.0,kname:开心消消乐,shangxiake:上课,sname:小红,zhuangtai:迟到}]}
				JSONArray array = jObj.getJSONArray("jilu");
				Map<String, String> mapjilu = new HashMap<String, String>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject jobject = array.getJSONObject(i);
					mapjilu = new HashMap<String, String>();
					String time=jobject.getString("time").replace("*", ":");
					mapjilu.put("time", time);
					mapjilu.put("kname", jobject.getString("kname"));
					mapjilu.put("shangxiake", jobject.getString("shangxiake"));
					mapjilu.put("sname", jobject.getString("sname"));
					mapjilu.put("zhuangtai", jobject.getString("zhuangtai"));
					listjilu.add(mapjilu);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}