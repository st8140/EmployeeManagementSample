package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domain.Employee;
import com.example.domain.EmployeeRepository;


@Service
public class EmployeeService{
	
	@Autowired
	private EmployeeRepository eRepository;
	
	/** ユーザー登録 */	
	@Transactional
	public void insert(Employee employee) {
		eRepository.save(employee);
	}
	
	/** ユーザー更新 */	
	@Transactional
	public void update(Employee employee) {
		eRepository.save(employee);
	}
	
	/** ユーザー削除 */
	public void delete(Integer employee_id) {
		eRepository.deleteById(employee_id);
	}
	
	/** ユーザー全件検索 */
	public List<Employee> allList() {
		return eRepository.findAll();
	}
	
	/** ユーザー１件検索 */
	@GetMapping("{employee_id}")
	public Employee findOne(@PathVariable("employee_id") Integer employee_id) {
		return eRepository.findById(employee_id).orElseThrow();
	}
}
