package kr.co.datarse.sample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.datarse.common.util.ConvertUtil;
import kr.co.datarse.user.model.UserModel;

@RequestMapping("/api")
@RestController
public class SampleController {
	

	@RequestMapping("/sample/requestdata")
	public JSONObject sampleMethod(@RequestBody Map<String, Object> postparams , Authentication authentication , Principal principal) {
		
		//로그인 정보 확인방법 1
		if(authentication != null) {
			
			Object pri = authentication.getPrincipal();
			
			System.out.println( ((UserModel)pri).getUsr_nm() );
			
		}
		//로그인 정보 확인방법 2
		if(principal != null) {
			Object pp = ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
			System.out.println( ((UserModel)pp).getUsr_nm() );
		}
		
		//로그인 정보 확인방법 3
		Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
		Object authPri = auth.getPrincipal();
		System.out.println( ((UserModel)authPri).getUsr_nm() );
		
		JSONObject jsonObj = new JSONObject();
		
		int totalCount	= 300;
		int requestPage	= postparams.get("page") != null ? (int) postparams.get("page") : 1 ;
		
		ArrayList<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for(int i=0; i<10; i++) {
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("comments_count", requestPage+"at"+i);
			dataMap.put("domain"		, requestPage+"bt"+i);
			dataMap.put("id"			, requestPage+"ct"+i);
			dataMap.put("points"		, requestPage+"dt"+i);
			dataMap.put("time"			, requestPage+"et"+i);
			dataMap.put("title"			, requestPage+"ft"+i);
			dataMap.put("type"			, requestPage+"gt"+i);
			dataMap.put("url"			, requestPage+"ht"+i);
			dataMap.put("user"			, requestPage+"it"+i);
			paramList.add(dataMap);
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("totalCount"	, totalCount);
		paramMap.put("page"			, requestPage);
		paramMap.put("data"			, paramList);
		
		jsonObj = ConvertUtil.setGrid(paramMap);
		
		return jsonObj;
		
	}
	
	@RequestMapping("/sample/dashboardsample")
	public JSONObject dashboardsample() {
		JSONObject jsonObj = new JSONObject();
		
		int totalCount	= 300;
//		int requestPage	= postparams.get("page") != null ? (int) postparams.get("page") : 1 ;
		int requestPage	= 1;
		
		ArrayList<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for(int i=0; i<10; i++) {
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("projectno"		, requestPage+"at"+i);
			dataMap.put("projectstep"	, requestPage+"bt"+i);
			dataMap.put("projectname"	, requestPage+"ct"+i);
			paramList.add(dataMap);
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("totalCount"	, totalCount);
		paramMap.put("page"			, requestPage);
		paramMap.put("data"			, paramList);
		
		jsonObj = ConvertUtil.setGrid(paramMap);
		
		return jsonObj;
		
	}
}
