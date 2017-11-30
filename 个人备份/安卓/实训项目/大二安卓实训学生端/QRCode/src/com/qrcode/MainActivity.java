package com.qrcode;


import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView resultTextView;
	private EditText qrStrEditText;
	private ImageView qrImgImageView;
	Button btn, btn1;
	Spinner spinner;
	private TextView textView,tv_scan_result; 
	 private final static int SCANNIN_GREQUEST_CODE = 1;  
	    /** 
	     * 显示扫描结果 
	     */  
	    private TextView mTextView ;  
	    /** 
	     * 显示扫描拍的图片 
	     */  
	    private ImageView mImageView;  
	private void display(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = ((TextView) findViewById(R.id.tv_scan_result));  
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
      /*  qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);*/
       /* qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);*/
        btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i=new Intent(MainActivity.this,Curriculum.class); 
			startActivity(i);*/
			}
		});
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this, Curriculum.class);
				startActivity(in);
			}
		});
		spinner = (Spinner) findViewById(R.id.spinner);
		String[] universities = { "上课", "下课" };
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, universities);
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				display(((TextView) arg1).getText().toString());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				Intent openCameraIntent = new Intent(MainActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
    }
      /*  Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String contentString = qrStrEditText.getText().toString();
					if (!contentString.equals("")) {
						
						Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
						qrImgImageView.setImageBitmap(qrCodeBitmap);
					}else {
						Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
					}
					
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }*/

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		 switch (requestCode) {  
	     case SCANNIN_GREQUEST_CODE:  
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
			qrImgImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));  
		}
	
		 break;  
}
}	
}