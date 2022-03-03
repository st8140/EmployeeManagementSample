package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Department;
import com.example.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class departmentController {
	
	@Autowired
	private DepartmentService dService;
	
	@GetMapping("/allList")
	public String toDepartmentAllList(@ModelAttribute Department dep, Model model) {
		model.addAttribute("dAllList", dService.dAllList());
		return "department/allList";
	}

}
