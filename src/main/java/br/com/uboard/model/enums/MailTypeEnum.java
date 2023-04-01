package br.com.uboard.model.enums;

public enum MailTypeEnum {

	CONFIRM_ACCOUNT("confirm-account.html");

	private String template;

	MailTypeEnum(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}
}
