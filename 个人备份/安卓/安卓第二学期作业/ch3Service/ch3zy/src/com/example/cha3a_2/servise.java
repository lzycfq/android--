package com.example.cha3a_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class servise extends Service {
	private int count;
	private boolean quit;
	// ����onBinder���������صĶ���
	private MyBinder binder = new MyBinder();
	// ͨ���̳�Binder��ʵ��IBinder��
	public class MyBinder extends Binder{
		public int getCount(){
			// ��ȡService������״̬��count
			return count;
		}
	}
	// ����ʵ�ֵ�onBind(����
	                              
	// Service������ʱ�ص��÷�����
	@Override
	public void onCreate(){
		super.onCreate();
		System.out.println("Service is Created");
		// ����һ���̡߳���̬���޸�count״ֵ̬
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
	// Service���Ͽ�����ʱ�ص��÷���
	                                
	// Service���ر�֮ǰ�ص���
                                  	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
