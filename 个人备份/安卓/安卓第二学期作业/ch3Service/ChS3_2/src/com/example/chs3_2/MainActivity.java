package com.example.chs3_2;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	Button bind;
	Button unbind;
	Button status;
	//������������Service��IBinder����
	BindService.MyBinder binder;
	//����һ��ServiceConnection����
	private ServiceConnection conn=new ServiceConnection() {
		//��Activity��Service���ӳɹ�ʱ�ص��÷���
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Toast.makeText(getApplicationContext(), "--Disconnected--", Toast.LENGTH_SHORT).show();			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//��ȡService��onBind�������ص�MyBinder����
			binder=(BindService.MyBinder) service;
			Toast.makeText(getApplicationContext(), "--Connected--", Toast.LENGTH_SHORT).show();
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=(Button)findViewById(R.id.btnBind);
        unbind=(Button)findViewById(R.id.btnUnBind);
        status=(Button)findViewById(R.id.btnStatus);
        
        final Intent intent=new Intent();
        intent.setAction("com.example.chs3_2.BindService");
        
        bind.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//��ָ��Service
				bindService(intent, conn, Service.BIND_AUTO_CREATE);				
			}
		});
        
        unbind.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {				
					unbindService(conn);				
			}
		});
        
        status.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Service��countֵΪ��"+
			binder.getCount(), Toast.LENGTH_LONG).show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
