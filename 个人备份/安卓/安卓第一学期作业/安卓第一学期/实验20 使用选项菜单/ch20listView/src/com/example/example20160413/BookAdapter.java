package com.example.example20160413;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

final class ViewHolder {
	public ImageView imgpic;
	public ImageView imgScore;
	public TextView txtTitle, txtType, txtAuthor;
	public Button btnlike, btnRecommend;
}

public class BookAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Map<String, String>> mDate;

	public BookAdapter(Context context, List<Map<String, String>> data) {
		super();
		this.mInflater = LayoutInflater.from(context);
		this.mDate = data;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return mDate.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO 自动生成的方法存根
		return mDate.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_book, null);
			holder = new ViewHolder();
			holder.imgpic = (ImageView) convertView.findViewById(R.id.imgpic);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.txttitle);
			holder.txtAuthor = (TextView) convertView.findViewById(R.id.txtauthor);
			holder.txtType = (TextView) convertView.findViewById(R.id.txttype);
			holder.imgScore = (ImageView) convertView.findViewById(R.id.txtscore);
			holder.btnlike = (Button) convertView.findViewById(R.id.btnlike);
			
			holder.btnRecommend = (Button) convertView
					.findViewById(R.id.btnRecommend);
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder)convertView.getTag();

		}
		String title=mDate.get(position).get("title").toString();
		String author=mDate.get(position).get("author").toString();
		String type=mDate.get(position).get("type").toString();
		holder.txtTitle.setText(title);
		holder.txtAuthor.setText(author);
		holder.txtType.setText(type);
		holder.imgpic.setImageResource(Integer.parseInt(mDate.get(position).get("pic").toString()));
		holder.imgScore.setImageResource(Integer.parseInt(mDate.get(position).get("score").toString()));	
		holder.btnlike.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				String book=mDate.get(position).get("title").toString();
				Toast.makeText(mInflater.getContext(),"《"+book+"》书真好！" , 0).show();
			}
		});
		holder.btnRecommend.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				String book=mDate.get(position).get("title").toString();
				Toast.makeText(mInflater.getContext(),"我推荐你阅读《"+book+"》！", 0).show();
			}
		});
		return convertView;
	}

}
