package com.example.controller;

import java.text.NumberFormat;

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
	
	/**
	 * 部門別一覧画面へ遷移
	 */
	@GetMapping("/departmentList")
	public String toDepartmentList(Model model) {
		NumberFormat nfCur = NumberFormat.getCurrencyInstance();
		
		model.addAttribute("nfCur", nfCur);
		model.addAttribute("departmentJoinMembersList", dService.departmentJoinMembersList());
		return "department/departmentList";
	}
}
