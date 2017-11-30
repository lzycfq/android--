package com.example.chs3_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BindService extends Service {
	//��¼�ۼ���ֵ
	private int count;
	//����Ƿ��˳�
	private boolean quit;
	//����onBinder���������صĶ���
	private MyBinder binder=new MyBinder();
	//�̳���Binder����ʵ��IBinder�ӿ�		
	public class MyBinder extends Binder{
		public int getCount(){
			//���ص�ǰ�ۼӵ�ֵ
			return count;
		}
	}
	@Override
	//����ʵ�ֵķ���
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "Binded", Toast.LENGTH_SHORT).show();
		// ���ذ�IBinder����
		return binder;
	}
	
	@Override
	//Service������ʱ�ķ���
	public void onCreate() {		
		super.onCreate();
		Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
		//�����߳�ÿ1���ۼ�1
		new Thread(){
			public void run(){
				while(!quit){
					try{
						Thread.sleep(1000);
					}
					catch(Exception e){}
					count++;
				}
			}
		}.start();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(getApplicationContext(), 
				"UnBinded", Toast.LENGTH_SHORT).show();
		return true;
	}
	
	@Override
	public void onDestroy() {		
		super.onDestroy();
		this.quit=true;
		Toast.makeText(getApplicationContext(),
				"Destoyed", Toast.LENGTH_SHORT).show();
	}

}
