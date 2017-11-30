package com.example.lab21;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,OnSelectCategoryListener,TitleListener {
	ImageButton ibtnName, ibtnScore, ibtnAdd,ibtnTitle;
	TextView txtTitle;
	FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtTitle=(TextView)findViewById(R.id.txtTitle);
		ibtnTitle=(ImageButton)findViewById(R.id.ibtnTitle);
		ibtnName = (ImageButton) findViewById(R.id.ibtnName);
		ibtnScore = (ImageButton) findViewById(R.id.ibtnScore);
		ibtnAdd = (ImageButton) findViewById(R.id.ibtnAdd);
		ibtnTitle.setOnClickListener(this);
		ibtnName.setOnClickListener(this);
		ibtnScore.setOnClickListener(this);
		ibtnAdd.setOnClickListener(this);

		fm = getFragmentManager();
		addFragment("score");
        addFragment("name");
        
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

	public void addFragment(String tag) {
		FragmentTransaction trans = fm.beginTransaction();
		Fragment fragment = fm.findFragmentByTag(tag);
		boolean canAdd = false;
		if (fragment == null)
			canAdd = true;
		else if (!fragment.isAdded()) {
			canAdd = true;
		}
		if (canAdd && tag.equals("name"))
			trans.add(R.id.center, new fragment_name(), tag);
		else if (canAdd && tag.equals("score")) {
			trans.add(R.id.center, new fragment_score(), tag);
		} else if (canAdd && tag.equals("add")) {
			trans.add(R.id.center, new fragment_add(), tag);
		}

		trans.commit();
	}

	protected void changeFragment(String tag) {
		FragmentTransaction trans = fm.beginTransaction();
		Fragment f = fm.findFragmentByTag(tag);
		if (f != null && f.isAdded()) {
			trans.detach(f);
			trans.attach(f);
			trans.commit();
		} else
			addFragment(tag);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ibtnName:
            changeFragment("name");
            
			break;
		case R.id.ibtnScore:
			changeFragment("score");
			
			break;
		case R.id.ibtnAdd:
			changeFragment("add");
			
			break;
		case R.id.ibtnTitle:
			fragment_title ft=new fragment_title();
			ft.show(fm, "title");
		default:
			break;
		}
	}

	@Override
	public void onCategorySelect(String title) {
		// TODO Auto-generated method stub
		fragment_score score=(fragment_score)fm.findFragmentByTag("score");
		score.SetMessage(title);
		//Toast.makeText(getBaseContext(), title+"2", 0).show();
	}

	@Override
	public void setTitle(String type, String name) {
		// TODO Auto-generated method stub
		txtTitle.setText(String.format("%s:%s", new String[]{type,name}));
	}
}
