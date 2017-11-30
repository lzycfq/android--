package com.example.ch17tabhost;

import org.w3c.dom.Text;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class MainActivity extends TabActivity {
	EditText edt=null;
	Button btnOK=null;
	TabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act);
        edt=(EditText)findViewById(R.id.edt);
        btnOK=(CheckBox)findViewById(R.id.btnOk);
        mTabHost=getTabHost();
        
        TabSpec ts1=mTabHost.newTabSpec("tab1");
        ts1.setIndicator("兴趣").setContent(R.id.Tab1);
        mTabHost.addTab(ts1);
        
        TabSpec ts2=mTabHost.newTabSpec("tab2");
        ts2.setIndicator("登陆").setContent(R.id.Tab2);
        mTabHost.addTab(ts2);
        
        TabSpec ts3=mTabHost.newTabSpec("tab3");
        ts3.setIndicator("注册").setContent(R.id.Tab3);
        mTabHost.addTab(ts3);
        
        edt.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			
				
			}
		});
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
