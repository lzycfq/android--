package com.example.class21;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener{
	Button btnleft,btnmiddle,btnRight;
	FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnleft=(Button)findViewById(R.id.btnLeft);
		btnmiddle=(Button)findViewById(R.id.btnmiddle);
		btnRight=(Button)findViewById(R.id.btnright);
		btnleft.setOnClickListener(this);
		btnmiddle.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		fragmentManager=getFragmentManager();
		addFragment("Student");
	}
	
	protected void addFragment(String tag){
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		Fragment fragment=fragmentManager.findFragmentByTag(tag);
		boolean canAdd=false;
		if(fragment==null)
			canAdd=true;
		else if (!fragment.isAdded()) {
			canAdd = true;
		}
		if(canAdd)
		if (tag.equals("Student"))
			transaction.add(R.id.fragmentMain, new StudentFragment(), tag);
		else if ( tag.equals("Exam")) {
			transaction.add(R.id.fragmentMain, new ExamFragment(), tag);
		}else if(tag.equals("Add"))
			transaction.add(R.id.fragmentMain, new AddFragment(), tag);

		transaction.commit();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	protected void changeFragment(String tag) {
		FragmentTransaction trans = fragmentManager.beginTransaction();
		Fragment f = fragmentManager.findFragmentByTag(tag);
		if (f != null && f.isAdded()) {
			// 替换fragment到容器中
			trans.detach(f);
			trans.attach(f);
			trans.commit();
		} else
			addFragment(tag);

	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {

		case R.id.btnLeft:
			changeFragment("Student");
			break;
		case R.id.btnmiddle:
			changeFragment("Exam");
			break;
		case R.id.btnright:
			changeFragment("Add");
			break;

		default:
			break;
		}
		
	}
}
