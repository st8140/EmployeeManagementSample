package com.example.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Employee;
import com.example.domain.ValidGroup1;
import com.example.form.EmployeeForm;
import com.example.service.EmployeeService;

@Controller
public class EditController {

	@Autowired
	private EmployeeService eService;
	
	@GroupSequence({ Default.class, ValidGroup1.class })
	interface GroupOrder {}
	
	//編集画面へ遷移
	@GetMapping("user/{employee_id}/edit")
	public String toEdit(
			@PathVariable("employee_id") Integer employee_id,
			@ModelAttribute EmployeeForm eForm, Model model) {
		Employee emp = eService.findOne(employee_id);
		model.addAttribute("employeeForm", emp);
		return "/user/edit";
	}
	
	//更新処理
	@PostMapping(value="user/{employee_id}/edit", params="update")
	public String editEmployeeData(
			@PathVariable Integer employee_id,
			@ModelAttribute @Validated(GroupOrder.class) EmployeeForm eForm,
			BindingResult result, Model model) {
		Employee emp =  new Employee();
		
		if(result.hasErrors()) {
			System.out.println(result);
			return "/user/edit";
		} else {
			BeanUtils.copyProperties(eForm, emp);
			eService.update(emp);
			return "redirect:/user/allList";
		}	
	}
	
	//削除処理
	@PostMapping(value="user/{employee_id}/edit", params="delete")
	public String deleteEmployeeData(@PathVariable("employee_id") Integer employee_id, Model model) {
		eService.delete(employee_id);
		return "redirect:/user/allList";
	}
		
}
