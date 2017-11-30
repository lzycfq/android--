package com.example.ch4a_1;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText editNumber, editContent;
	SmsManager smsManager;
	Button button;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		smsManager=SmsManager.getDefault();
        		editNumber = (EditText) findViewById(R.id.editNumber);
        		editContent = (EditText) findViewById(R.id.editContent);
        	    button=(Button)findViewById(R.id.button);
        	    button.setOnClickListener(new OnClickListener() {
					
					private PendingIntent pi;

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String numberStr = editNumber.getText().toString();
						//将字符串以逗号进行分割
						String[] numbers =
						//遍历numbers数组
				
							// 创建PendingIntent对象
							for(){
								PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
							}
							//发送短信
							smsManager.sendTextMessage(editNumber.getText().toString(), 
									null, editContent.getText().toString(), pi, null);
							
							Toast.makeText(getApplicationContext(), "短信发送成功",
									Toast.LENGTH_SHORT).show();

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
