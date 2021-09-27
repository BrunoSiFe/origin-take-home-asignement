package com.bruno.origin.exception;

public class WrongParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WrongParametersException(String msg) {
		super(msg);
	}

	public WrongParametersException(String msg, Throwable clause) {
		super(msg, clause);
	}
}
