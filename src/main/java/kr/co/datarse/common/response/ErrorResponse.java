package kr.co.datarse.common.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse extends DefaultResponse {

	private String errorMessage;
	private String errorCode;
	
	public ErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
		this.errorCode = "404";
	}
	
	public ErrorResponse(String errorMessage, String errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
}
