package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Department;
import com.example.domain.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository dRepository;
	
	//全件検索
	public List<Department> dAllList() {
		return dRepository.findAll();
	}
	
	//部門一覧表示用検索
	public List<Object[]> empList() {
		return dRepository.findJoinData();
	}
	
}
