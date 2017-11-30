package cn.itcast.fragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends Activity {

	private FragmentTransaction beginTransaction;
	//��������
	private String[][] settingText = { { "����", "ϵͳ��ֽ" }, { "���˻�", "�ٶ����˻�" },
			{ "֪ͨ", "֪ͨ������" }, { "�ƶ�����", "��ЯʽWIFI�ȵ�" }, { "WLAN", "����" },
			{ "����", "�ɱ�����" },{ "����", "�¶�" }, { "ͨ������", "ý������" }, 
			{ "��������", "��λ����" },{ "����", "���뷨����" },  { "���ÿ������", "��������" },
			{ "�豸����", "�洢" } };
	//����ͼ��
	private int[] settingicons = { R.drawable.theme, R.drawable.clound,
			R.drawable.notifycation, R.drawable.internet, R.drawable.wifi,
			R.drawable.bluetooth, R.drawable.wether, R.drawable.volume,
			R.drawable.gps, R.drawable.language, R.drawable.gesture,
			R.drawable.info };
	//��ȡͼ������ķ���
	public int[] getIcons() {
		return settingicons;
	}
	//��ȡ�������ֵķ���
	public String[][] getSettingText() {
		return settingText;
	}
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//����Fragment
		SettingListFragment fragment = new SettingListFragment();
		//����Fragment
		SettingiconFragment icFragment = new SettingiconFragment();
		//��ȡ����
		beginTransaction = getFragmentManager().beginTransaction();
		//���Fragment
		beginTransaction.replace(R.id.settingcontent, fragment);
		beginTransaction.replace(R.id.settinglist, icFragment);
		//�ύ����
		beginTransaction.commit();
	}
}
