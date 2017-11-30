package com.example.ch16;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements OnClickListener,ViewFactory{
   ImageView a,b,c,d,e,f,g,h;
   ImageView IM=null;
    HS=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=(ImageView)findViewById(R.id.a);
        b=(ImageView)findViewById(R.id.b);
        c=(ImageView)findViewById(R.id.c);
        d=(ImageView)findViewById(R.id.d);
        e=(ImageView)findViewById(R.id.e);
        f=(ImageView)findViewById(R.id.f);
        g=(ImageView)findViewById(R.id.g);
        h=(ImageView)findViewById(R.id.h);
        IM=(ImageView)findViewById(R.id.IM);
        HS=(HorizontalScrollView)findViewById(R.id.HS);
          
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        h.setOnClickListener(this);
         
       IM.setFactory(this);
        IM.setImageResource(R.drawable.z1);
    }
        public void onClick(View v) {
       
    	ImageView image=(ImageView) v;
    	IM.setImageDrawable(image.getDrawable());
        }
        public View makeView(){
        	return new ImageView(this);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    }


	

	