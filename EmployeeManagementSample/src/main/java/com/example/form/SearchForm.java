package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchForm {

	private Integer gender;
	
	private String employee_email;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date start_date;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date end_date;
}
