package com.example.ch31;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
  TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      text=(TextView)findViewById(R.id.text);
    }
   /* @Override
    	public boolean onKeyDown(int keyCode, KeyEvent event) {
    		// TODO Auto-generated method stub
    		 super.onKeyDown(keyCode, event);
    		 text.setText("X:"+event.getX()+" Y:"+event.getY());
    			return text.showContextMenu();
    	}*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	 super.onTouchEvent(event);	 
		 text.setText("X:"+event.getX()+" Y:"+event.getY());
			return text.showContextMenu();
	}
}
