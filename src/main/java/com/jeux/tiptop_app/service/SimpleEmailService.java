/**
 * 
 */
package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.SendMail;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class SimpleEmailService implements EmailService {

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String username;


	@Override
	public String sendEmail(SendMail usr) throws MessagingException {
		final AtomicInteger counter = new AtomicInteger(0);

			try {
				Context context = new Context();
				Map<String, Object> map = new HashMap<>();
				map.put("firstname", usr.getUser());
				map.put("lastname", usr.getUser());
				map.put("name", usr.getUser());
				map.put("sign", usr.getUser());
				map.put("location", "");
				map.put("uniqueid", UUID.randomUUID().toString());
				map.put("repo", "");
				map.put("blogtype", "");
				context.setVariables(map);
				String process = springTemplateEngine.process("welcome", context);
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				String subject = StringUtils
						.join(Arrays.asList("", "", usr.getUser(), "!!"), ' ');
				helper.setSubject(subject);
				helper.setText(process, true);
				helper.setTo(usr.getToemail());
				helper.setFrom(username);
				javaMailSender.send(mimeMessage);
			} catch (MessagingException | MailException ex) {
				counter.incrementAndGet();
			}
		if (counter.intValue() > 0) {
			return counter.intValue() + " email(s) sending failed. Please verify logs...!!!";
		}
		return "Email(s) sent successfully, Please check your inbox...!!!";
	}
}
