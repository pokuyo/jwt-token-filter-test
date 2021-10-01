package kr.co.datarse.sample.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.datarse.sample.service.DataTableService;


@RestController
@RequestMapping("/api/datatable")
public class DataTableController {

	@Autowired
	DataTableService dataTableService;
	
	@PostMapping("retrieveRiskList")
	public JSONObject retrieveRiskList() {
		JSONObject resultObj = new JSONObject();
		
		List<Map<String, Object>> riskList = dataTableService.retrieveRiskList();
		
		resultObj.put("RISK_LIST", riskList);
		
		return resultObj;
	}
}
