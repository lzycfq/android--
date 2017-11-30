package com.example.xuesheng;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Guanli extends Fragment implements OnClickListener{
	Button btn_denglu;
	static TextView text_sno;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.guanli, container, false);
		btn_denglu=(Button) view.findViewById(R.id.btn_denglu);
		text_sno=(TextView) view.findViewById(R.id.text_sno);
		btn_denglu.setOnClickListener(this);
		
		if(CL.sid!=null){
		text_sno.setText(CL.sname);}
		return view;
	}
	public static void a(String s){
//		Toast.makeText(getActivity(), "登录成功", 0).show();
		text_sno.setText(s);
	}
	public void onClick(View v) {
		if(v.getId()==R.id.btn_denglu){
			DengluFragment login=new DengluFragment();
			login.show(getFragmentManager(), "login");
		}
	}



}
