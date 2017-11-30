package com.example.ch4zy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText editNumber;
TelephonyManager tManager;
Button Button;
ArrayList<String> blackList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNumber=(EditText)findViewById(R.id.editNumber);
        Button=(Button)findViewById(R.id.Button);
        bindPhoneManager();
        tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String number=editNumber.getText().toString();
				    	if(number.isEmpty()){
				    		Toast.makeText(getApplicationContext(), "û�е绰����", Toast.LENGTH_LONG).show();
				    		return;
				    	}
				    	OutputStream os=null;
						try{
							//��׷�ӷ�ʽд���ļ�blacklist
							os=openFileOutput("phoneList", Context.MODE_APPEND);
						}
						catch(Exception e){e.printStackTrace();}
						PrintStream ps=new PrintStream(os);
						ps.println(number);      
						Toast.makeText(getApplicationContext(), "��ӳɹ�", Toast.LENGTH_LONG).show();
						//��ȡ������
						readBlackList();	

			}
		});
    }
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
        		Toast.makeText(getApplicationContext(), "������Ӻ�����", Toast.LENGTH_SHORT).show();
        	}
        	catch(IOException ioe){
        		Toast.makeText(getApplicationContext(), "��ȡ����", Toast.LENGTH_SHORT).show();
        	}
        }
        	 private void bindPhoneManager(){
          	   //��ʼ���������
              tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
              //����һ���绰״̬������
            		  PhoneStateListener listener=new PhoneStateListener(){
              	//����״̬�ı��¼�
              	public void onCallStateChanged(int state, String incomingNumber) {
              		//�ж��Ǻ���״̬�����Һ������б�������
              		if(state==TelephonyManager.CALL_STATE_RINGING&&blackList.size()>0){
              				//�����������б�
      for (String bNumber:blackList) {
              					//�жϺ��������������к���һ��
      						if(incomingNumber.equals(bNumber))
      							//��ʾ��ʾ��Ϣ
      							Toast.makeText(getApplicationContext(), "����һ��ɧ�ŵ绰", Toast.LENGTH_LONG).show();
      					}					
              		}
              		super.onCallStateChanged(state, incomingNumber);
              		
              	}        	
              	};
              	tManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

              //��ӵ绰����״̬������

            
              	
      }
            		  
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
