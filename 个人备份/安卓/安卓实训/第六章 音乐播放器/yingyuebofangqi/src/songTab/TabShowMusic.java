package songTab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Song.songActivty.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import songActivty.MainActivity;
import songActivty.Mediaplayer;
import songActivty.SongLyric;
import songAdapter.MyAdapter;
import songBean.MusicModel;

public class TabShowMusic extends Activity implements OnClickListener ,OnItemClickListener,OnSeekBarChangeListener{
	ImageView  iv2, iv4, iv5;
	Spinner iv3;
	TextView tv2, quanbubofang, chazhao, duoxuan,time1,time2;
	ListView lv;
	ImageButton ib1;
	SeekBar sBar;
	ImageButton back_main;
	public static ArrayList<MusicModel> listdata;
	public static MyAdapter adapter;
	public static int Music_index = -1;
	public static boolean isplay = false;
	public static boolean stopthread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabmusic);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		back_main=(ImageButton)findViewById(R.id.back_main);
		//iv1 = (ImageView) findViewById(R.id.tabmusic_iv1);
		iv2 = (ImageView) findViewById(R.id.tabmusic_iv2);
		iv3 = (Spinner) findViewById(R.id.tabmusic_iv3);
		tv2 = (TextView) findViewById(R.id.tabmusic_tv2);
		time1 = (TextView) findViewById(R.id.tabmusic_tv4);
		time2 = (TextView) findViewById(R.id.tabmusic_tv5);
		iv4 = (ImageView) findViewById(R.id.tabmusic_iv4);
		iv5 = (ImageView) findViewById(R.id.tabmusic_iv5);
		ib1 = (ImageButton) findViewById(R.id.tabmusic_ib1);
		back_main.setOnClickListener(this);
		
		ib1.setOnClickListener(this);
		
		sBar = (SeekBar) findViewById(R.id.tabmusic_sb);
		sBar.setOnSeekBarChangeListener(this);
		sBar.setMax(100);
		iv5.setOnClickListener(this);
		iv2.setOnClickListener(this);
		iv4.setOnClickListener(this);
		quanbubofang = (TextView) findViewById(R.id.tabmusic_quanbubofang);
		chazhao = (TextView) findViewById(R.id.tabmusic_chazhao);
		duoxuan = (TextView) findViewById(R.id.tabmusic_duoxuan);
		
		lv = (ListView) findViewById(R.id.tabmusic_lv);
		lv.setOnItemClickListener(this);
		
		Intent intent = getIntent();
		String string = intent.getStringExtra("num");
	    tv2.setText("本地播放器");
		showlist();
		if (isplay) {
			iv5.setImageDrawable(getResources().getDrawable(
					R.drawable.pause_button_default));
		} else {
			iv5.setImageDrawable(getResources().getDrawable(
					R.drawable.play_button_default));
		}
		Thread thread = new Thread(sBarrunner);
		thread.start();
		stopthread = false;
		
       	}

	
	Runnable sBarrunner = new Runnable() {

		@Override
		public void run() {
			while (!stopthread) {
                if (isplay) {
					Message  message=handler.obtainMessage();
					message.arg1=(Mediaplayer.DoGetCurrentTime()/1000);
					message.arg2=(Mediaplayer.DoGetMaxTime()/100);
					message.sendToTarget();
					try {
						Thread.sleep(800);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	};
Handler handler=new Handler(){

	@Override
	public void handleMessage(Message msg) {
	if (isplay) {
		sBar.setProgress(getprogress(msg.arg1, msg.arg2));
		iv5.setImageDrawable(getResources().getDrawable(
				R.drawable.pause_button));
		DoChangeTxt();
	}else {
		iv5.setImageDrawable(getResources().getDrawable(
				R.drawable.play_button));
	}

	}
	
};
//判断显示歌词进度
private  int  getprogress(int ctime,int mtime){
	float a=(ctime);
	float b=(mtime);
	int c=(int) (a/b*1000);
	return c;
}

private void DoChangeTxt(){
	SimpleDateFormat sim =new SimpleDateFormat("mm:ss");
	String ctime=sim.format(Mediaplayer.DoGetCurrentTime());
	String mtime=sim.format(Mediaplayer.DoGetMaxTime());
	String s2=ctime+"/"+mtime;
	String s1=listdata.get(Music_index).getMusic_Name();
	//错误
	time1.setText(s1);
	time2.setText(s2);
	
}

private void showlist() {
		listdata = new ArrayList<MusicModel>();
		ContentResolver res = getContentResolver();
		Cursor cursor = res.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				null, null, null, null);
		while (cursor.moveToNext()) {
			MusicModel model = new MusicModel();
			model.setMusic_Id(cursor.getString(cursor
					.getColumnIndex(BaseColumns._ID)));
			model.setMusic_Name(cursor.getString(cursor
					.getColumnIndex(MediaColumns.TITLE)));
			model.setMusic_ARTIST(cursor.getString(cursor
					.getColumnIndex(AudioColumns.ARTIST)));
			model.setMusic_Path(cursor.getString(cursor
					.getColumnIndex(MediaColumns.DATA)));
			model.setMusic_ALBUM_ID(cursor.getString(cursor
					.getColumnIndex(AudioColumns.ALBUM_ID)));
			listdata.add(model);
			System.out.println(cursor.getString(cursor
					.getColumnIndex(MediaColumns.TITLE)));
		}
		adapter = new MyAdapter(this, listdata, 1);
		lv.setAdapter(adapter);

		cursor.close();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tabmusic_ib1:
			Intent intent = new Intent(this, SongLyric.class);
			intent.putExtra("name", time1.getText().toString());
			startActivity(intent);
			break;
		case R.id.back_main:
          Intent in=new Intent(this,MainActivity.class);
			startActivity(in);
			break;
		case R.id.tabmusic_iv5:
			if (Music_index == -1 && !isplay) {
				Mediaplayer.DoPlayer(listdata.get(0).getMusic_Path());
				iv5.setImageDrawable(getResources().getDrawable(
						R.drawable.pause_button));
				isplay = true;
				Music_index = 0;
				
			} else if (isplay) {
				Mediaplayer.DoPause();
				iv5.setImageDrawable(getResources().getDrawable(
						R.drawable.play_button));
				isplay = false;
			} else {
				Mediaplayer.DoContinuePlay();
				iv5.setImageDrawable(getResources().getDrawable(
						R.drawable.pause_button));
				isplay = true;
			}
			break;
		case R.id.tabmusic_iv4:
			if (!isplay) {
				return;
			}
			if (Music_index == listdata.size() - 1) {
				Music_index = 0;
			} else {
				Music_index++;
			}
			Mediaplayer.DoPlayer(listdata.get(Music_index).getMusic_Path());
			isplay = true;
			adapter.notifyDataSetChanged();
		
			break;
			
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int i, long arg3) {
         if (Music_index!=i) {
			Mediaplayer.DoPlayer(listdata.get(i).getMusic_Path());
			isplay=true;
			iv5.setImageDrawable(getResources().getDrawable(
					R.drawable.pause_button_default));
			adapter.notifyDataSetChanged();
			if (listdata.get(i).getALBUM_Image()!=null) {
				ib1.setImageBitmap(listdata.get(i).getALBUM_Image());
//			}else {
//				ib1.setImageResource(R.drawable.widget1_default_album);
//			}
			}
			
		}		else if (Music_index==i) {
			if (isplay) {
				Mediaplayer.DoPause();
				isplay=false;
				iv5.setImageDrawable(getResources().getDrawable(
						R.drawable.play_button_default));
			}else {
				Mediaplayer.DoContinuePlay();
				isplay=true;
				iv5.setImageDrawable(getResources().getDrawable(
						R.drawable.pause_button_default));
			}
		}
         Music_index=i;
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Mediaplayer.DoSetSeekMusic(seekBar.getProgress()*Mediaplayer.DoGetMaxTime()/100);
		
	}
}
	
	

