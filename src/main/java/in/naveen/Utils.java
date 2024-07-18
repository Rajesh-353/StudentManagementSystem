package in.naveen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class Utils {

	@Autowired
	private JavaMailSender jms;

	public boolean Sendmail(String email, String subject, String body) {
		boolean isSent = false;
		try {
			MimeMessage mimeMessage = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(email);
			jms.send(mimeMessage);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}

}
