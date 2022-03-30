package com.example.controller.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.service.EmployeeApplicationService;
import com.example.domain.Employee;
import com.example.domain.ValidGroup1;
import com.example.form.EmployeeForm;
import com.example.rest.RestResult;
import com.example.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeRestController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeService eService;

	@Autowired
	private EmployeeApplicationService eApplicationService;
	
	@GroupSequence({ Default.class, ValidGroup1.class })
	interface GroupOrder {}
	

	@GetMapping("/get/list")
	public List<Employee> getEmployeeList(@ModelAttribute EmployeeForm eForm, Model model, Locale locale) {
		List<Employee> allList = eService.allList();
		return allList;
	}
	
	// 新規登録
	@PostMapping("/registration/rest")
	public RestResult regist(@Validated(GroupOrder.class) EmployeeForm eForm,
				BindingResult result, Model model, Locale locale) {
		List<Employee> eList = new ArrayList<>();
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			log.info(eForm.toString());
			// エラーメッセージ取得
			for (FieldError error : result.getFieldErrors()) {
				String message = messageSource.getMessage(error, locale);
				errors.put(error.getField(), message);
				
			}
			return new RestResult(90, errors);
		}
		
		Employee emp = modelMapper.map(eForm, Employee.class);
		emp.setStart_date(eForm.getStartDate());
		emp.setEnd_date(eForm.getEndDate());
		
		eService.insert(emp);
		return new RestResult(0, null);
	}
}
