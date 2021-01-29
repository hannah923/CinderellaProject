package com.project.cinderella.exception;

public class MailSendException extends RuntimeException{
	
	public MailSendException(String msg) {
		super(msg);
	}
	public MailSendException(String msg, Throwable e) {
		super(msg, e);
	}

}