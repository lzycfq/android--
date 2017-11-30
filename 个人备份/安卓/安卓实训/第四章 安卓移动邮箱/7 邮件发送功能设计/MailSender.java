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

	// ���췽��

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
			// �����ʼ��������û���֤
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(props, auth);
			
			// ����session,���ʼ�����������ͨѶ��
			MimeMessage message = new MimeMessage(session);
			
			message.setSubject(mailSubject); // �����ʼ�����
			message.setText(mailBody); // �����ʼ�����
			message.setSentDate(new Date()); // �����ʼ���������
			Address address = new InternetAddress(username, personalName);
			message.setFrom(address); // �����ʼ������ߵĵ�ַ
			Address toAddress = new InternetAddress(mailTo); // �����ʼ����շ��ĵ�ַ
			message.addRecipient(Message.RecipientType.TO, toAddress);
			Transport.send(message); // �����ʼ�
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
