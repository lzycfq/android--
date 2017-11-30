package com.example.ch31;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;

public class NoTouchButton extends Button {

	private boolean ture;
	public NoTouchButton(Context context) {
		super(context);
	
	}
	public NoTouchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	
	}
		public NoTouchButton(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			
		}
 @Override
public boolean onTouchEvent(MotionEvent event) {
			super.onTouchEvent(event);
			return false;
}
}
  