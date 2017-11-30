package com.example.ch16zy_1;

import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class NetworkThread extends Thread {
	//定义handler对象
	 Handler handler;
	//定义字符串类型的url地址
	String strUrl;
	
	public NetworkThread(Handler h) {
		handler = h;
		strUrl="";
	}
	
	public void setUrl(String s){
		//指定图片路径
		this.strUrl=s;
		
	}
	public void run() {
		Message message = new Message();
		try {
			//构造URL对象
			try{
				  URL url=new URL(strUrl);
				//通过URL打开输入流					
				 InputStream is=url.openStream();
					//将输入流的内容构造Bitmap
				 Bitmap bitmap=BitmapFactory.decodeStream(is);
				 is.close();
					//设置bitmap作为回传数据
				 message.obj=bitmap;		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//回传消息
			  handler.sendMessage(message);
		      super.run();
		
	}
	}
		
	}
	

	
