package com.example.controller;

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

import com.example.domain.Members;
import com.example.form.MembersEditForm;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import com.example.service.MembersService;

@Controller
//@RequestMapping("/members")
public class MembersController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private MembersService mService;
	
	@Autowired
	private DepartmentService dService;

	/**
	 * 部門別/社員一覧画面へ遷移
	 * @param department_id
	 * @param model
	 * @return
	 */
	@GetMapping("/members/{department_id}/membersList")
	public String toMembersList(@PathVariable("department_id") Integer department_id, Model model) {
		model.addAttribute("employeeJoinMembersList", mService.employeeJoinMembersList(department_id))	;

		//表示用の課名を取得
		model.addAttribute("getName", dService.getById(department_id).getDepartment_name());
		return "members/membersList";
	}
	
	/**
	 * 部門別 / 社員編集画面へ遷移
	 */
	@GetMapping("members/{employee_id}/edit")
	public String toMembersEdit(@PathVariable("employee_id") Integer employee_id,
			@ModelAttribute MembersEditForm membersEditForm, Model model) { 
		//表示用の氏名を取得
		model.addAttribute("employee_name", eService.findOne((Integer)employee_id).getEmployee_name());
		
		Members member = mService.findOne(employee_id);		
		model.addAttribute("membersEditForm", member);
		return "members/edit";
	}
	
	/**
	 * 更新処理
	 */
	@PostMapping(value="/members/{employee_id}/edit", params="update")
	public String edit(@PathVariable("employee_id") Integer employee_id,
			@Validated @ModelAttribute MembersEditForm membersEditForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			//表示用の氏名を取得
			model.addAttribute("employee_name", eService.findOne(employee_id).getEmployee_name());
			return "members/edit";
		} else {
			Members member = new Members();
			BeanUtils.copyProperties(membersEditForm,member);
			mService.update(member);
//			Integer id = member.getDepartment_id();
			return "redirect:/department/departmentList";
//			return "redirect:/{id}/departmentList";
		}
	}
}
