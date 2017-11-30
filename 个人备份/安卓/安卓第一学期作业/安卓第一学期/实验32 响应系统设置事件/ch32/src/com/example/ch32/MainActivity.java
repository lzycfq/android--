package com.example.ch32;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Config;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
  EditText ori,navigation,touch,mnc;
  Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ori=(EditText)findViewById(R.id.ori);
        navigation=(EditText)findViewById(R.id.navigation);
        	touch=(EditText)findViewById(R.id.touch);
        	mnc=(EditText)findViewById(R.id.mnc);
        navigation.setOnClickListener(this);	
        ori.setOnClickListener(this);
        touch.setOnClickListener(this);
        mnc.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Configuration config=getResources().getConfiguration();
		if(config.orientation==Configuration.ORIENTATION_LANDSCAPE){
		}else if(config.orientation==Configuration.ORIENTATION_PORTRAIT){
		}else if(config.orientation==Configuration.ORIENTATION_SQUARE);
		case.ORIENTATION_LANDSCAPE:
	}
	}
