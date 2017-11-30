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
        // 创建SharedPreferences对象
        sp=getSharedPreferences("config", MODE_PRIVATE);
        // 从sp对象中获取存储的IP号码,并将号码显示到et_ipnumber控件中
        et_ipnumber.setText(sp.getString("ipnumber",""));
    }
 //"设置IP拨号按钮"的点击事件
 public void click(View view){
     // 获取用户输入的IP号码
    	   String ipnumber =et_ipnumber.getText().toString().trim();
        // 创建Editor对象,保存用户输入的IP号码
        Editor editor=sp.edit();
    	   editor.putString("ipnumber", ipnumber);
    	   editor.commit();
    	   Toast.makeText(this, "设置成功",0).show();
    }
}
