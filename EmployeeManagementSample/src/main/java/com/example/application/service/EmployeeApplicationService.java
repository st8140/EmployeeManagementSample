package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@Service
public class EmployeeApplicationService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private EmployeeService eService;

	/** 性別Map生成 */
	public Map<String, Integer> getGenderMap(Locale locale) {
		Map<String, Integer> genderMap = new LinkedHashMap<>();
		
		String male = messageSource.getMessage("male", null, locale);
		String female = messageSource.getMessage("female", null, locale);
		genderMap.put(male, 1);
		genderMap.put(female, 2);
		return genderMap;
	}
	
	/** 従業員Map生成 */
	public Map<String, Integer> getEmployeeMap() {
		Map<String, Integer> employeeMap = new LinkedHashMap<>();
		
		// 従業員List作成
		List<Employee> empList = eService.allList();
		
		// 従業員Mapに流し込む
		empList.stream().forEach(e -> {
			employeeMap.put(e.getEmployee_name(), e.getEmployee_id());
		});
		
		System.out.println(empList);
		
		return employeeMap;
	}
	
}
