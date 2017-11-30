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
	//�������б�
	ArrayList<String> blackList=new ArrayList<String>();

	//�ֻ�״̬�������
	TelephonyManager tManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editNumber=(EditText)findViewById(R.id.editNumber);
        //��ȡ������
        readBlackList();
        //�󶨵绰��ʾ
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
    
    //��ȡ������
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
    
    //�󶨵绰��ʾ
    private void bindPhoneManager(){
    	 //��ʼ���������
        tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //����һ���绰״̬������
        PhoneStateListener listener =new PhoneStateListener(){
        	//����״̬�ı��¼�
        	public void onCallStateChanged(int state, String incomingNumber) {
        		//��Ժ���״̬����
        		if(state==TelephonyManager.CALL_STATE_RINGING&&blackList.size()>0){
        			for (String bNumger : blackList) {
        				//���������������к���һ��
						if(incomingNumber.equals(bNumger))
							//��ʾ��ʾ��Ϣ
							Toast.makeText(getApplicationContext(), "����һ��ɧ�ŵ绰", Toast.LENGTH_LONG).show();
					}					
        		}
        		super.onCallStateChanged(state, incomingNumber);
        	}        	
        };
        //��ӵ绰����״̬������
        tManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }
    
    //��ӵ绰��������
    public void onAddClick(View view){
    	String number=editNumber.getText().toString();
    	if(number.isEmpty()){
    		Toast.makeText(getApplicationContext(), "û�е绰����", Toast.LENGTH_LONG).show();
    		return;
    	}
    	OutputStream os=null;
		try{
			//��׷�ӷ�ʽд���ļ�blacklist
			os=openFileOutput("blackList", Context.MODE_APPEND);
		}
		catch(Exception e){e.printStackTrace();}
		PrintStream ps=new PrintStream(os);
		ps.println(number);      
		Toast.makeText(getApplicationContext(), "��ӳɹ�", Toast.LENGTH_LONG).show();
		readBlackList();
    }

	@Override
	public void onClick(View v) {
		
		
	}
}
