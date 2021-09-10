package kr.co.datarse.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailDuplicateException.class)
	public String handleEmailDuplicateException(EmailDuplicateException ex) throws JsonProcessingException {
		log.error("handleEmailDuplicateException: ", ex.getErrorCode());
		ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
		
		Map<String, String> map = new HashMap<>();
		map.put("code", errorResponse.getCode());
		map.put("message", errorResponse.getMessage());
		
		ResponseEntity<Map> responseEntity = new ResponseEntity(map, HttpStatus.OK); 
		
		ObjectMapper objMapper = new ObjectMapper();
		String resultMsg = "";
		resultMsg = objMapper.writeValueAsString(responseEntity);
		
		return resultMsg;
	}
}
