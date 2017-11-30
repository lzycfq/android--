package cn.itcast.login;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class LoginService {
	//ʹ��HttpURLConnection   GET��ʽ�ύ����
	public static String loginByGet(String username, String password) {
		try {
              //ƴװURL ע��Ϊ�˷�ֹ���� ������Ҫ���������б���
			String path = "http://172.16.26.59:8080/Web/LoginServlet?username="
					+ URLEncoder.encode(username, "UTF-8")
					+ "&password="
					+ URLEncoder.encode(password);
              //����URLʵ��
			URL url = new URL(path);
               //��ȡHttpURLConnection����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              conn.setConnectTimeout(5000);        //���ó�ʱʱ��
			conn.setRequestMethod("GET");        //���÷��ʷ�ʽ
			int code = conn.getResponseCode(); //�õ����ص�״̬��
			if (code == 200) {                    // ����ɹ�
				InputStream is = conn.getInputStream();  
				String text = StreamTools.readInputStream(is); 
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//ʹ��HttpURLConnection  POST��ʽ�ύ����
	public static String loginByPost(String username, String password) {
		try {
              //Ҫ���ʵ���Դ·��
			String path = "http://172.16.26.59:8080/Web/LoginServlet";
			//����URL��ʵ��
               URL url = new URL(path);
               //��ȡHttpURLConnection����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//���ó�ʱʱ��
              conn.setConnectTimeout(5000);
              //ָ������ʽ
			conn.setRequestMethod("POST");
			//׼������ ����������
			String data = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password);
              //��������ͷ
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", data.length() + "");
              //������д��������
			conn.setDoOutput(true);
               //�õ������
			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());           //������д���������
			int code = conn.getResponseCode(); //�ǵ����������ص�״̬��
			if (code == 200) {
                   //�õ����������ص�������
				InputStream is = conn.getInputStream();
                   //��������ת�����ַ���
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// ����httpclient get�ύ����
	public static String loginByClientGet(String username, String password) {
		try {
			//1 ������HttpClient����
			HttpClient client = new DefaultHttpClient();
			//2��ƴװ·��,ע�⽫��������
			String path = "http://172.16.26.59:8080/Web/LoginServlet?username="
					+ URLEncoder.encode(username)
					+ "&password="
					+ URLEncoder.encode(password);
			//3��GET��ʽ����
			HttpGet httpGet = new HttpGet(path);
              //4���õ����������ص�HttpResponse����
			HttpResponse response = client.execute(httpGet);
              //5���õ�״̬��
			int code = response.getStatusLine().getStatusCode(); 
			if (code == 200) { 
                   //��ȡ������
				InputStream is = response.getEntity().getContent();
                    //��������ת�����ַ���
				String text = StreamTools.readInputStream(is);			
	              return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 //����httpclient post�ύ����
	public static String loginByClientPost(String username, String password) {
		try {
			//1����ȡHttpClient����
			HttpClient client = new DefaultHttpClient();
			//2��ָ�����ʵ�ַ
			String path = "http://172.16.26.59:8080/Web/LoginServlet";
              //3��POST��ʽ��������
			HttpPost httpPost = new HttpPost(path);
			//4��ָ��Ҫ�ύ������ʵ��
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("username", username));
			parameters.add(new BasicNameValuePair("password", password));
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			//5��������������õ����������ص���Ϣ 
			HttpResponse  response =   client.execute(httpPost);
			int code = response.getStatusLine().getStatusCode(); //�õ�״̬��
			if (code == 200) { //���ʳɹ�
				InputStream is = response.getEntity().getContent();
				//��������ת�����ַ���
				String text = StreamTools.readInputStream(is); 
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

