package com.example.chs22_2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RightFragment extends Fragment {

	TextView textMsg;

	// 公开一个用于修改内容的方法
	public void SetMessage(String msg) {
		textMsg.setText(msg);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_right, container, false);
		textMsg = (TextView) view.findViewById(R.id.textMsg);
		return view;
	}

}
