package com.example.chs22_1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
	TextView textShow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_second, container, false);
		textShow = (TextView) view.findViewById(R.id.textShow);
		//通过getArguments获取参数
		if (getArguments() != null) {
			String name = getArguments().getString("param");
			textShow.setText(name);
		}

		return view;
	}

}
