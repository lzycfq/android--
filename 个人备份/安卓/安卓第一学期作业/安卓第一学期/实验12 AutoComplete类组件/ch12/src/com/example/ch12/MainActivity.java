package com.example.ch12;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends Activity {
TextView se=null;
AutoCompleteTextView aname=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aname=(AutoCompleteTextView)findViewById(R.id.aname);
        se=(TextView)findViewById(R.id.se);
        String[] city=getResources().getStringArray(R.array.city);
        
        ArrayAdapter<String>arrayname=new ArrayAdapter<String>(this,R.layout.list_item, city);
        aname.setAdapter(arrayname);
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
