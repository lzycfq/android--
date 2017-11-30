package com.example.ch22muen;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
  TextView TxTse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxTse=(TextView)findViewById(R.id.TXTse);
        
        registerForContextMenu(TxTse);

        
    }
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    		menu.setHeaderTitle("");
    		menu.add(0, 1, 1, "∏¥÷∆");
    		menu.add(0, 2, 2, "ºÙ«–");
    		super.onCreateContextMenu(menu, v, menuInfo);
    		}
@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	switch(item.getItemId()){
	case R.id.py:
		Toast.makeText(this,item.getTitle(),0);
	case R.id.SE:
		 TxTse.setTextColor(Color.RED);
	case R.id.SQ:
		 TxTse.setTextColor(Color.GREEN);
	case R.id.SW:
		 TxTse.setTextColor(Color.BLUE);
	
	}
		return super.onOptionsItemSelected(item);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    
}
