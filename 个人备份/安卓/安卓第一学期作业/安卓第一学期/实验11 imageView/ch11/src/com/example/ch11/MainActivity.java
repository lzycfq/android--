package com.example.ch11;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    int index,count=0;
    Button se=null;
	private ImageView imageView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.img1);
      
        se=(Button)findViewById(R.id.se);
        final int a[]={R.drawable.img1,R.drawable.img2};
        count=a.length;
        imageView.setImageResource(a[index]);
        se.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index++;
				if(index==count)
					index=0;
				imageView.setImageResource(a[index]);
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
