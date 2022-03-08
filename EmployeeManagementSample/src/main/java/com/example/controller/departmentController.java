package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class departmentController {
	
	@Autowired
	private DepartmentService dService;
	
	@GetMapping("/allList")
	public String toDepartmentAllList(Model model) {
		model.addAttribute("findJoinList", dService.findJoinList());
		return "department/allList";
	}

}
