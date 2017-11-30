package songTab;

import java.util.ArrayList;
import java.util.HashMap;

import AppFinal.AppFinal;
import Song.songActivty.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.LayerRasterizer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import songActivty.SongLyric;
import songActivty.SongloveActivity;

public class TabOne extends Activity implements OnItemClickListener{
	Cursor cursor;
	ListView lV;
	int deinx=1;
	ArrayList<HashMap<String, String>> listData;
	String[] Tabonelist={"我的音乐","我喜欢","本地收藏","网络收藏","下载管理","最近播放"};
	String[] Tabonelistnum={"9","0","0","未登录","0","1",""};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost_one);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		lV=(ListView) findViewById(R.id.tabhost_one_lv);
		
		ContentResolver res=getContentResolver();
		cursor=res.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
		if (cursor!=null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				//Log.d("external", cursor.getColumnName(i));
			}
			Tabonelistnum[0]=cursor.getCount()+"";
			DoShowList();
		}
	}
	private void DoShowList(){
		Tabonelistnum[0]=String.valueOf(cursor.getCount());
		listData=new ArrayList<HashMap<String,String>>();
		for (int i = 0; i < Tabonelist.length; i++) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put(AppFinal.list_item_one, Tabonelist[i]);
			map.put(AppFinal.list_item_two, Tabonelistnum[i]);
			listData.add(map);
			
		}
		SimpleAdapter adapter=new SimpleAdapter(this, listData, R.layout.tabhost_one_item,
				new String[]{AppFinal.list_item_one,AppFinal.list_item_two},
				new int[]{R.id.tab_one_item_tv1,R.id.tab_one_item_tv2});
		lV.setAdapter(adapter);
		lV.setOnItemClickListener(this);
	}
	@Override
/*	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {*/
		/*if (arg2==0) {
			Intent intent=new Intent(TabOne.this, TabShowMusic.class);
			intent.putExtra("num", Tabonelistnum[0]);
			startActivity(intent);
			
		}*/
	 public void onItemClick(AdapterView<?> parent, View view, int position,long id) {  
		 Log.i("mm", " onItemClick ");  
         Intent intent=new Intent();  
         switch(position){  
         case 0:  
             intent.setClass(getApplicationContext(), TabShowMusic.class);  
             TabOne.this.startActivity(intent);  
             break;
         case 1:  
             intent.setClass(getApplicationContext(), SongloveActivity.class);  
             TabOne.this.startActivity(intent);  
             break;  
       
	/* public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  
        Intent intent = new Intent(getActivity(), demos[position].demoClass);  
        startActivity(intent);  
    }  

	//把每个activity转成class  
    private static class DemoInfo {  
        private final Class<? extends android.app.Activity> demoClass;  
  
        public DemoInfo(Class<? extends android.app.Activity> demoClass) {  
            this.demoClass = demoClass;  
        }  
    }  
  
    //把每个activity转成xxx.class  
    private static final DemoInfo[] demos = {  
        new DemoInfo(TabShowMusic.class),
        new DemoInfo(SongLyric.class),
        
          
    };  */
         }
	}
}
