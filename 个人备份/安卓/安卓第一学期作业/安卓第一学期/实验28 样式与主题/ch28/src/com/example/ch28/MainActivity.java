package com.example.ch28;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
Button btnCancel=null;
Button btnOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOK=(Button)findViewById(R.id.btnOK);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        registerForContextMenu(btnOK);
			}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	SubMenu subMenu2 = menu.addSubMenu(0,2,Menu.NONE, "ÑÕÉ«");
       // getMenuInflater().inflate(R.menu.main, menu);
    	subMenu2.add(2,23, 3, "À¶É«");
        return true;
    }
    

@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
	switch(item.getItemId()){
	case 23:
	btnCancel.setTextColor(Color.parseColor("#436EEE")); 
	break;
	}
	return true;
	
}
}

