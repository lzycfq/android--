package com.example.xuesheng;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.widget.Toast;

public class Musicservice extends Service {
	AlarmManager aManager;
	DBHepler dbHepler;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate() {
		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Calendar currentTime = Calendar.getInstance();
		aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		dbHepler = new DBHepler(getApplicationContext());
		currentTime = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		SQLiteDatabase db = dbHepler.getReadableDatabase();
		Cursor cursor = null;
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			cursor = db.query("kechengbiao", null, "week=?",
					new String[] { "周一" }, null, null, null);
		} else if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			cursor = db.query("kechengbiao", null, "week=?",
					new String[] { "周二" }, null, null, null);
		} else if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			cursor = db.query("kechengbiao", null, "week=?",
					new String[] { "周三" }, null, null, null);
		} else if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			cursor = db.query("kechengbiao", null, "week=?",
					new String[] { "周四" }, null, null, null);
		} else if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			cursor = db.query("kechengbiao", null, "week=?",
					new String[] { "周五" }, null, null, null);
		}
		while (cursor.moveToNext()) {
			Map<String, String> mapjilu = new HashMap<String, String>();
			String time = cursor.getString(cursor.getColumnIndex("time"));
			if (time.length() > 0) {
				String[] t = time.split(":");
				if (t[1].length() <= 1) {
					t[1] = "0" + t[1];
				}
				c.setTimeInMillis(System.currentTimeMillis());
				c.set(Calendar.HOUR, Integer.valueOf(t[0]));
				c.set(Calendar.MINUTE, Integer.valueOf(t[1]));
				Intent intent1 = new Intent();
				ComponentName component = new ComponentName("com.example.xuesheng",	"com.example.xuesheng.MusicActivity");
				intent1.setComponent(component);
				PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent1, 0);
				aManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
				Toast.makeText(getApplicationContext(), time + "的闹钟设置成功",Toast.LENGTH_SHORT).show();
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
}