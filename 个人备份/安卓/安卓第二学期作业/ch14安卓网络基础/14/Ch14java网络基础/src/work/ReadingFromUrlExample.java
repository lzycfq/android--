package work;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadingFromUrlExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int bytes;
		String contents;
		try {
		URL gdpepe= new URL("http://www.gdpepe.cn/");
		BufferedReader in = new BufferedReader(
		new InputStreamReader(gdpepe.openStream()));
		String str;
		while ((str = in.readLine()) != null) {
			System.out.println(str);
		}
		in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		}
