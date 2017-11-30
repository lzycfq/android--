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
	
	//�ص��ӿ�
	interface LoginListener{
		void onLoginInput(String name,String password);		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		//���岼�ֶ���
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.fragment_login, null);
		editName = (EditText) view.findViewById(R.id.editName);
       
		builder.setView(view)
				// ��ӶԻ���ť
				.setPositiveButton("��¼",
						new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int id){
								//��ô���ص��Ķ���
								LoginListener listener = (LoginListener) getActivity();
								listener.onLoginInput(editName
										.getText().toString(), editPassword
										.getText().toString());
							}
						}).setNegativeButton("ȡ��", null);
		return builder.create();
	}

}
