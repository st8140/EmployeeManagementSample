package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domain.Employee;
import com.example.domain.EmployeeRepository;
import com.example.form.SearchForm;
import com.example.specification.EmployeeSpecification;


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
	
	/** ユーザーID検索 */
	@GetMapping("{employee_id}")
	public Employee findOne(@PathVariable("employee_id") Integer employee_id) {
		return eRepository.findById(employee_id).orElseThrow();
	}
	
	/** ユーザー検索 */
	public List<Employee> getSearchEmployee(SearchForm sForm) {
		EmployeeSpecification<Employee> spec = new EmployeeSpecification<>();
		return eRepository.findAll(Specification
					.where(spec.genderEqual(sForm.getGender()))
					.and(spec.employeeEmaiContains(sForm.getEmployee_email()))
					.and(spec.startDateGreaterThanEqual(sForm.getStart_date()))
					.and(spec.endDateLessThanEqual(sForm.getEnd_date()))
					);
	}
}
