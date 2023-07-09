package com.simon.exception;

public class NoDataFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -656757656;
			
			public NoDataFoundException() {
			}
			public NoDataFoundException(String msg) {
				super(msg);
			}
}
