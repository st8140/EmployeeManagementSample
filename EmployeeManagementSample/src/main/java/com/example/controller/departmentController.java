package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Department;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/department")
public class departmentController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private DepartmentService dService;
	
	@GetMapping("/departmentList")
	public String toDepartmentList(Model model) {
		model.addAttribute("departmentJoinMembersList", dService.departmentJoinMembersList());
		return "department/departmentList";
	}
	
	@GetMapping("/{department_id}/employeeList")
	public String toEmployeeList(@PathVariable("department_id") Integer department_id, Model model) {
		Department dep = new Department();
		model.addAttribute("employeeJoinMembersList", eService.employeeJoinMembersList(department_id))	;
//		System.out.println(dService.getById(department_id).getDepartment_name());
		model.addAttribute("getName", dService.getById(department_id).getDepartment_name());
		return "department/employeeList";
	}

}
