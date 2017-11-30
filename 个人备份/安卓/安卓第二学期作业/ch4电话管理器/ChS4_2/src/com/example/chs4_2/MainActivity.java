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
	//�ֻ�״̬�������
	TelephonyManager tManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //��ʼ���������
        tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //����һ���绰״̬������
        PhoneStateListener listener =new PhoneStateListener(){
        	//����״̬�ı��¼�
        	public void onCallStateChanged(int state, String incomingNumber) {
        		//��Ժ���״̬����
        		if(state==TelephonyManager.CALL_STATE_RINGING){
        			OutputStream os=null;
        			try{
        				//��׷�ӷ�ʽд���ļ�phonelist
        				os=openFileOutput("phoneList", Context.MODE_APPEND);
        			}
        			catch(Exception e){e.printStackTrace();}
        			PrintStream ps=new PrintStream(os);
        			ps.println(String.format("%s  �������:%s",(new Date()).toString(),incomingNumber));        					
        		}
        		super.onCallStateChanged(state, incomingNumber);
        	}        	
        };
        //��ӵ绰����״̬������
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
