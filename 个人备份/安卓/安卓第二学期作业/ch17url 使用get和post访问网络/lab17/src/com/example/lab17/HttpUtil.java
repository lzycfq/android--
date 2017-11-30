package com.example.lab17;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtil {

	// urlstringΪ���ӷ������ĵ�ַ��keysΪ�����ύ�Ӳ�������valuesΪ������ֵ
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
			//����������ݽ���utf8����
			builder.append(keys[0]).append(URLEncoder.encode(values[0],"UTF-8"));
		} catch (UnsupportedEncodingException ex) {				
			ex.printStackTrace();
		}
		builder.append(keys[1]).append(values[1]);			
		
		try {							
			URL url = new URL(urlstring);				
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();	
			//���ñ�����֧�ֶ�ȡ��Ӧ
			conn.setDoInput(true);
			//���ñ�����ʹ��post��ʽ�ύ
			conn.setDoOutput(true);
			//�����ύ�����ݸ�ʽ
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			//��DataOutputStream����ķ�ʽд��
			DataOutputStream output=new DataOutputStream(conn.getOutputStream());
			//���ַ������ֽ�����ʽд���ύ
			output.writeBytes(builder.toString());
			//���
			output.flush();
			//�ر����
			output.close();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(conn.getInputStream(),"UTF-8"));//��ȡ�����ݱ���
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
