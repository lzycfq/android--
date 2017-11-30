package com.example.cha3a_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class servise extends Service {
	private int count;
	private boolean quit;
	// 定义onBinder方法所返回的对象
	private MyBinder binder = new MyBinder();
	// 通过继承Binder来实现IBinder类
	public class MyBinder extends Binder{
		public int getCount(){
			// 获取Service的运行状态：count
			return count;
		}
	}
	// 必须实现的onBind(方法
	                              
	// Service被创建时回调该方法。
	@Override
	public void onCreate(){
		super.onCreate();
		System.out.println("Service is Created");
		// 启动一条线程、动态地修改count状态值
		new Thread(){
			@Override
			public void run(){
				while (!quit){
					try{
						Thread.sleep(1000);
					}
					catch (InterruptedException e){}
					count++;
				}
			}
		}.start();		
	}
	// Service被断开连接时回调该方法
	                                
	// Service被关闭之前回调。
                                  	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
