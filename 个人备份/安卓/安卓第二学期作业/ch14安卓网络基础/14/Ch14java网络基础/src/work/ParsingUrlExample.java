package work;

import java.net.URL;

public class ParsingUrlExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL urlObject = new URL("http://www.ibm.com:1532");
			System.out.println("Э��: " + urlObject.getProtocol());
			System.out.println("����: " + urlObject.getHost());
			System.out.println("�˿�: " + urlObject.getPort());
			} catch (Exception e) {
			System.out.println("��URL����");
			e.printStackTrace();
	}
	}
}
