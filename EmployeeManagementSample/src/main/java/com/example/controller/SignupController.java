package com.example.controller;

import java.util.Locale;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.application.service.EmployeeApplicationService;
import com.example.domain.Employee;
import com.example.domain.ValidGroup1;
import com.example.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {

	@Autowired
	 private EmployeeApplicationService eApplicationService;
	
	@Autowired
	private EmployeeService eService;
	
	@GroupSequence({ Default.class, ValidGroup1.class })
	interface GroupOrder {}
	
	
	//ユーザー登録画面の表示
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute Employee Employee, Model model, Locale locale) {
		Map<String, Integer> genderMap = eApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		return "signup";
	}
	
	//ユーザー登録処理
	@PostMapping("/signup")
	public String postSignup(
			@ModelAttribute @Validated(GroupOrder.class)  Employee employee, 
			BindingResult result, Model model, Locale locale ) {
		log.info(employee.toString());
		
		if (result.hasErrors()) {
			model.addAttribute("message", "エラーが発生しました");
			return getSignup(employee, model, locale);
		} else {
			log.info(employee.toString());
			
			//ユーザー登録
			eService.insertEmployeeData(employee);			
			return "redirect:/allList";
		}
	}
	
	//ユーザー一覧画面表示
	@GetMapping("/allList")
	public String getAllList(@ModelAttribute Employee employee, Model model, Locale locale) {
		return "allList";
	}
}
