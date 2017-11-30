package com.example.ch20zy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeftFragment extends Fragment {
	public View OnCreatView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {	
 		return inflater.inflate(R.layout.fragmentleft,container,false);
}
}
