package com.example.ch16zy_1;

import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class NetworkThread extends Thread {
	//����handler����
	 Handler handler;
	//�����ַ������͵�url��ַ
	String strUrl;
	
	public NetworkThread(Handler h) {
		handler = h;
		strUrl="";
	}
	
	public void setUrl(String s){
		//ָ��ͼƬ·��
		this.strUrl=s;
		
	}
	public void run() {
		Message message = new Message();
		try {
			//����URL����
			try{
				  URL url=new URL(strUrl);
				//ͨ��URL��������					
				 InputStream is=url.openStream();
					//�������������ݹ���Bitmap
				 Bitmap bitmap=BitmapFactory.decodeStream(is);
				 is.close();
					//����bitmap��Ϊ�ش�����
				 message.obj=bitmap;		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�ش���Ϣ
			  handler.sendMessage(message);
		      super.run();
		
	}
	}
		
	}
	

	
