package com.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class MUser {

	private Integer employee_id;
	private String employee_name;
	private Integer gender;
	private String employee_phone;
	private String employee_email;
	private String password;
	private Date date_of_entry;
}
