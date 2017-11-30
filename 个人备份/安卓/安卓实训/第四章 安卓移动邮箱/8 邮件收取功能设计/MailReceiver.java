package mailutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

public class MailReceiver {

	private String host;
	private String username;
	private String password;

	public MailReceiver(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public List<ReceiveMail> getIMAPMail() {
		Properties props = System.getProperties();
		List<ReceiveMail> mailList = new ArrayList<ReceiveMail>();
		props.put("mail.store.protocol", "imap");
		props.put("mail.imap.host", host);
		Session session = Session.getDefaultInstance(props);		
		IMAPStore store;
		try {
			store = (IMAPStore) session.getStore("imap");
			store.connect(username, password);
			IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			int mailCount = folder.getMessageCount();
			if (mailCount == 0) {
				folder.close(true);
				store.close();
				return null;
			}
			Message[] messages = folder.getMessages();
			if (messages.length > 0) {
				for (int i = 0; i < messages.length; i++) {
					ReceiveMail rmail = new ReceiveMail(
							(MimeMessage) messages[i]);
					rmail.setDateFormat("yy-MM-dd HH:mm");
					mailList.add(rmail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailList;
	}

	public List<ReceiveMail> getPopMail() {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		List<ReceiveMail> mailList = new ArrayList<ReceiveMail>();
		Session session = Session.getDefaultInstance(props, null);
		URLName url = new URLName("pop3", host, 110, username, username,
				password);
		try {
			Store store = session.getStore(url);
			store.connect();
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			int mailCount = folder.getMessageCount();
			if (mailCount == 0) {
				folder.close(true);
				store.close();
				return null;
			}
			Message[] messages = folder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				ReceiveMail rmail = new ReceiveMail((MimeMessage) messages[i]);
				rmail.setDateFormat("yy-MM-dd HH:mm");
				mailList.add(rmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mailList;
	}

}
