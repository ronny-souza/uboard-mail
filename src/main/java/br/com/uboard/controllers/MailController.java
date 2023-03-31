package br.com.uboard.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.uboard.model.MailDTO;
import br.com.uboard.services.MailService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mail")
public class MailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

	private MailService mailService;

	public MailController(MailService mailService) {
		this.mailService = mailService;
	}

	@PostMapping("/send")
	public ResponseEntity<MailDTO> send(@Valid @RequestBody MailDTO mailDTO) throws Exception {
		try {
			this.mailService.sendMail(mailDTO);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
