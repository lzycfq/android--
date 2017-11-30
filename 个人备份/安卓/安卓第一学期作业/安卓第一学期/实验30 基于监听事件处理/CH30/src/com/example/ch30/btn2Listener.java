package com.example.ch30;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class btn2Listener implements OnClickListener {
	Activity act;
	EditText edt;
	public btn2Listener(Activity act,EditText edt){
		this.act=act;
		this.edt=edt;
	}

	@Override
	public void onClick(View v) {
		edt.setText("Õ‚≤ø¿‡");
	}

}
