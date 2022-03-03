package com.example.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.EmployeeApplicationService;
import com.example.domain.Employee;
import com.example.form.SearchForm;
import com.example.service.EmployeeService;

@RequestMapping("/user")
@Controller
public class ListController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeApplicationService eApplicationService;
	
	//検索画面の表示
	@GetMapping("/search")
	public String toSearch(@ModelAttribute  Employee employee, Model model, Locale locale) {
		SearchForm sForm = new SearchForm();
		List<Employee> eAllList = eService.allList();
		this.createGenderMap(model, locale);
		model.addAttribute("eAllList", eAllList);
		model.addAttribute("sForm", sForm);
		return "user/search";
	}
	
	//検索処理
	@GetMapping("/search/result")
	public String searchResult(@ModelAttribute("sForm") @Validated SearchForm sForm, 
				BindingResult result, Model model, Locale locale) {
		if (result.hasErrors()) {
			this.createGenderMap(model, locale);
			return "user/search";
		} else {
			this.createGenderMap(model, locale);
			model.addAttribute("employees", eService.getSearchEmployee(sForm));
			return "user/search";	
		}
	}
	
	//削除処理
		@PostMapping(value="/search/{employee_id}/delete", params="delete")
		public String deleteEmployeeData(@PathVariable("employee_id") Integer employee_id, Model model) {
			eService.delete(employee_id);
			return "redirect:/user/search";
		}
	
	private Map<String, Integer> createGenderMap(Model model, Locale locale) {
		Map<String, Integer> genderMap = eApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		return genderMap;
	}
}
