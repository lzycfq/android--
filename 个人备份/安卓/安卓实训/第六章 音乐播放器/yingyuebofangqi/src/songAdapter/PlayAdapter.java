package songAdapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import songBean.PlayEnity;

public class PlayAdapter extends BaseAdapter{
private List<PlayEnity> list=null;
private Context context;
public static final int  TextHeight=30;
public PlayAdapter(Context context,List<PlayEnity> list){
	this.context=context;
	this.list=list;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public PlayEnity getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			convertView=getricview();
		}
		TextView textView=(TextView) convertView;
		textView.setText(list.get(position).getContext());
		System.out.println(list.get(position).getContext()+"");
		if (textView.getTop()<parent.getHeight()/2&&textView.getBottom()>parent.getHeight()/2) {
		Shader shader=new LinearGradient(0, 0, 0,textView.getTextSize(), Color.RED, Color.GRAY,Shader.TileMode.CLAMP);
		textView.getPaint().setShader(shader);
		}else {
			Shader shader=new LinearGradient(0, 0, 0,textView.getTextSize(), Color.YELLOW, Color.GRAY,Shader.TileMode.CLAMP);
			textView.getPaint().setShader(shader);
		}
		return convertView;
	}
public TextView getricview(){
	AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,TextHeight);
	TextView textView=new TextView(context);
	textView.setTextSize(TextHeight-15);
	textView.setLayoutParams(lp);
	textView.setGravity(Gravity.CENTER);
	return textView;
	
}
}
