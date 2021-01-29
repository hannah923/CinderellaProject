package com.project.cinderella.exception;

//CRUD 작업 시 발생하는 예외
public class ProductRegistException extends RuntimeException{
	
	public ProductRegistException(String msg) {
		super(msg);
	}
	public ProductRegistException(String msg, Throwable e) {
		super(msg, e);
	}

}