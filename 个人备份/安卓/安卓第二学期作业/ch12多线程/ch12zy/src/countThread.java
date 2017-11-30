
public class countThread extends Thread {
  private int delay;
  private String message;
  public countThread(String m,int r){
	  this.delay=delay;
	  this.message=message;
	  /*message=m;
	  delay=r;*/
  }
  public void run(){
	  int sum=1;
	  for(int i=0;i<=10;i++)  {				
		  System.out.println(message+"Êä³ö½á¹û:"+sum);
		  try {
				sleep(delay);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  }

	  
	  
  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		countThread t1=new countThread("T1", 200);
		countThread t2=new countThread("T2", 200);
		countThread t3=new countThread("T3", 200);
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	}


