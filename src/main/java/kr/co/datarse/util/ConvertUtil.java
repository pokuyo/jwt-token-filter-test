package kr.co.datarse.util;

import java.util.Map;

import org.json.simple.JSONObject;

public class ConvertUtil {

	@SuppressWarnings("unchecked")
	public static JSONObject setGrid(Map<String, Object> params) {
		
		JSONObject resultJson = new JSONObject();
		
		int totalCount	= 0;
		int page		= 1;
		totalCount		= params.get("totalCount") != null ? (int)params.get("totalCount") : 0;
		page			= params.get("page") != null ? (int)params.get("page") : 1;
		
		// pagination
		JSONObject pageObject = new JSONObject();
		pageObject.put("page"		, page);
		pageObject.put("totalCount"	, totalCount);
		
		// return data set
		JSONObject dataObject = new JSONObject();
		dataObject.put("contents"	, params.get("data"));
		dataObject.put("pagination"	, pageObject);
		
		// result flag
		resultJson.put("result"	, true);
		resultJson.put("data"	, dataObject);
		
		return resultJson;
	}
}
