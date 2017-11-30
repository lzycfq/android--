package com.example.chs3_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BindService extends Service {
	//记录累加数值
	private int count;
	//标记是否退出
	private boolean quit;
	//保持onBinder方法所返回的对象
	private MyBinder binder=new MyBinder();
	//继承自Binder类以实现IBinder接口		
	public class MyBinder extends Binder{
		public int getCount(){
			//返回当前累加的值
			return count;
		}
	}
	@Override
	//必须实现的方法
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "Binded", Toast.LENGTH_SHORT).show();
		// 返回绑定IBinder对象
		return binder;
	}
	
	@Override
	//Service被创建时的方法
	public void onCreate() {		
		super.onCreate();
		Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
		//利用线程每1秒累加1
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
