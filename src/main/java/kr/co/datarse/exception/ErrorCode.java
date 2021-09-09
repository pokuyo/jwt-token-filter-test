package kr.co.datarse.exception;

public enum ErrorCode {
	INVALID_PARAMETER(900, "1", "Invalid Request Data"),
	EMAIL_DUPLICATION(400, "1", "email duplicated");
	
	private final String code;
	private final String message;
	private final int status;
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getStatus() {
		return status;
	}
	
	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
}
