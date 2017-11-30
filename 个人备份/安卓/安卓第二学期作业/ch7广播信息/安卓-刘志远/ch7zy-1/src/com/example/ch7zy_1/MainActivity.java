package com.example.ch7zy_1;

import com.example.chs7_1.MyReceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    Button send;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener(){
	
			public void onClick(View v){
				// ����Intent����
				Intent in=new Intent();
				// ָ��Intent��ִ������
				//������б������
				in.setAction("com.example.ch7zy-1.OR");
				//���ô��ݵ�����
				in.putExtra("msg","�������Ϣhello");
				// ��������㲥
				sendOrderedBroadcast(in, null);
			

			
			
				
			}	
		});
	}

    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
