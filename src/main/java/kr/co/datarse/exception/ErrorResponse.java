package kr.co.datarse.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private LocalDateTime timestamp = LocalDateTime.now();
	
	private String message;
	private String code;
	private int status;
	
	public ErrorResponse() {}
	
	public ErrorResponse(ErrorCode errorCode) {
		this.status	= errorCode.getStatus();
		this.code	= errorCode.getCode();
		this.message= errorCode.getMessage();
	}
}
