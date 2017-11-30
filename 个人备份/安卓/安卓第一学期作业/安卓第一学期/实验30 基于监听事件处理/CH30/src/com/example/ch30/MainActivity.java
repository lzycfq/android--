package com.example.ch30;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
EditText edt;
Button btn1,btn2,btn3,btn4;
class btn1Listener implements OnClickListener{

	@Override
	public void onClick(View v) {
		edt.setText("内部类");
		
	}}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edt=(EditText)findViewById(R.id.edt);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        
        btn1Listener b1=new btn1Listener();
        btn1.setOnClickListener(b1);
        
        btn2.setOnClickListener(new btn2Listener(this, edt));
        
        btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edt.setText("匿名内部类");
			}
		});
        
        btn4.setOnClickListener(this);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		edt.setText("Activity");
		
	}
	
	public void btn5Listener(View v){
		edt=(EditText)findViewById(R.id.edt);
		edt.setText("绑定到标签");
	}
    
}
