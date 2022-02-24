package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.domain.EmployeeRepository;

@Service
public class EmployeeService{
	
	@Autowired
	private EmployeeRepository eRepository;
	
	/** ユーザー登録 */	
	@Transactional
	public void insertEmployeeData(Employee employee) {
		eRepository.save(employee);
	}

}
