package com.example.lab20;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

public class FragmentHead extends Fragment implements OnClickListener{
	
	ImageButton imgBtn;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.fragment_head, container, false);
	imgBtn=(ImageButton)view.findViewById(R.id.imgBtn);
	imgBtn.setOnClickListener(this);
	return view;
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	Toast.makeText(getActivity(), "Fragment!!!", 0).show();
}

}
