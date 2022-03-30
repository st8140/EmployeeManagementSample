package com.example.controller;

import java.util.Locale;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.example.domain.Members;
import com.example.domain.ValidGroup1;
import com.example.form.MembersEditForm;
import com.example.form.MembersForm;
import com.example.form.RegistrationForm;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import com.example.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/members")
@Slf4j
public class MembersController {
	
	@Autowired
	private EmployeeService eService;	
	@Autowired
	private MembersService mService;
	@Autowired
	private DepartmentService dService;
	@Autowired
	private EmployeeApplicationService eAService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ModelMapper modelMapper;
	
	@GroupSequence({ Default.class, ValidGroup1.class })
	interface GroupOrder {}

	/**
	 * 部門別/社員一覧画面へ遷移
	 */
	@GetMapping("/{department_id}/membersList")
	public String toMembersList(@ModelAttribute MembersForm membersForm,
			@ModelAttribute RegistrationForm registrationForm,
			@PathVariable("department_id") Integer department_id, Model model, Locale locale) {
		
		this.createGenderMap(model, locale);
		
		//全社員表示
		model.addAttribute("employeeJoinMembersList", mService.employeeJoinMembersList(department_id));
		//表示用の課名を取得
		model.addAttribute("getName", dService.getById(department_id).getDepartment_name());
		return "members/membersList";
	}
	
	
	
	/**
	 * 在籍社員一覧
	 */
	@GetMapping("/{department_id}/enrollmentList")
	public String enrollmentList(@ModelAttribute MembersForm membersForm,
			@ModelAttribute RegistrationForm registrationForm,
			@PathVariable("department_id") Integer department_id, Model model, Locale locale) {
		
		this.createGenderMap(model, locale);
		
		//在籍社員一覧
		model.addAttribute("findEnrollmentEmployeeList", mService.findEnrollmentEmployeeList(department_id));
		//表示用の課名を取得
		model.addAttribute("getName", dService.getById(department_id).getDepartment_name());
		return "members/membersList";
	}
	
	
	
	/**
	 * 退職社員一覧
	 */
	@GetMapping("/{department_id}/retirementList")
	public String retirementList(@ModelAttribute MembersForm membersForm,
			@ModelAttribute RegistrationForm registrationForm,
			@PathVariable("department_id") Integer department_id, Model model, Locale locale) {
		
		this.createGenderMap(model, locale);
		
		//退職社員一覧
		model.addAttribute("findRetirementEmployeeList", mService.findRetirementEmployeeList(department_id));
		//表示用の課名を取得
		model.addAttribute("getName", dService.getById(department_id).getDepartment_name());
		return "members/membersList";
	}
	
	
	
	/**
	 * 新規複数登録処理
	 */
	@PostMapping("/{department_id}/registration")
//	@ResponseBody
	public String regist(@Validated @ModelAttribute MembersForm membersForm,
		@PathVariable("department_id") Integer department_id,
		BindingResult result, Model model, Locale locale) {
	if (result.hasErrors()) {
//		Map<String, String> errors = new HashMap<>();
		log.info(result.toString());
//		// エラーメッセージ取得
//		for (FieldError error : result.getFieldErrors()) {
//			String message = messageSource.getMessage(error, locale);
//			errors.put(error.getField(), message);		
//		}
		return "members/{department_id}/membersList";
//		return new RestResult(90, errors);
	}
		// Listの要素数を取得
		Integer mFormSize = membersForm.getEmployee_name().size();
		
		for (int i = 0; mFormSize > i; i++) {
			// 新規従業員登録
			Employee employee = new Employee();
			employee.setEmployee_name(membersForm.getEmployee_name().get(i));
			employee.setEmployee_phone(membersForm.getEmployee_phone().get(i));
			employee.setEmployee_email(membersForm.getEmployee_email().get(i));
			employee.setGender(membersForm.getGender().get(i));
			employee.setDate_of_entry(membersForm.getDate_of_entry().get(i));
			employee.setPassword("password");
			employee.setStart_date(membersForm.getDate_of_entry().get(i));
			employee.setEnd_date(membersForm.getDate_of_entry().get(i));
			System.out.println(employee);
			eService.insert(employee);
			
			// 上記の新従業員を新しくメンバーとして登録
			Members members = new Members();
			members.setDistrict_in_charge(membersForm.getDistrict_in_charge().get(i));
			members.setCustomers(0);
			members.setSales(0);
			members.setRetirement(false);
			members.setUpdated_at(membersForm.getUpdated_at().get(i));
			members.setEmployee(employee);
			members.setDepartment(dService.getById(department_id));
			System.out.println(members);
			mService.multipleRegistrations(members);
			//System.out.println(i);
		}
		
		return "redirect:/members/{department_id}/membersList";
//		return new RestResult(0, null);
	}
	
	
	
	/**
	 * 部門別 / 社員編集画面へ遷移
	 */
	@GetMapping("/{employee_id}/edit")
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
	@PostMapping(value="/{department_id}/{employee_id}/edit", params="update")
	public String edit(@PathVariable("employee_id") Integer employee_id, @PathVariable("department_id") Integer department_id,
			@Validated @ModelAttribute MembersEditForm membersEditForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			//表示用の氏名を取得
			model.addAttribute("employee_name", eService.findOne(employee_id).getEmployee_name());
			return "members/edit";
		} else {
			Members members = modelMapper.map(membersEditForm, Members.class);
			members.setEmployee(eService.findOne(employee_id));
			members.setDepartment(dService.getById(department_id));
			mService.update(members);
			return "redirect:/members/{department_id}/membersList";
		}
	}
	
	/**
	 * 退職処理
	 */
	@PostMapping(value="/{department_id}/{employee_id}/retirement", params="retirement")
	public String retirement(@PathVariable("employee_id") Integer employee_id, 
				@PathVariable("department_id") Integer department_id, Model model) {
		mService.retirement(true, employee_id);
		return "redirect:/members/{department_id}/membersList";		
	}
	
	// 性別Map
	private Map<String, Integer> createGenderMap(Model model, Locale locale) {
		Map<String, Integer> genderMap = eAService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		return genderMap;
	}
	
	// 登録者リスト
	private Map<String, Integer> createEmployeeMap(Model model) {
		Map<String, Integer> employeeMap = eAService.getEmployeeMap();
		model.addAttribute("employeeMap", employeeMap);
		return employeeMap;
	}
}
