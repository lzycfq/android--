package com.example.chs22_2;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//实现侦听器
public class MainActivity extends Activity implements OnSelectCategoryListener {
	
	FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //布局Fragment
        fManager=getFragmentManager();
        FragmentTransaction trans= fManager.beginTransaction();
        trans.replace(R.id.fragContainer, new RightFragment(),"container");
        trans.replace(R.id.fragCategory, new LeftFragment(),"catregory");
        trans.commit();
    }
    
    @Override
	public void onCategorySelect(String title) {
    	//调用RightFragment的方法修改内容
		RightFragment container=(RightFragment)fManager.findFragmentByTag("container");
		container.SetMessage(title);
		
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {       
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	
}
