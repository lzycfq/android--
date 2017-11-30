package work;

import java.net.URL;

public class ParsingUrlExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL urlObject = new URL("http://www.ibm.com:1532");
			System.out.println("协议: " + urlObject.getProtocol());
			System.out.println("主机: " + urlObject.getHost());
			System.out.println("端口: " + urlObject.getPort());
			} catch (Exception e) {
			System.out.println("打开URL错误");
			e.printStackTrace();
	}
	}
}
