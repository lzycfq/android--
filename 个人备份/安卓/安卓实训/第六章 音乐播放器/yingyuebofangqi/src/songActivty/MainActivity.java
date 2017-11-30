package songActivty;

import AppFinal.AppFinal;
import Song.songActivty.R;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
import songTab.TabOne;
import songTab.TabShowMusic;

public class MainActivity extends TabActivity implements OnTabChangeListener {
	TabHost tHost;
	TabWidget tWidget;
	String[] arrStrTabItem = { "我的音乐", "网络音乐", "更多功能" };
   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tHost = getTabHost();
		tWidget = getTabWidget();
		/*getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);*/
		LayoutInflater factory = LayoutInflater.from(MainActivity.this);
		
		View view1 = factory.inflate(R.layout.tabhost, null);
		View view2 = factory.inflate(R.layout.tabhost, null);
		View view3 = factory.inflate(R.layout.tabhost, null);
		tHost.addTab(tHost.newTabSpec(AppFinal.item_one).setIndicator(view1)
				.setContent(new Intent(this, TabOne.class)));
		tHost.addTab(tHost.newTabSpec(AppFinal.item_two).setIndicator(view2)
				.setContent(new Intent(this, SongLyric.class)));
		tHost.addTab(tHost.newTabSpec(AppFinal.item_three).setIndicator(view3)
				.setContent(new Intent(this,TabShowMusic.class)));

		tWidget.setOrientation(LinearLayout.VERTICAL);
		tHost.setCurrentTab(0);
		settabwidget();
		tHost.setOnTabChangedListener(this);
		
	}
   
	private void settabwidget() {
		int tabwidth = 60;
		int tabhight = 55;
		for (int i = 0; i < tWidget.getChildCount(); i++) {
			tWidget.getChildAt(i).getLayoutParams().width = tabwidth;
			tWidget.getChildAt(i).getLayoutParams().height = tabhight;
			tWidget.getChildAt(i).setBackgroundColor(
					Color.argb(250, 35, 36, 35));
			if (i == 0) {
				ImageView iv = (ImageView) tWidget.getChildAt(i).findViewById(
						R.id.tabhost_iv);
				iv.setVisibility(View.VISIBLE);

				TextView tv = (TextView) tWidget.getChildAt(i).findViewById(
						R.id.tabhost_tv);
				tv.setText(arrStrTabItem[i]);
				tv.setTextColor(Color.WHITE);
			} else {
				ImageView iv = (ImageView) tWidget.getChildAt(i).findViewById(
						R.id.tabhost_iv);
				iv.setVisibility(View.INVISIBLE);
			}
			TextView tv = (TextView) tWidget.getChildAt(i).findViewById(
					R.id.tabhost_tv);
			tv.setText(arrStrTabItem[i]);
		}
	}

	@Override
	public void onTabChanged(String tabId) {
		for (int i = 0; i < tWidget.getChildCount(); i++) {
	if (tHost.getCurrentTab()==i) {
		ImageView iv = (ImageView) tWidget.getChildAt(i).findViewById(
				R.id.tabhost_iv);
		iv.setVisibility(View.VISIBLE);
		TextView tv = (TextView) tWidget.getChildAt(i).findViewById(
				R.id.tabhost_tv);
		tv.setTextColor(Color.WHITE);
	}else {
		ImageView iv = (ImageView) tWidget.getChildAt(i).findViewById(
				R.id.tabhost_iv);
		iv.setVisibility(View.INVISIBLE);
		TextView tv = (TextView) tWidget.getChildAt(i).findViewById(
				R.id.tabhost_tv);
		tv.setTextColor(Color.GRAY);
	}
}
	}
}