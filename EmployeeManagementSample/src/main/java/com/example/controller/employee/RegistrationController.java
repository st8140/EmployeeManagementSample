package com.example.controller.employee;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.application.service.EmployeeApplicationService;
import com.example.domain.Employee;
import com.example.domain.ValidGroup1;
import com.example.form.MembersForm;
import com.example.form.RegistrationForm;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employee")
@Slf4j
public class RegistrationController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeApplicationService eApplicationService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GroupSequence({ Default.class, ValidGroup1.class })
	interface GroupOrder {}
	
	/**
	 * registation画面へ遷移
	 * @param eForm
	 * @param model
	 * @param locale
	 * @return
	 */
	// @GetMapping("/registration")
	// public String toRegistration(@ModelAttribute RegistrationForm registrationForm, Model model, Locale locale) {
	// 	MembersForm mFomr = new MembersForm();
	// 	registrationForm.getEmployeeList().add(members);
	// 	List<Employee> allList = eService.allList();
	// 	Map<String, Integer> genderMap = eApplicationService.getGenderMap(locale);
	// 	model.addAttribute("eAllList", allList);
	// 	model.addAttribute("genderMap", genderMap);
	// 	return "employee/registration";
	// }
	
	// @GetMapping("/getList")
	// @ResponseBody
	// public String getList() {
	// 	List<Employee> employeeList = eService.allList();
	// 	if (employeeList == null || employeeList.size() == 0) {
	// 		return null;
	// 	}
	// 	return getJson(employeeList);
	// }
	
	// @PostMapping("registration/insert")
	// public String insertEmployees(@ModelAttribute @Validated(GroupOrder.class) RegistrationForm registrationForm,
	// 		BindingResult result, Model model, Locale locale) {
	// 	if (result.hasErrors()) {
	// 		log.info(result.getAllErrors().toString());
	// 		return toRegistration(registrationForm, model, locale);
	// 	}
	// 	Employee emp = modelMapper.map(employeeForm, Employee.class);
	// 	emp.setStart_date(employeeForm.getDate_of_entry());
	// 	emp.setEnd_date(employeeForm.getDate_of_entry());
	// 	eService.insert(emp);
	// 	return "redirect:/employee/registration";
	// }
	
	// private String getJson(List<Employee> employeeList) {
	// 	String retVal = null;
	// 	ObjectMapper objectMapper = new ObjectMapper();
	// 	try{
  //           	retVal = objectMapper.writeValueAsString(employeeList);
  //       	} catch (JsonProcessingException e) {
  //       		System.err.println(e);
  //       	}
  //       return retVal;
	// }
}
