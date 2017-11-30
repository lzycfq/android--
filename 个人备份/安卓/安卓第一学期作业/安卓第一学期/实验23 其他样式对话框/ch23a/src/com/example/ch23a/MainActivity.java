package com.example.ch23a;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
  Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.btn);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("��ȷ��Ҫ�뿪");
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "ȷ��",0).show();
			
					}
				
					
                });
                builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //������ӵ��ȡ������߼�
                    	Toast.makeText(getApplicationContext(), "ȡ��",0).show();
                    }
                });
               

    builder.create().show();
						
				
				
			
			}
        });
    

    }
      public void onBackPressed(){
    	  AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
          builder.setIcon(R.drawable.ic_launcher);
          builder.setTitle("�˳�");
          builder.setMessage("��ȷ��Ҫ�˳�");
          builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
			      finish();
		
				}
			
				
          });
          builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                  //������ӵ��ȡ������߼�
              
              }
          });
         

          builder.create().show();
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
         
}
