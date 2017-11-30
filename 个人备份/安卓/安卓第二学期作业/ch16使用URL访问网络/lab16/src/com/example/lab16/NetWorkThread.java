package com.example.lab16;

import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class NetWorkThread extends Thread {
     Handler handler;
     String strUrl;
     
     public NetWorkThread(Handler h){
    	 handler=h;
    	 strUrl="";
     }
     public void setUrl(String s){
    	 strUrl=s;
     }
     
     @Override
    public void run() {
    	Message message=new Message();
    	try {
			URL url=new URL(strUrl);
			InputStream is=url.openStream();
			Bitmap bm=BitmapFactory.decodeStream(is);
			is.close();
			message.obj=bm;
		} catch (Exception e) {
			// TODO: handle exception
		}
    	handler.sendMessage(message);
    	super.run();
    }
     
}
