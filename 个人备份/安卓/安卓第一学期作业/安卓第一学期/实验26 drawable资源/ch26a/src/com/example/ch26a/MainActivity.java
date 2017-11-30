package com.example.ch26a;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final Activity dialogInterface = null;
	EditText Ednam;
    EditText Edname;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Edname=(EditText)findViewById(R.id.edname);
        Ednam=(EditText)findViewById(R.id.ednam);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("用户登录");
             
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "确定",0).show();
			
					}
				
					
         });
      builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "取消",0).show();
			
					}
				
					
         });
               btn=(Button)dialogInterface.findViewById(R.id.btn);
				LinearLayout log=(LinearLayout)getLayoutInflater().inflate(R.layout.a, null);
				builder.setView(log);
					
				 builder.create().show();		
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
