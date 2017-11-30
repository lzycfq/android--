import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends Thread {
	private JLabel lblTime;//标签
	private JButton btnChange;//按钮
	private Thread clockThread;
	boolean isRunning;
	int timer=0;
	//界面布局
	private void init() {
		isRunning=false;
		lblTime=new JLabel("点击按钮开始");
		btnChange = new JButton("开始");
		JPanel panel = new JPanel();
		panel.add(btnChange);//将按钮放入面板上
		Container con=getContentPane();
		//Container con = getContentPane();//得到窗体的内容面板
		con.add(lblTime, "Center");//将标签添加到内容面板"中央"
		con.add(panel,"South");//将面板panel添加到内容面板"南面"	
		//setBounds(300, 200, 200, 200);
		SetBounds(300,200,200,200);
		setVisible(true);	
		setTitle("计时");
	}

	public MainFrame(){
		//界面布局
		init();		
		btnChange.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				//判断是否处于运行状态
				if(isAlive()){
					isRunning=false;
					btnChange.setText("开始");
				}
				else{
					isRunning=true;
					btnChange.setText("暂停");
                    //定义线程对象
					clockThread =new Thread();
					//启动线程
					clockThread.start();
				}				
			}
		});	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}

	public void run() {
		//判断是否处于运行状态
		while(isAlive()) {
            try {              
                timer++;
			//修改显示的文本
                
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

	}
	}
}

