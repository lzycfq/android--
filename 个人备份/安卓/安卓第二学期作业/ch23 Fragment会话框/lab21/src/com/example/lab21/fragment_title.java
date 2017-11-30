package com.example.lab21;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
interface TitleListener{
	public void setTitle(String type,String name);
}
public class fragment_title extends DialogFragment {
	EditText editName;
	Spinner spnTitle;
	String stype=null;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		LayoutInflater inflater=getActivity().getLayoutInflater();
		View view=inflater.inflate(R.layout.fragment_title, null);
		editName=(EditText)view.findViewById(R.id.editName);
		spnTitle=(Spinner)view.findViewById(R.id.spnTitle);
		
		spnTitle.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				// TODO Auto-generated method stub
				String type[]=getResources().getStringArray(R.array.type);
				stype=type[pos];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.setView(view).setPositiveButton("µÇÈë", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TitleListener listener=(TitleListener)getActivity();
				listener.setTitle(stype, editName.getText().toString());
			}
		}).setNegativeButton("È¡Ïû", null);
		
		return builder.create();
	}

}
