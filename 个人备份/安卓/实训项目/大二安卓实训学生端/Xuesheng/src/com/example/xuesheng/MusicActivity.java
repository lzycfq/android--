package com.example.xuesheng;

import com.example.xuesheng.DengluFragment.NetTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;

public class MusicActivity extends Activity {
	 private MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  mp = MediaPlayer.create(this, R.raw.a);
		  mp.start();
		  AlertDialog.Builder builder=new AlertDialog.Builder(MusicActivity.this);
		  builder.setMessage("上课了...");
			builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							mp.stop();
							MusicActivity.this.finish();
						}
					});
	 builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
