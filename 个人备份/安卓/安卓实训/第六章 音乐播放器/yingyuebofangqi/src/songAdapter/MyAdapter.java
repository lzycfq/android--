package songAdapter;

import java.util.ArrayList;

import Song.songActivty.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import songActivty.PopMenu;
import songBean.MusicModel;
import songTab.TabShowMusic;

public class MyAdapter extends BaseAdapter{
private ArrayList<MusicModel> listdata;
private Activity context;
public static int i;
private int id;
PopMenu popMenu;
public MyAdapter(Activity context,ArrayList<MusicModel> listdata,int i){
	this.context=context;
	this.listdata=listdata;
	this.i=i;
	popMenu=new PopMenu(context);
	popMenu.setonitemclicklistener(popmenuclicklistener);
}
OnClickListener popmenuclicklistener =new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Button bt=(Button) v;
		if (bt.getTag().equals("1")) {
			//收藏
		}else if (bt.getTag().equals("2")) {
			if (i==2) {
				//网络
			}
		}else if (bt.getTag().equals("3")) {
			btTag3();//查看信息
		}else if (bt.getTag().equals("4")) {
			//删除
		}
		Toast.makeText(context, "下拉菜单点击"+bt.getText().toString(), 1).show();
		popMenu.dismiss();
	}
};
private void btTag3(){
	String[] arrStrings={"歌曲名："+listdata.get(id).getMusic_Name(),"文件类型：.mp3"};
	Builder builder=new AlertDialog.Builder(context);
	builder.setItems(arrStrings, null);
	builder.setTitle("歌曲信息");

	builder.show();
	
}
	@Override
	public int getCount() {
		return listdata.size();
	}

	@Override
	public Object getItem(int position) {
		return listdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.tabmusic_item, null);
		}
		TextView Music_Name = (TextView) convertView.findViewById(R.id.tabmusic_item_tv1);
		ImageView iv1=(ImageView) convertView.findViewById(R.id.tabmusic_item_iv1);
		ImageView iv2=(ImageView) convertView.findViewById(R.id.tabmusic_item_iv2);
		ImageView iv3=(ImageView) convertView.findViewById(R.id.tabmusic_item_iv3);
	
		MusicModel data = listdata.get(position);
		Music_Name.setText(data.getMusic_Name());
		System.out.println(position);
		iv3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popMenu.showasdropdown(v);
				id=position;
			}
		});
		if (i==1) {
			String mUriAlbums="content://media/external/audio/albums";
			String[] projection=new String[]{"album_art"};
			Cursor c=context.getContentResolver().query(Uri.parse(mUriAlbums+"/"+listdata.get(position).getMusic_ALBUM_ID()),
					projection, null, null, null);
			String album_art=null;
			if(c.getCount()>0&&c.getColumnCount()>0){
				c.moveToNext();
				album_art=c.getString(0);
			}
			c.close();
			c=null;
			if(album_art==null){
				iv2.setImageDrawable(context.getResources().getDrawable(R.drawable.local_item));
				
			}else{
				Bitmap bitMap=BitmapFactory.decodeFile(album_art);
				listdata.get(position).setALBUM_Image(bitMap);
				iv2.setImageBitmap(bitMap);
			}
			if (TabShowMusic.Music_index==position&&TabShowMusic.isplay) {
				iv1.setVisibility(View.VISIBLE);
			}else {
				iv1.setVisibility(View.INVISIBLE);
			}
			
		}
		return convertView;
	}

}
