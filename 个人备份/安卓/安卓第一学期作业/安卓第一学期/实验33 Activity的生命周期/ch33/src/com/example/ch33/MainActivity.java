package com.example.ch33;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
  TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.text);
        Log.v("tag","欢迎来到");
       
    }
    
    @Override
    	protected void onStart() {
    		// TODO Auto-generated method stub
    		super.onStart();
    		Log.v("欢迎来到", null);
    	}
    @Override
    	protected void onRestart() {
    		// TODO Auto-generated method stub
    		super.onRestart();
    	
    		Log.v("tag", "Welcome bacK");
    		
    	}
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
