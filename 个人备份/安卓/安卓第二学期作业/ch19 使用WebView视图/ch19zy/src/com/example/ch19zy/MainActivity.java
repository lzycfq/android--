package com.example.ch19zy;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
  Button btngo,btnback;
  EditText editinput;
  WebView wvBrowser;
private boolean ture;
  private class HellowebViewClient extends WebViewClient{
	  public boolean shouldOverrideUrlLoading(WebView view,String url){
		  view.loadUrl(url);
		return ture;
		  
	  }
  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngo=(Button)findViewById(R.id.btngo);
        btnback=(Button)findViewById(R.id.btnback);
        editinput=(EditText)findViewById(R.id.editinput);
        wvBrowser=(WebView)findViewById(R.id.wvBrowser);
        btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}

			
		});
        btngo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				go();
			}
		});
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onKeyDown(int keyCode,KeyEvent event){
    	if(keyCode==KeyEvent.KEYCODE_SEARCH){
    		go();   	
    	}
    	if((keyCode==KeyEvent.KEYCODE_BACK)){
    		wvBrowser.goBack();
    		return ture;
    	}
	    return super.onKeyDown(keyCode, event);
	
    	
    }


	private void go() {
		// TODO Auto-generated method stub
	String url=editinput.getText().toString();
	wvBrowser.loadUrl(url);
	}
}
