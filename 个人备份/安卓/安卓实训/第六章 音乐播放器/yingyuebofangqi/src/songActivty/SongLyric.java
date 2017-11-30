package songActivty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Song.songActivty.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import songAdapter.PlayAdapter;
import songBean.PlayEnity;
import songTab.TabShowMusic;

public class SongLyric extends Activity implements OnClickListener,
		OnSeekBarChangeListener {
	ImageView ib1, ib2, ib3, ib4;
	ImageButton ib;
	Button bt1;
	TextView tv1, time1, time2;
	CheckBox cBox;
	SeekBar sBar;
	int StyleIndex = 1;
	Random random;
	boolean  stopthread;
	private static final Pattern pattern = Pattern
			.compile("(?<=\\[).*?(?=\\])");
	private ListView listView;
	private PlayAdapter playAdapter;
	private View background;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.main_play);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		super.onCreate(savedInstanceState);
		/*background=getLayoutInflater().IN;*/
		
		
		if (TabShowMusic.isplay&&TabShowMusic.listdata.get(TabShowMusic.Music_index).getALBUM_Image()!=null) {
			background.setBackgroundDrawable(new BitmapDrawable(TabShowMusic.listdata.get(TabShowMusic.Music_index).getALBUM_Image()));
		}else {
			/*background.setBackgroundResource(R.drawable.cf);*/
		}
		ib = (ImageButton) findViewById(R.id.MainPlay_ib);
		ib1 = (ImageView) findViewById(R.id.MainPlay_ib1);
		ib2 = (ImageView) findViewById(R.id.MainPlay_ib2);
		ib3 = (ImageView) findViewById(R.id.MainPlay_ib3);
		ib4 = (ImageView) findViewById(R.id.MainPlay_ib4);
		ib.setOnClickListener(this);
		ib1.setOnClickListener(this);
		ib2.setOnClickListener(this);
		ib3.setOnClickListener(this);
		ib4.setOnClickListener(this);
		listView = (ListView) findViewById(R.id.MainPlay_lv);
		listView.setCacheColorHint(0);
		listView.setDividerHeight(0);
		listView.setEnabled(false);

		tv1 = (TextView) findViewById(R.id.MainPlay_tv1);
		time1 = (TextView) findViewById(R.id.MainPlay_time1);
		time2 = (TextView) findViewById(R.id.MainPlay_time2);
		cBox = (CheckBox) findViewById(R.id.MainPlay_cb);
		bt1 = (Button) findViewById(R.id.MainPlay_bt1);
		bt1.setOnClickListener(this);
		sBar = (SeekBar) findViewById(R.id.MainPlay_sb);
		sBar.setOnSeekBarChangeListener(this);
		sBar.setMax(80);
		if (TabShowMusic.isplay) {
			ib2.setImageDrawable(getResources().getDrawable(
					R.drawable.pause_button_default));
		} else {
			ib2.setImageDrawable(getResources().getDrawable(
					R.drawable.play_button_default));
		}
		if (TabShowMusic.isplay) {
			tv1.setText(TabShowMusic.listdata.get(TabShowMusic.Music_index)
					.getMusic_Name().toString());
		} else {
			Intent intent = getIntent();
			String string = intent.getStringExtra("name");
			tv1.setText(string);
		}
		random = new Random();
		random.setSeed(System.currentTimeMillis());
		Mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Mediaplayer.DoPlayer(TabShowMusic.listdata.get(
				 	TabShowMusic.Music_index).getMusic_Path());
			}
		});
		Thread thread = new Thread(sBarrunner);
		thread.start();
		stopthread = false;
		if (TabShowMusic.isplay) {
			Doshowric();
		}
	}

	
	protected  void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Mediaplayer.setOnCompletionListener(null);
	stopthread = true;
	}
	List<PlayEnity> list;
	private void Doshowric() {
		File f = new File(TabShowMusic.listdata.get(TabShowMusic.Music_index)
				.getMusic_Path().replace(".mp3", ".LRC"));
		  
		list = new ArrayList<PlayEnity>();
		playAdapter = new PlayAdapter(this, list);
		if (!f.exists()) {
			listView.setAdapter(playAdapter);
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "UTF-8"));
			String src = null;
			List<PlayEnity> list = new ArrayList<PlayEnity>();
			SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
			for (int i = 0; i < 10; i++) {
				list.add(new PlayEnity(-1, ""));
			}
			while ((src = br.readLine()) != null) {
				Matcher matcher = pattern.matcher(src);
				String senString = src.substring(src.lastIndexOf(']') + 1);
				while (matcher.find()) {
					if (src.endsWith("]")) {

					} else {
						list.add(new PlayEnity(sdf.parse(matcher.group() + "0")
								.getTime(), senString));
					}
				}

			}
			for (int i = 0; i < 10; i++) {
				list.add(new PlayEnity(Integer.MAX_VALUE, ""));
			}
			Collections.sort(list, new Comparator<PlayEnity>() {

				@Override
				public int compare(PlayEnity lhs, PlayEnity rhs) {
					// TODO Auto-generated method stub
					return (int) (lhs.getTime() - rhs.getTime());
				}

			});
			playAdapter = new PlayAdapter(this, list);
			listView.setAdapter(playAdapter);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	Runnable sBarrunner = new Runnable() {

		@Override
		public void run() {
			while (!stopthread) {
				if (TabShowMusic.isplay) {
					Message message = handler.obtainMessage();
					message.arg1 = (Mediaplayer.DoGetCurrentTime());
					message.arg2 = (Mediaplayer.DoGetMaxTime());
					message.what=1;
					message.sendToTarget();
					try {
						Thread.sleep(400);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			Message message = handler.obtainMessage();
			message.what=3;
		}
	};
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Log.e("debuginfo", msg.what+"qqqq");
				sBar.setProgress(getprogress(msg.arg1, msg.arg2));
				if (playAdapter==null) {
				return;	
				}
				Log.e("debuginfo", playAdapter.getCount()+"www");
				for (int i = 0; i < playAdapter.getCount(); i++) {
					
					if (playAdapter.getItem(i).getTime() > msg.arg1) {
						long lasttime = 0;
						long currentTime = 0;
						if (i > 0) {
							lasttime = playAdapter.getItem(i - 1).getTime();
						}
						currentTime = Math.min(msg.arg2, playAdapter.getItem(i)
								.getTime());
						int scolly = (int) ((msg.arg1 - lasttime)
								* playAdapter.TextHeight / (currentTime - lasttime));
						listView.setSelectionFromTop(i, playAdapter.TextHeight
								- scolly + listView.getHeight() / 2);
						handler.obtainMessage(2).sendToTarget();
						
						break;
					}
				}
				if (TabShowMusic.isplay) {
					DoChangeTxt();
				
					tv1.setText(TabShowMusic.listdata.get(TabShowMusic.Music_index).getMusic_Name().toString());
				}
				break;
			case 2:
				playAdapter.notifyDataSetChanged();
				
				break;
			}
		};
	};

	private int getprogress(int ctime, int mtime) {
		float a = (ctime);
		float b = (mtime);
		int c = (int) (a / b * 100);
		return c;
	}

	private void DoChangeTxt() {
		SimpleDateFormat sim = new SimpleDateFormat("mm:ss");
		String ctime = sim.format(Mediaplayer.DoGetCurrentTime());
		String mtime = sim.format(Mediaplayer.DoGetMaxTime());
		time1.setText(ctime);
		time2.setText(mtime);

	}

	private void btStyle() {
		if (StyleIndex > 4) {
			StyleIndex = 1;
		}
		Mediaplayer.style = StyleIndex;
		switch (StyleIndex) {
		case 1:
			ib.setImageResource(R.drawable.widget_playmode_repeate_all_default);
			Mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					TabShowMusic.adapter.notifyDataSetChanged();
					if (TabShowMusic.Music_index == TabShowMusic.listdata
							.size() - 1) {
						TabShowMusic.Music_index = 0;
					} else {
						TabShowMusic.Music_index++;
					}
					Mediaplayer.DoPlayer(TabShowMusic.listdata.get(
							TabShowMusic.Music_index).getMusic_Path());
					Doshowric();
				}
			});
			
			Toast.makeText(this, "选择列表循环", 0).show();
			break;
		case 2:
			ib.setImageResource(R.drawable.widget_playmode_sequence_default);
			Mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					TabShowMusic.adapter.notifyDataSetChanged();
					if (TabShowMusic.Music_index == TabShowMusic.listdata
							.size() - 1) {
						TabShowMusic.Music_index = -1;
						ib2.setImageResource(R.drawable.widget_play_button_default);
						TabShowMusic.isplay = false;
					} else {
						TabShowMusic.Music_index++;
						Mediaplayer.DoPlayer(TabShowMusic.listdata.get(
								TabShowMusic.Music_index).getMusic_Path());
						Doshowric();
					}
				}
			});
		
			Toast.makeText(this, "选择顺序播放", 0).show();
			break;
		case 3:
			ib.setImageResource(R.drawable.widget_playmode_repeate_random_default);
			Mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					TabShowMusic.Music_index = random
							.nextInt(TabShowMusic.listdata.size());
					Mediaplayer.DoPlayer(TabShowMusic.listdata.get(
							TabShowMusic.Music_index).getMusic_Path());
					Doshowric();
				}
			});
			
			Toast.makeText(this, "选择随机播放", 0).show();
			break;
		case 4:
			ib.setImageResource(R.drawable.widget_playmode_repeate_single_default);
			Mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					Mediaplayer.DoPlayer(TabShowMusic.listdata.get(
							TabShowMusic.Music_index).getMusic_Path());

				}
			});
			Toast.makeText(this, "选择单曲循环", 0).show();
			break;
		}
		StyleIndex++;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.MainPlay_bt1:
			finish();
			break;
		case R.id.MainPlay_ib:
			btStyle();
			break;
		case R.id.MainPlay_ib1:
			
			if (!TabShowMusic.isplay) {
				return;
			}
			if (TabShowMusic.Music_index == 0) {
				TabShowMusic.Music_index = TabShowMusic.listdata.size() - 1;
			} else {
				TabShowMusic.Music_index--;
				
			}
			Mediaplayer.DoPlayer(TabShowMusic.listdata
					.get(TabShowMusic.Music_index).getMusic_Path());
			TabShowMusic.isplay = true;
			TabShowMusic.adapter.notifyDataSetChanged();
			tv1.setText(TabShowMusic.listdata.get(TabShowMusic.Music_index)
					.getMusic_Name().toString());
			Doshowric();
			break;

		case R.id.MainPlay_ib2:
		
			if (TabShowMusic.Music_index == -1 && !TabShowMusic.isplay) {
				Mediaplayer.DoPlayer(TabShowMusic.listdata.get(0).getMusic_Path());
				ib2.setImageDrawable(getResources().getDrawable(
						R.drawable.pause_button));
				TabShowMusic.isplay = true;
				TabShowMusic.Music_index = 0;
				Doshowric();
			} else if (TabShowMusic.isplay) {
				Mediaplayer.DoPause();
				ib2.setImageDrawable(getResources().getDrawable(
						R.drawable.play_button_default));
				TabShowMusic.isplay = false;
				
			} else {
				Mediaplayer.DoContinuePlay();
				ib2.setImageDrawable(getResources().getDrawable(
						R.drawable.pause_button_default));
				TabShowMusic.isplay = true;
				Doshowric();
			}
			tv1.setText(TabShowMusic.listdata.get(TabShowMusic.Music_index)
					.getMusic_Name().toString());
		
			break;
		case R.id.MainPlay_ib3:
			
			if (!TabShowMusic.isplay) {
				return;
			}
			if (TabShowMusic.Music_index == TabShowMusic.listdata.size() - 1) {
				TabShowMusic.Music_index = 0;
			
			} else {
				TabShowMusic.Music_index++;
			}
			Mediaplayer.DoPlayer(TabShowMusic.listdata
					.get(TabShowMusic.Music_index).getMusic_Path());
			TabShowMusic.isplay = true;
			TabShowMusic.adapter.notifyDataSetChanged();
			tv1.setText(TabShowMusic.listdata.get(TabShowMusic.Music_index)
					.getMusic_Name().toString());
			Doshowric();
			break;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		Mediaplayer.DoSetSeekMusic(seekBar.getProgress() * Mediaplayer.DoGetMaxTime()
				/ 100);
		
	}
}
