package com.example.xuesheng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IndexActivity extends Activity implements OnClickListener {
	Button btn_chakanchuqin;
	Button btn_guanli;
	Button btn_kechengbiao;
	FragmentManager fragmentManager;
	AlarmManager aManager;
	TextView text_name;
	Calendar currentTime = Calendar.getInstance();
	DBHepler dbHepler;
	Intent i;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		
		btn_chakanchuqin = (Button) findViewById(R.id.btn_chakanchuqin);
		btn_guanli = (Button) findViewById(R.id.btn_guanli);
		btn_kechengbiao = (Button) findViewById(R.id.btn_kechengbiao);
		btn_chakanchuqin.setOnClickListener(this);
		btn_kechengbiao.setOnClickListener(this);
		btn_guanli.setOnClickListener(this);
		 dbHepler=new DBHepler(getApplicationContext());
		 
		fragmentManager = getFragmentManager();
		addFragment("kechengbiao");
		i=new Intent();
	        i.setAction("com.example.xuesheng.Musicservice");
		aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
	}

	public void tixing(View v) {
	    	startService(i);
	    	}

	public void guanbi(View v){
		stopService(i);
		Toast.makeText(getApplicationContext(), "关闭成功", 0).show();
	}
	public void saomiao(View v) {
		if (CL.sid == null) {
			Toast.makeText(getApplicationContext(), "请先登录", 0).show();
			return;
		}
		Intent intent = new Intent(getApplicationContext(),
				MipcaActivityCapture.class);
		startActivity(intent);
	}

	protected void addFragment(String tag) {
		FragmentTransaction trans = fragmentManager.beginTransaction();
		Fragment fragment = fragmentManager.findFragmentByTag(tag);
		boolean canAdd = false;
		if (fragment == null)
			canAdd = true;
		else if (!fragment.isAdded()) {
			canAdd = true;
		}
		if (canAdd && tag.equals("chakanchuqin"))
			trans.replace(R.id.fragmentShow, new Chakanchuqin());
		else if (canAdd && tag.equals("kechengbiao")) {
			trans.replace(R.id.fragmentShow, new Kechengbiao(), tag);
		} else if (canAdd && tag.equals("guanli")) {
			trans.replace(R.id.fragmentShow, new Guanli(), tag);
		}
		trans.commit();
	}

	protected void changeFragment(String tag) {
		FragmentTransaction trans = fragmentManager.beginTransaction();
		Fragment f = fragmentManager.findFragmentByTag(tag);
		if (f != null) {
			trans.replace(R.id.fragmentShow, f);
			trans.commit();
		} else
			addFragment(tag);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_chakanchuqin:
			changeFragment("chakanchuqin");
			break;
		case R.id.btn_guanli:
			changeFragment("guanli");
			break;
		case R.id.btn_kechengbiao:
			changeFragment("kechengbiao");
			break;
		}
	}

	public void onLoginInput(String sname) {
		Toast.makeText(getApplicationContext(), sname, 0).show();
	}

}
