package com.example.lab17;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtil {

	// urlstring为连接服务器的地址，keys为参数提交从参数名，values为参赛的值
	public static String sendGet(String urlstring, String[] keys, String[] values) {
		String result = "";
		StringBuilder builder = new StringBuilder(urlstring);
		builder.append(keys[0]).append(values[0]);
		builder.append(keys[1]).append(values[1]);
		URL url;
		try {
			url = new URL(builder.toString());
			//
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();				
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String line;
				//String result = "";
				while ((line = in.readLine()) != null) {
					result = line;
				}					
				return result;
			}
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String sendPost(String urlstring, String[] keys, String[] values) {
		String result = "";
		StringBuilder builder = new StringBuilder();
		try {
			//将输入的内容进行utf8编码
			builder.append(keys[0]).append(URLEncoder.encode(values[0],"UTF-8"));
		} catch (UnsupportedEncodingException ex) {				
			ex.printStackTrace();
		}
		builder.append(keys[1]).append(values[1]);			
		
		try {							
			URL url = new URL(urlstring);				
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();	
			//设置本连接支持读取响应
			conn.setDoInput(true);
			//设置本连接使用post方式提交
			conn.setDoOutput(true);
			//设置提交的数据格式
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			//以DataOutputStream对象的方式写入
			DataOutputStream output=new DataOutputStream(conn.getOutputStream());
			//将字符串以字节流形式写入提交
			output.writeBytes(builder.toString());
			//清空
			output.flush();
			//关闭输出
			output.close();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(conn.getInputStream(),"UTF-8"));//获取的数据编码
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

		return result;
	}
}
