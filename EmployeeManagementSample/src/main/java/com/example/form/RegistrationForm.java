package com.example.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegistrationForm {

	@Valid
	List<@NotNull MembersForm> membersList = new ArrayList<>(); 
}
