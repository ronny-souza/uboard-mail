package br.com.uboard.services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import br.com.uboard.model.transport.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Value("${spring.mail.username}")
	private String address;
	private JavaMailSender mailSender;
	private SpringTemplateEngine springTemplateEngine;

	public MailService(JavaMailSender mailSender, SpringTemplateEngine springTemplateEngine) {
		this.mailSender = mailSender;
		this.springTemplateEngine = springTemplateEngine;
	}

	public void sendMail(MailDTO mailDTO) throws MessagingException {
		mailDTO.getProperties().put("currentYear", LocalDateTime.now().getYear());

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();

		context.setVariables(mailDTO.getProperties());
		helper.setFrom(this.getAddress());
		helper.setTo(mailDTO.getTo());
		helper.setSubject(mailDTO.getSubject());

		String html = springTemplateEngine.process(mailDTO.getMailType().getTemplate(), context);
		helper.setText(html, true);

		mailSender.send(message);
	}

	private String getAddress() {
		return address;
	}

}
