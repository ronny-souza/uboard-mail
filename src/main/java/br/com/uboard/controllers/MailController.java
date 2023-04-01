package br.com.uboard.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uboard.model.transport.MailDTO;
import br.com.uboard.services.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mail")
public class MailController {

	private MailService mailService;

	public MailController(MailService mailService) {
		this.mailService = mailService;
	}

	@PostMapping("/send")
	public ResponseEntity<MailDTO> send(@Valid @RequestBody MailDTO mailDTO) throws MessagingException {
		this.mailService.sendMail(mailDTO);
		return ResponseEntity.ok().build();
	}

}
