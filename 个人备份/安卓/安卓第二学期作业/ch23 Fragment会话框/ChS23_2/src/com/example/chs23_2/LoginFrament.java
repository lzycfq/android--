package com.example.chs23_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LoginFrament extends DialogFragment {
	
	EditText editName;
	EditText editPassword;
	
	//回调接口
	interface LoginListener{
		void onLoginInput(String name,String password);		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		//定义布局对象
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.fragment_login, null);
		editName = (EditText) view.findViewById(R.id.editName);
       
		builder.setView(view)
				// 添加对话框按钮
				.setPositiveButton("登录",
						new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int id){
								//获得处理回调的对象
								LoginListener listener = (LoginListener) getActivity();
								listener.onLoginInput(editName
										.getText().toString(), editPassword
										.getText().toString());
							}
						}).setNegativeButton("取消", null);
		return builder.create();
	}

}
