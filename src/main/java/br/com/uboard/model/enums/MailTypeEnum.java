package br.com.uboard.model.enums;

public enum MailTypeEnum {

	CONFIRM_ACCOUNT("Confirmação de conta", "confirm-account.html");

	private String subject;

	private String template;

	MailTypeEnum(String subject, String template) {
		this.subject = subject;
		this.template = template;
	}

	public String getSubject() {
		return subject;
	}

	public String getTemplate() {
		return template;
	}
}
