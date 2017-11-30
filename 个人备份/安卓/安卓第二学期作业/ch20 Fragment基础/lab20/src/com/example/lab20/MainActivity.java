package com.example.lab20;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	
	FragmentHead fmHead;
	FragmentCenter fmCenter;
	
	protected void createFragment() {
		FragmentManager fm=getFragmentManager();
		FragmentTransaction transaction=fm.beginTransaction();
		fmHead=new FragmentHead();
		fmCenter=new FragmentCenter();
		transaction.add(R.id.fmHead, fmHead);
		transaction.add(R.id.fmCenter, fmCenter);
		transaction.commit();
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
