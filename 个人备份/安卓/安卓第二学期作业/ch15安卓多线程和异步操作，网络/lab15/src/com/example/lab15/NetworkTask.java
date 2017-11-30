package com.example.lab15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.widget.TextView;

public class NetworkTask extends AsyncTask<String, Void , String>{
	TextView text;
	public NetworkTask(TextView t){
		text=t;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			Socket socket=new Socket("10.0.2.2",8189);
			PrintWriter wr=new PrintWriter(socket.getOutputStream());
			wr.write(params[0]);
			wr.flush();
			socket.shutdownOutput();
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line=br.readLine();
			wr.close();
			br.close();
			socket.close();
			return line;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if(result!=null){
			text.setText(result);
		}else{
			text.setText("√ª”–œÏ”¶");
		}
		super.onPostExecute(result);
	}

}
