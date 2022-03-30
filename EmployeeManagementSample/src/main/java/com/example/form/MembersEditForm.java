package com.example.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MembersEditForm {

	private Integer id;
//	private Integer employee_id;
//	private Integer department_id;
	
	@NotNull(message="{sales_check}")
	private Integer sales;
	
	@Length(min=1, max=10)
	private String district_in_charge;
	
	@NotNull(message="{customers_check}")
	private Integer customers;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date updated_at;
}
