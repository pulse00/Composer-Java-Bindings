package com.dubture.getcomposer.json;

public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4252451904249471375L;
	
	public static final int ERROR_UNEXPECTED_MALFORMED = 0;
	public static final int ERROR_UNEXPECTED_IO = 2;
	public static final int ERROR_UNEXPECTED_EXCEPTION = 4;

	private int errorType;

	public ParseException(String message) {
		super(message);
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

}
