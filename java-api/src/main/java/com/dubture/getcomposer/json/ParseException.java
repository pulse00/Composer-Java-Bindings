package com.dubture.getcomposer.json;

public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4252451904249471375L;
	
	public static final int ERROR_UNEXPECTED_CHAR = org.json.simple.parser.ParseException.ERROR_UNEXPECTED_CHAR;
	public static final int ERROR_UNEXPECTED_TOKEN = org.json.simple.parser.ParseException.ERROR_UNEXPECTED_TOKEN;
	public static final int ERROR_UNEXPECTED_EXCEPTION = org.json.simple.parser.ParseException.ERROR_UNEXPECTED_EXCEPTION;

	private int errorType;
	private Object unexpectedObject;
	private int position;

	public ParseException(String message) {
		super(message);
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public Object getUnexpectedObject() {
		return unexpectedObject;
	}

	public void setUnexpectedObject(Object unexpectedObject) {
		this.unexpectedObject = unexpectedObject;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer();

		switch (errorType) {
		case ERROR_UNEXPECTED_CHAR:
			sb.append("Unexpected character (").append(unexpectedObject)
					.append(") at position ").append(position).append(".");
			break;
		case ERROR_UNEXPECTED_TOKEN:
			sb.append("Unexpected token ").append(unexpectedObject)
					.append(" at position ").append(position).append(".");
			break;
		case ERROR_UNEXPECTED_EXCEPTION:
			sb.append("Unexpected exception at position ").append(position)
					.append(": ").append(unexpectedObject);
			break;
		default:
			sb.append("Unkown error at position ").append(position).append(".");
			break;
		}
		return sb.toString();

	}
}
