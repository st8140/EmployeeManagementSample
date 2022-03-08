package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domain.Department;
import com.example.domain.DepartmentRepository;
import com.example.summary.DepartmentSummary;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository dRepository;
	
	//部門一覧表示用検索
	public List<DepartmentSummary> departmentJoinMembersList() {
		return dRepository.departmentJoinMembersData();
	}
	
	/**
	 * ID検索
	 */
	@GetMapping("{department_id")
	public Department getById(@PathVariable("department_id") Integer department_id) {
		return dRepository.findById(department_id).orElseThrow();
	}
	
}
