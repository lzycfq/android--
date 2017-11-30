package com.example.ch4a_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
   ListView showView;
   String[] statusName;
   ArrayList<String> statusVlues=new ArrayList<String>();
   String[] phoneType={"NONE","GSM","CDMA","SIP"};
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showView=(ListView)findViewById(R.id.showView);
        TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        statusName=getResources().getStringArray(R.array.statusname);
        statusVlues.add(tm.getNetworkOperatorName());
        statusVlues.add(phoneType[tm.getPhoneType()]);
        statusVlues.add(tm.getSimCountryIso());
        statusVlues.add(tm.getSimSerialNumber());
        
        ArrayList<Map<String,String>> status=new ArrayList<Map<String,String>>();
        for(int i=0;i<statusVlues.size();i++){
        	HashMap<String,String>map=new HashMap<String, String>();
        	map.put("name",statusName[i]);
        	map.put("value",statusVlues.get(i));
        	status.add(map);
        }
        SimpleAdapter ad=new SimpleAdapter(this,status,R.layout.line, new String[]{"name","value"},new int[]{R.id.textStatusName,R.id.textStatusVaLue});
        showView.setAdapter(ad);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
