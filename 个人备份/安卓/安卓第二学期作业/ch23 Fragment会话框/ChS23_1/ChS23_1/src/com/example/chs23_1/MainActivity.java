package com.example.chs23_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnInput).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				InputFragment input=new InputFragment();
				input.show(getFragmentManager(), "input");
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {      
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
