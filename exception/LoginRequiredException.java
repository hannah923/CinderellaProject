package com.project.cinderella.exception;

//메일 전송 시 발생하는 예외
public class LoginRequiredException extends RuntimeException{
	
	public LoginRequiredException(String msg) {
		super(msg);
	}
	public LoginRequiredException(String msg, Throwable e) {
		super(msg, e);
	}

}
