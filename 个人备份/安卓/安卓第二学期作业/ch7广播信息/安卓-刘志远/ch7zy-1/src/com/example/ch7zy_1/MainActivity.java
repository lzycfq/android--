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
				// 创建Intent对象
				Intent in=new Intent();
				// 指定Intent的执行内容
				//不能用斜杠引入
				in.setAction("com.example.ch7zy-1.OR");
				//设置传递的数据
				in.putExtra("msg","有序的信息hello");
				// 发送有序广播
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
