package cn.itcast.ipdail;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
private EditText et_ipnumber;
private SharedPreferences sp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ipnumber=(EditText) findViewById(R.id.et_ipnumber);
        // ����SharedPreferences����
        sp=getSharedPreferences("config", MODE_PRIVATE);
        // ��sp�����л�ȡ�洢��IP����,����������ʾ��et_ipnumber�ؼ���
        et_ipnumber.setText(sp.getString("ipnumber",""));
    }
 //"����IP���Ű�ť"�ĵ���¼�
 public void click(View view){
     // ��ȡ�û������IP����
    	   String ipnumber =et_ipnumber.getText().toString().trim();
        // ����Editor����,�����û������IP����
        Editor editor=sp.edit();
    	   editor.putString("ipnumber", ipnumber);
    	   editor.commit();
    	   Toast.makeText(this, "���óɹ�",0).show();
    }
}
