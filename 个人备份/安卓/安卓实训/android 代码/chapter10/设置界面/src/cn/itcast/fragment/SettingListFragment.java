package cn.itcast.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class SettingListFragment extends Fragment{
	
	private View view;
	private TextView mTextView1;
	private TextView mTextView2;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//将布局文件解析出来
		view = inflater.inflate(R.layout.fragment_settinglist, container, false);
		if(view != null){   //如果view不为空
		initView();
		}
		setText(((MainActivity)getActivity()).getSettingText()[0]);
		return view;
	}
	
	public void initView(){
		mTextView1 = (TextView) view.findViewById(R.id.tv);
		mTextView2 = (TextView) view.findViewById(R.id.tv1);
	}
	
	public void setText(String[] text){
		mTextView1.setText(text[0]);
		mTextView2.setText(text[1]);
	}
}
