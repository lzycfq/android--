package com.example.rssreal;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShowdescriptionActivity extends Activity {

	@Override
	/*protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showdescription);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.showdescription, menu);
		return true;
	}*/
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
	    setContentView(R.layout.activity_showdescription);
		String content=null;
		Intent startinggglntent=getlntent(); 
	if(startingIntent !=null){
	Bundle bundle=startingIntent.getBundleExtra("android.intent.extra.rssltem");
	if(bundle==null){
	content="不好意思程序出错啦";
	}else{
	content=bundle.getString("title")+"\n\n"
	+bundle.getString("pubdate")+"n\n"
			+bundle.getString("description").replase('\n\','')
			+"n\n访问一下网址\n "bundle.getString("link");
	}
	}
	}
}
