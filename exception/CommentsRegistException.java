package com.project.cinderella.exception;

//CRUD 작업 시 발생하는 예외
public class CommentsRegistException extends RuntimeException{
	
	public CommentsRegistException(String msg) {
		super(msg);
	}
	public CommentsRegistException(String msg, Throwable e) {
		super(msg, e);
	}

}
