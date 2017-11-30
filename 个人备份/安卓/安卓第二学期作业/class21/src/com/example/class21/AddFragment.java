package com.example.class21;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.class21.R;

class AddFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO 自动生成的方法存根
			return inflater.inflate(R.layout.item_layout_addstudent, container,false);
		}
	}