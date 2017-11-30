package com.example.ch29;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

	

public class MainActivity extends Activity {
	EditText edit;
	Button button;
	Button butto;
	Button butt;
	Button but;
	Button bu;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //edit=(EditText)findViewById(R.id.edit);
        button=(Button)findViewById(R.id.button);
        butto=(Button)findViewById(R.id.butto);
        butt=(Button)findViewById(R.id.butt);
        but=(Button)findViewById(R.id.but);
        bu=(Button)findViewById(R.id.bu);
        button.setOnClickListener(new MyClickListener());
    }
    class MyClickListener implements OnClickListener{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText TXT=(EditText)findViewById(R.id.edit);
				Toast.makeText();
			}
		}
     void clikHander(View soure) {
		// TODO Auto-generated method stub
    	EditText show=(EditText)findViewById(R.id.edit);
    	show.setText("");
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
