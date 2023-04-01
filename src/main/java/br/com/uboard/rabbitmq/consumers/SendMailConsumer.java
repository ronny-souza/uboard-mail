package br.com.uboard.rabbitmq.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.uboard.model.transport.MailDTO;
import br.com.uboard.rabbitmq.RabbitQueues;
import br.com.uboard.services.MailService;

@Component
public class SendMailConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailConsumer.class);

	private MailService mailService;

	public SendMailConsumer(MailService mailService) {
		this.mailService = mailService;
	}

	@RabbitListener(queues = RabbitQueues.SEND_MAIL)
	public void receive(MailDTO mailDTO) {
		try {
			this.mailService.sendMail(mailDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
