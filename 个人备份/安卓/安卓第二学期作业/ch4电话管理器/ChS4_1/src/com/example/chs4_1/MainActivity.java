package com.example.chs4_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class MainActivity extends Activity {
	
	ListView showView;
	//���ڱ�ʾ״̬���Ƶ�����
	String[]	statusNames;
	//��¼�ֻ�״̬�ļ���
	ArrayList<String> statusValues=new ArrayList<String>();
	//�������Ͷ�Ӧֵ
	String[] phoneType={"NONE","GSM","CDMA","SIP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        showView=(ListView)findViewById(R.id.showView);
        //�ֻ�״̬�������
        TelephonyManager tManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //�������ȡ״̬����
        statusNames=getResources().getStringArray(R.array.statusNames);
        //������Ӫ����
        statusValues.add(tManager.getNetworkOperatorName());
        //�������ͣ�getPhoneType()���ص���int����Ҫ����phoneType����ת��
        statusValues.add(phoneType[tManager.getPhoneType()]);
        //�������ڹ���
        statusValues.add(tManager.getSimCountryIso());
        //sim������
        statusValues.add(tManager.getSimSerialNumber());
        
        ArrayList <Map<String,String>> status=new ArrayList<Map<String,String>>();        
        for (int i = 0; i < statusValues.size(); i++) {
			HashMap<String,String> map=new HashMap<String, String>();
			map.put("name",statusNames[i]);
			map.put("value", statusValues.get(i));
			status.add(map);			
		}
        
        SimpleAdapter adapter=new SimpleAdapter(this, status, R.layout.line,
        		new String[]{"name","value"}, new int[]{R.id.textStatusName,R.id.textStatusValue});
        
        showView.setAdapter(adapter);
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
