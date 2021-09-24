package kr.co.datarse.common.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResponse<T> extends DefaultResponse {
	
	private int count;
	private T data;
	
	public CommonResponse(T data) {
		this.data = data;
	}
}
