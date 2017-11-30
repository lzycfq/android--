package com.example.example20160525;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost=getTabHost();
        Intent Loginintent=new Intent(this, LoginActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("用戶登陸").setContent(Loginintent));
        Intent listIntent=new Intent(this, ListActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("人物列表").setContent(listIntent));
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
