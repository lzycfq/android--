import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends Thread {
	private JLabel lblTime;//��ǩ
	private JButton btnChange;//��ť
	private Thread clockThread;
	boolean isRunning;
	int timer=0;
	//���沼��
	private void init() {
		isRunning=false;
		lblTime=new JLabel("�����ť��ʼ");
		btnChange = new JButton("��ʼ");
		JPanel panel = new JPanel();
		panel.add(btnChange);//����ť���������
		Container con=getContentPane();
		//Container con = getContentPane();//�õ�������������
		con.add(lblTime, "Center");//����ǩ��ӵ��������"����"
		con.add(panel,"South");//�����panel��ӵ��������"����"	
		//setBounds(300, 200, 200, 200);
		SetBounds(300,200,200,200);
		setVisible(true);	
		setTitle("��ʱ");
	}

	public MainFrame(){
		//���沼��
		init();		
		btnChange.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				//�ж��Ƿ�������״̬
				if(isAlive()){
					isRunning=false;
					btnChange.setText("��ʼ");
				}
				else{
					isRunning=true;
					btnChange.setText("��ͣ");
                    //�����̶߳���
					clockThread =new Thread();
					//�����߳�
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
		//�ж��Ƿ�������״̬
		while(isAlive()) {
            try {              
                timer++;
			//�޸���ʾ���ı�
                
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

	}
	}
}

