package com.example.chs22_1;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager.OnCancelListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment implements OnClickListener {
	Button btnNext;
	EditText editName;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View view=inflater.inflate(R.layout.fragment_first, container,false);
		btnNext=(Button)view.findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
		
		editName=(EditText)view.findViewById(R.id.editName);
		return view;
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction trans= getFragmentManager().beginTransaction();
		//定义Bundle对象
		Bundle bundle=new Bundle();
		//添加字符串参数
		bundle.putString("param",editName.getText().toString());
		SecondFragment sf=new SecondFragment();
		//设置到Fragment的参数
		sf.setArguments(bundle);
		trans.replace(R.id.fragmentShow, sf);
		trans.commit();		
	}

}
