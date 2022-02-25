package com.example.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

//@RequestMapping("/user")
@Controller
public class AllListController {
	
	@Autowired
	private EmployeeService eService;
	
	//ユーザー一覧画面の表示
	@GetMapping("/user/allList")
	public String toAllList(@ModelAttribute Employee employee, Model model, Locale locale) {
		List<Employee> eAllList = eService.allList();
		model.addAttribute("eAllList", eAllList);
		System.out.println(eAllList.size());
		return "user/allList";
	}
}
