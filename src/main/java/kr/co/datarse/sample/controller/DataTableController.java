package kr.co.datarse.sample.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.datarse.sample.service.DataTableService;


@RestController
@RequestMapping("/api/datatable")
public class DataTableController {

	@Autowired
	DataTableService dataTableService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("retrieveRiskList")
	public JSONObject retrieveRiskList(@RequestBody Map<String, Object> params) {
		JSONObject resultObj = new JSONObject();
		
		int offset = (int) params.get("pagePerSize");
		int page = (int) params.get("page");
		int startIdx = ((page-1) * offset);
		int endIdx = (page * offset) - 1;
		
		params.put("startIdx"	, startIdx);
		params.put("endIdx"		, endIdx);
		
		List<Map<String, Object>> riskList = dataTableService.retrieveRiskList(params);
		int riskListTotalCount = dataTableService.retrieveRiskListCount();
		
		resultObj.put("RISK_LIST", riskList);
		resultObj.put("RISK_LIST_COUNT", riskListTotalCount);
		
		return resultObj;
	}
}
