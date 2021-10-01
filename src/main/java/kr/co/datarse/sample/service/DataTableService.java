package kr.co.datarse.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.datarse.sample.mapper.DataTableMapper;

@Service
public class DataTableService {

	@Autowired
	private DataTableMapper dataTableMapper;
	
	public List<Map<String, Object>> retrieveRiskList() {
		return dataTableMapper.retrieveRiskList();
	}

}
