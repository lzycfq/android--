package com.example.example7;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;

public class MainActivity extends Activity {
DigitalClock dClock=null;
AnalogClock aClock=null;
Button btn=null;
boolean isDigtal=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dClock=(DigitalClock)findViewById(R.id.dClock);
        aClock=(AnalogClock)findViewById(R.id.aClock);
        btn=(Button)findViewById(R.id.btnchange);
        
        
       btn.setOnClickListener(new OnClickListener(){
    	   public void OnClick(View v){
    		   if(isDigtal){
    			   dClock.setVisibility(View.GONE);
    			   aClock.setVisibility(View.VISIBLE);
    			   isDigtal=false;
    		   }else
    			   dClock.setVisibility(View.GONE);
			   aClock.setVisibility(View.VISIBLE);
			   isDigtal=true;
    	   }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}  
    	   });
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
