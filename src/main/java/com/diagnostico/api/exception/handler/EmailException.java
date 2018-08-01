package com.diagnostico.api.exception.handler;

public class EmailException extends RuntimeException {

	private static final long serialVersionUID = -218973794433554204L;

	public EmailException(String msg) {
		super(msg);
	}
	
}
