package com.example.ch13zy;

import com.example.ch13zy.*;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity  {
	TextView textMsg;
	Handler handler;
	boolean isRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMsg = (TextView) findViewById(R.id.textMsg);
        handler=new Handler(){
        	public void handleMessage(Message msg){
        	switch (msg.what) {
			case 4:
				textMsg.setText(" ≈»•¡À"+msg.obj+"√Î");
				
			}	
        	super.handleMessage(msg);
        }
    
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 public void run() {
	int timer=0;
	while(isRunning){
		timer++;
		Message m=new Message();
		m.obj=timer;
		m.what=4;
		handler.sendMessage(m);
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		
		}
	}
}
	 
 }

