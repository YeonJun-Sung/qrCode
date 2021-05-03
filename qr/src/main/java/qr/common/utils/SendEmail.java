package qr.common.utils;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.log4j.Log4j;
import qr.vo.EmailVO;

@Log4j
public class SendEmail {

	public static void emailSender(EmailVO email) {
		final String user_id = "totheppi";
		final String user_pw = "tothepp3545";
		String host = "smtp.naver.com";
		int port = 587;

		Properties props = System.getProperties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enble", "true");
		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String id = user_id;
			String pw = user_pw;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(id, pw);
			}
		});
		session.setDebug(true);
		MimeMessage mimeMsg = new MimeMessage(session);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, "UTF-8");
			helper.setSubject(email.getSubject());
			helper.setText(email.getContent(), true);
			helper.setFrom(user_id + "@naver.com");
			helper.setTo(email.getReceiver());

			Transport.send(mimeMsg); 
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (MailException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
