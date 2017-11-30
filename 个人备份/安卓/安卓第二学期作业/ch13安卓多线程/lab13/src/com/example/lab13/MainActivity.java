package com.example.lab13;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{
	TextView textView;
	ImageView imageView;
	Button button;
	ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.txtshow);
        imageView=(ImageView)findViewById(R.id.imgPicture);
        bar=(ProgressBar)findViewById(R.id.pgbProgress);
        button=(Button)findViewById(R.id.btnAdd);
        imageView.setBackground(getResources().getDrawable(R.drawable.star));
        button.setOnClickListener(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class Task extends AsyncTask<Void,Integer ,Void>{
    	

		@Override
		protected Void doInBackground(Void... params) {
			// TODO 自动生成的方法存根
			int prog=0;
			
			while(prog<100){
				prog=prog+10;
				publishProgress(prog);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			//设置进度条当前值
			bar.setProgress(values[0]);
			//显示进度数字
			textView.setText(Integer.toString(values[0])+"%");
			//如果进度已经达到100
			if (values[0]==100) {
				//设置图片标题
				textView.setText("z4");
				
				//加载图片
				imageView.setBackground(getResources().getDrawable(R.drawable.z4));
			}
			super.onProgressUpdate(values);
		}
	}
	@Override
	public void onClick(View v) {
		imageView.setBackground(getResources().getDrawable(R.drawable.star));
		new Task().execute();
		
	}
    	
    
}
