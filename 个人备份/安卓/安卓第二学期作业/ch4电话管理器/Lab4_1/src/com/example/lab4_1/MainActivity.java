package com.example.lab4_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	
	EditText editNumber;	
	//黑名单列表
	ArrayList<String> blackList=new ArrayList<String>();

	//手机状态服务对象
	TelephonyManager tManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editNumber=(EditText)findViewById(R.id.editNumber);
        //读取黑名单
        readBlackList();
        //绑定电话提示
        bindPhoneManager();        
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
    
    //读取黑名单
    private void readBlackList(){
    	InputStream is=null;    	
    	try{
    		is=openFileInput("blackList");
    		InputStreamReader isReader=new InputStreamReader(is);
    		BufferedReader bReader=new BufferedReader(isReader);
    		String number;
    		blackList.clear();
    		while((number=bReader.readLine())!=null){
    			blackList.add(number);
    		}    		
    	}
    	catch(FileNotFoundException fnfe){
    		Toast.makeText(getApplicationContext(), "请先添加黑名单", Toast.LENGTH_SHORT).show();
    	}
    	catch(IOException ioe){
    		Toast.makeText(getApplicationContext(), "读取错误", Toast.LENGTH_SHORT).show();
    	}
    }
    
    //绑定电话提示
    private void bindPhoneManager(){
    	 //初始化服务对象
        tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //定义一个电话状态侦听器
        PhoneStateListener listener =new PhoneStateListener(){
        	//呼叫状态改变事件
        	public void onCallStateChanged(int state, String incomingNumber) {
        		//针对呼入状态处理
        		if(state==TelephonyManager.CALL_STATE_RINGING&&blackList.size()>0){
        			for (String bNumger : blackList) {
        				//呼入号码与黑名单中号码一致
						if(incomingNumber.equals(bNumger))
							//显示提示信息
							Toast.makeText(getApplicationContext(), "这是一个骚扰电话", Toast.LENGTH_LONG).show();
					}					
        		}
        		super.onCallStateChanged(state, incomingNumber);
        	}        	
        };
        //添加电话呼叫状态侦听绑定
        tManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }
    
    //添加电话到黑名单
    public void onAddClick(View view){
    	String number=editNumber.getText().toString();
    	if(number.isEmpty()){
    		Toast.makeText(getApplicationContext(), "没有电话号码", Toast.LENGTH_LONG).show();
    		return;
    	}
    	OutputStream os=null;
		try{
			//以追加方式写入文件blacklist
			os=openFileOutput("blackList", Context.MODE_APPEND);
		}
		catch(Exception e){e.printStackTrace();}
		PrintStream ps=new PrintStream(os);
		ps.println(number);      
		Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_LONG).show();
		readBlackList();
    }

	@Override
	public void onClick(View v) {
		
		
	}
}
