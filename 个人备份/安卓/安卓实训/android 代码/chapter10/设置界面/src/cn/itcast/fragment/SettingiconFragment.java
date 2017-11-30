package cn.itcast.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class SettingiconFragment extends Fragment {

	private View view;
	private int[] settingicon;
	private String[][] settingText;
	private ListView mListView;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//��������
		view = inflater
				.inflate(R.layout.fragment_settingicon, container, false);
		//��ȡAcitivtyʵ������
		MainActivity activity = (MainActivity) getActivity();
		//��ȡActivity�е�ͼ������
		settingicon = activity.getIcons();
		//��ȡActivity�е�������������
		settingText = activity.getSettingText();
		if (view != null) { // ���view��Ϊ��
			initView();
		}
		//ΪListView������Ŀ����
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//ͨ��Activityʵ����ȡ��һ��Fragmentʵ��
				SettingListFragment listFragment = (SettingListFragment) ((MainActivity) getActivity())
						.getFragmentManager().findFragmentById(
								R.id.settingcontent);
				//��������Fragment������
				listFragment.setText(settingText[position]);
				
			}
		});
		return view;
	}

	//��ʼ���ؼ��ķ���
	private void initView() {
		mListView = (ListView) view.findViewById(R.id.settingicon);
		if (settingicon != null) {
			mListView.setAdapter(new MyAdapter());
		}

	}

	//������
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return settingicon.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return settingicon[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(getActivity(), R.layout.item_list, null);
			ImageView mNameTV = (ImageView) convertView
					.findViewById(R.id.settingicon_imgv);
			mNameTV.setBackgroundResource(settingicon[position]);
			return convertView;
		}

	}
}
