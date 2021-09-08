package kr.co.datarse.auth.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private int status = 200;
	private String message	= "OK";
	private String code		= "200";
	private Object data		= "no data";
}
