package mailutil;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private String host;
	private String username;
	private String password;

	// 构造方法

	public MailSender(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public void send(String mailTo, String mailSubject, String mailBody)
			throws Exception {
		this.send(mailTo, mailSubject, mailBody, null);
	}

	public void send(String mailTo, String mailSubject, String mailBody,
			String personalName) throws Exception {
		try {
			Properties props = new Properties();
			Authenticator auth = new Email_Autherticator();
			// 进行邮件服务器用户认证
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(props, auth);
			
			// 设置session,和邮件服务器进行通讯。
			MimeMessage message = new MimeMessage(session);
			
			message.setSubject(mailSubject); // 设置邮件主题
			message.setText(mailBody); // 设置邮件正文
			message.setSentDate(new Date()); // 设置邮件发送日期
			Address address = new InternetAddress(username, personalName);
			message.setFrom(address); // 设置邮件发送者的地址
			Address toAddress = new InternetAddress(mailTo); // 设置邮件接收方的地址
			message.addRecipient(Message.RecipientType.TO, toAddress);
			Transport.send(message); // 发送邮件
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	public class Email_Autherticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

}
