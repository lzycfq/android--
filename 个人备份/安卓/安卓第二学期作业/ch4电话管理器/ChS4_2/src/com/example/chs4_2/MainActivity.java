package com.example.chs4_2;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	//手机状态服务对象
	TelephonyManager tManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化服务对象
        tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //定义一个电话状态侦听器
        PhoneStateListener listener =new PhoneStateListener(){
        	//呼叫状态改变事件
        	public void onCallStateChanged(int state, String incomingNumber) {
        		//针对呼入状态处理
        		if(state==TelephonyManager.CALL_STATE_RINGING){
        			OutputStream os=null;
        			try{
        				//以追加方式写入文件phonelist
        				os=openFileOutput("phoneList", Context.MODE_APPEND);
        			}
        			catch(Exception e){e.printStackTrace();}
        			PrintStream ps=new PrintStream(os);
        			ps.println(String.format("%s  来电号码:%s",(new Date()).toString(),incomingNumber));        					
        		}
        		super.onCallStateChanged(state, incomingNumber);
        	}        	
        };
        //添加电话呼叫状态侦听绑定
        tManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
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
