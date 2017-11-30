package songActivty;

import Song.songActivty.R;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopMenu {
private Context context;
private PopupWindow popupWindow;
Button bt1,bt2,bt3,bt4;
public PopMenu (Context context){
	this.context=context;
	View view=LayoutInflater.from(context).inflate(R.layout.popmenu, null);
	bt1=(Button) view.findViewById(R.id.PopMenu_Btn1);
	bt2=(Button) view.findViewById(R.id.PopMenu_Btn2);
	bt3=(Button) view.findViewById(R.id.PopMenu_Btn3);
	bt4=(Button) view.findViewById(R.id.PopMenu_Btn4);
	popupWindow =new PopupWindow(view,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
	Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(), R.color.lanse);
	BitmapDrawable bd=new BitmapDrawable();
	popupWindow.setBackgroundDrawable(bd);
}
public void setonitemclicklistener(OnClickListener listener){
	bt1.setOnClickListener(listener);
	bt2.setOnClickListener(listener);
	bt3.setOnClickListener(listener);
	bt4.setOnClickListener(listener);
}
public void showasdropdown(View pView){
	popupWindow.showAsDropDown(pView, 10, context.getResources().getDimensionPixelSize(R.dimen.popmenu_yoff));
	popupWindow.setFocusable(true);
	popupWindow.setOutsideTouchable(true);
	popupWindow.update();
}
public void dismiss(){
	popupWindow.dismiss();
}
}
