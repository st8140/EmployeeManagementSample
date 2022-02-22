package com.example.controller;

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

import com.example.application.service.UserApplicationService;
import com.example.form.EmployeeForm;
import com.example.form.ValidGroup1;
import com.example.model.MUser;
import com.example.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {

	@Autowired
	 private UserApplicationService userApplicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GroupSequence({ Default.class, ValidGroup1.class })
	interface GroupOrder {}
	
	
	//ユーザー登録画面の表示
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute EmployeeForm eForm, Model model, Locale locale) {
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		return "signup";
	}
	
	//ユーザー登録処理
	@PostMapping("/signup")
	public String postSignup(
			@ModelAttribute @Validated(GroupOrder.class)  EmployeeForm eForm, 
			BindingResult result, Model model, Locale locale ) {
		log.info(eForm.toString());
		if (result.hasErrors()) {
			model.addAttribute("message", "エラーが発生しました");
			return getSignup(eForm, model, locale);
		} else {
			log.info(eForm.toString());
			//FormをMuserクラスに変換
			MUser mUser = modelMapper.map(eForm, MUser.class);
			
			//ユーザー登録
			userService.signup(mUser);			
			return "redirect:/allList";
		}
	}
	
	//ユーザー一覧画面表示
	@GetMapping("/allList")
	public String getAllList(@ModelAttribute EmployeeForm eForm, Model model, Locale locale) {
		return "allList";
	}
}
