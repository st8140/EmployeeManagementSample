package com.example.controller;

import java.util.Locale;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import org.springframework.beans.BeanUtils;
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
import com.example.form.EmployeeForm;
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
	public String getSignup(@ModelAttribute EmployeeForm eForm, Model model, Locale locale) {
		Map<String, Integer> genderMap = eApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		eForm.setGender(1);
		return "signup";
	}
	
	//ユーザー登録処理
	@PostMapping("/signup")
	public String postSignup(
			@ModelAttribute @Validated(GroupOrder.class)  EmployeeForm eForm, 
			BindingResult result, Model model, Locale locale ) {
		Employee emp = new Employee();
		
		if (result.hasErrors()) {
			model.addAttribute("message", "エラーが発生しました");
			return getSignup(eForm, model, locale);
		} else {
			log.info(eForm.toString());
			
			//ユーザー登録
			BeanUtils.copyProperties(eForm, emp);
			eService.insert(emp);	
			
			//ユーザー一覧画面へ遷移
			return "redirect:/user/allList";
		}
	}
}
